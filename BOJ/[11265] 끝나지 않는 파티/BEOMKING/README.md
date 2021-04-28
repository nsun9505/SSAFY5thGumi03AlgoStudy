# [11265] 끝나지 않는 파티

## 분류
> 그래프 이론
> 플로이드-와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11265_끝나지않는파티 {
    static int N, M, map[][], A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
//                if(i == j) map[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if(k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(map[A][B] <= C) System.out.println("Enjoy other party");
            else System.out.println("Stay here");
        }
    }
}
```

## 문제풀이

플로이드 와샬 문제입니다.

플로이드 와샬 알고리즘은 n^3의 시간 복잡도를 가지고 있습니다.

그래서 N의 최대값인 500의 세제곱은 125,000,000이므로 쌉가능입니다.

알고리즘을 사용해 각 파티로 이동하는 최단 거리를 구하고 A에서 B로 가는 최단거리가 C보다 같거나 작다면 "다른 파티를 즐겨라" 아니면 "여기 머물러"를 출력하면 됩니다.