# [11265] 끝나지 않는 파티

## 분류
> 그래프 이론
>
> 플로이드-와샬

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final String result[] = {"Enjoy other party\n", "Stay here\n"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N][N];

        // 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 와샬
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            // 시간 이내인지 아닌지 확인
            if(dist[a][b] <= c) sb.append(result[0]);
            else sb.append(result[1]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
플로이드 와샬로 쉽게 해결할 수 있습니다.

N이 500이긴 하지만, 주어진 시간이 2초기 때문에 충분합니다!

그리고 M개의 입력으로 주어지는 것이 A 파티장에서 B 파티장으로 가는 것이므로 하나의 지점에서 다른 지점들로 가는 최단 경로가 아니라 모든 경로에 대해서 다 필요하기 때문에 플로이드 와샬을 사용했습니다.

먼저 플로이드 와샬로 모든 지점에서 다른 지점으로 가는 최단 경로를 구합니다.

그리고 M개의 입력을 받으면서 A에서 B로 갈 때 시간(C) 이내에 도착할 수 있는지 없는지 판단해서 Enjoy other party나 Stay here을 출력하면 됩니다.