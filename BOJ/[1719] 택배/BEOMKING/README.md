# [1719] 택배

## 분류
> 그래프 이론
>
> 다익스트라
>
> 플로이드-와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1719_택배 {
    static final int INF = 10000;
    static int n, m, a, b, c, map[][], result[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        result = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
            map[b][a] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if(k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if(i == j) continue;
                    if(map[i][j] > (map[i][k] + map[k][j])){
                        map[i][j] = map[i][k] + map[k][j];
                        result[i][j] = k;
                        int t = j;
                        while(result[i][t] != 0){
                            result[i][j] = result[i][t];
                            t = result[i][t];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j) System.out.print('-' + " ");
                else if(result[i][j] == 0) System.out.print(j + " ");
                else System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

```

## 문제풀이
플로이드 와샬의 응용 문제입니다.

양방향이기 때문에 입력할 때 반대 방향도 값을 넣어야합니다.

플로이드 와샬로 최단거리를 갱신할 때 현재 거리보다 특정 경로를 거쳤을 때 값이 더 작다면 그 경로로 갱신을 하면 되는데 여기서 주의할 점이 있습니다.

특정 경로로 가는 것이 최단 거리이지만 그 경로가 가장 먼처 거치는 경로는 아닐 수 있다는 점입니다.

그러므로 그 경로를 찾아야합니다.

예시로 1 ~ 5로 가는 최단 경로의 k가 3일때 초기값은 3입니다. 그 이후 1 ~ 3으로 가는 첫 경로를 찾고

만약 그 안에 k가 2라면 2로 갱신을 합니다. 이후 1 ~ 2로 가는 경로의 값이 0이라면 중간에 거치는 값이 없는 것이므로 반복문을 종료시킵니다.

이러한 원리로 풀었는데 비효율적인 것 같습니다. 남썬의 풀이 방법을 추천합니다.