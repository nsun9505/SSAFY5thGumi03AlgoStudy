# [14938] 서강그라운드

## 분류
> 그래프 이론
>
> 다익스트라
>
> 플로이드-와샬

## 코드
```java
package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938_서강그라운드 {
    static int n, m, r, map[][], a, b, l, item[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        item = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 100);
        }

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            map[a][b] = l;
            map[b][a] = l;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if(k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int now = item[i];
            for (int j = 1; j <= n; j++) {
                if(map[i][j] <= m) now += item[j];
            }
            result = Math.max(result, now);
        }

        System.out.println(result);
    }
}
```

## 문제풀이
모든 정점의 거리를 알아야하기에 플로이드 와샬로 풀었습니다.

각 간선은 양방향이기 때문에 배열에 값을 입력할 때 반대 방향도 입력을 했습니다.

`끝나지 않는 파티` 문제와 다르게 모든 점과 점 사이의 거리를 주지 않기에 큰 값으로 배열을 초기화 해야합니다.

자기 자신의 아이템 개수와 m 보다 작은 거리에 있는 곳의 아이템의 수를 합친 것을 반복문으로 최대 값을 구하면 해결할 수 있습니다. 