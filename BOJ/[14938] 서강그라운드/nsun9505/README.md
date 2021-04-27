# [14938] 서강그라운드

## 분류
> 그래프 이론
>
> 다익스트라
>
> 플로이드-와샬

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N][N];
        int[] items = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            items[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        // 플로이드 와샬
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 임의의 정점 i에서 시작해서 j로 갈 때 M 이내 거리에 있다면 j에 있는 아이템 습득
        // 그 중 가장 큰 값을 찾아서 정답으로 출력
        int answer = 0;
        for(int i=0; i<N; i++){
            int sum = 0;
            for(int j=0; j<N; j++){
                if(dist[i][j] <= M)
                    sum += items[j];
            }
            answer = Math.max(answer, sum);
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
플로이드 와샬로 풀었습니다!

N이 최대 100이므로 충분하다고 생각했습니다.

물론 다익스트라로도 풀 수 있습니다!
   - 다익스트라는 ElogE니깐 모든 정점에 대해서 다른 정점들로 가는 모든 최적 경로를 알아야 하므로 N * (ElogE) = 100 * (100 * log100) = 200log100 = 약 1400 정도이기 때문에 충분합니다.

문제는 하나의 정점에서 다른 정점들로 가는 최단 경로를 구하고 획득할 수 있는 아이템 수가 가장 많은 것을 알아내는 것입니다.

어떤 지역으로 착륙을 할지 모른므로 모든 정점에 대해서 시작 정점으로 설정해서 다른 지역으로 가는 최단 경로가 필요합니다.

그래서 플로이드 와샬로 풀었습니다.
   - N이 100이하이므로 100^3 = 1,000,000이므로 충분합니다.

그러면 플로이드 와샬로 각 정점에서 다른 정점으로 가는 최단 거리를 구합니다.

그리고 0번 정점부터 N-1정점부터 다른 정점으로 가는 거리를 보면서 R이내라면 획득할 수 있는 아이템 수를 구합니다.
   - 즉, 1번 정점에서 출발했을 때 어떤 정점으로 가는데 거리가 R이내라면 해당 노드에서 얻을 수 있는 아이템 수를 sum에 더합니다.
   - 구한 sum 값을 정답과 비교해서 더 큰것을 정답으로 갱신하면 됩니다.