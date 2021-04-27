# [1719] 택배

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
    static int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N+1][N+1];
        int[][] prev = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 경로 입력
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 무방향 그래프이므로 u->v, v->u 모두 설정
            if(d < dist[u][v]){
                dist[u][v] = d;
                dist[v][u] = d;
                prev[u][v] = v;
                prev[v][u] = u;
            }
        }

        for(int k=1;k<=N;k++){
            for(int i=1;i<=N; i++){
                for(int j=1;j<=N; j++){
                    int d = dist[i][k] + dist[k][j];
                    if(d < dist[i][j]){
                        dist[i][j] = d;
                        prev[i][j] = prev[i][k];
                    }
                }
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(prev[i][j] == 0) sb.append("- ");
                else sb.append(prev[i][j] + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
플로이드 와샬로 풀 수 있습니다!

물론 다익스트라로 해서 임의의 정점에서 출발할 때 다른 정점으로 가는 최단 거리를 구할 수 있습니다.

문제는 A 집하장에서 B 집하장으로 가는데 어디로 가야하는지를 출력해야 하는 문제입니다.

간단하게 플로이드 와샬을 돌리면서 dist[i][j]가 갱신될 때 다른 작업이 필요한 것을 알 수 있습니다.
   - 추가로 해야하는 것이 바로 prev를 갱신하는 것입니다.
   - prev는 i->j로 갈때 i에서 어떤 정점으로 가야 j로 가는 최단 경로인지를 알려주는 것입니다.

플로이드 와샬을 사용하면 i 집하장에서 j 집하장으로 갈 때 k 정점을 지나서 갑니다.
   - 즉, i -> ... -> k -> ... -> j가 될것입니다.
   - 중간에 ...이 들어가는 이유는 i에서 k로 가는데에도 바로 갈 수도 있지만, i에서 k로 가는 경로도 어떤 경로를 지나서 갈 수도 있기 때문에 prev[i][k]를 prev[i][j]의 값으로 넣어줍니다.

플로이드 와샬을 모두 돌리고 나면 prev도 모두 갱신되었으므로 prev[i][j]가 0이면 `-`를 출력하고 prev가 0이 아니라면 prev[i][j]를 출력하면 답이 됩니다.