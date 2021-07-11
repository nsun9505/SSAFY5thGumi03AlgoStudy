# [1956] 운동

## 분류

그래프 이론

플로이드 와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, map[][], a, b, c, Max = 4000001, result = 8000001, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V + 1][V + 1];

        for (int i = 0; i <= V; i++) {
            Arrays.fill(map[i], Max);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i == j) continue;
                for (int k = 1; k <= V; k++) {
                    if(j == k || i == k) continue;
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                if(map[i][j] >= Max || map[j][i] >= Max) continue;
                int temp = map[i][j] + map[j][i];
                result = Math.min(result, temp);
            }
        }
        if(result == 8000001) result = -1;
        System.out.println(result);
    }
}
```

## 문제풀이

플로이드 와샬 문제입니다.

사이클 길이 합의 최소가 아닌 최소 사이클 길이만 알면 되는 단순한 문제입니다.

길이가 모두 양수이므로 플로이드 와샬을 진행하고 a -> b가 되고 b-> a가 된다면 최소 사이클이 성립합니다.

그렇기 때문에 맵을 모두 확인하면서 a -> b가 되고 b-> a가 되는지만 확인해서 최소값을 찾으면 됩니다.

(ex 예외)

11 11
1 2 10000
2 3 10000
3 4 10000
4 5 10000
5 6 10000
6 7 10000
7 8 10000
8 9 10000
9 10 10000
10 11 10000
11 1 10000

입력인 경우

코드에서 Max, result 초기값을 100000을 하면 안되야하는데 된다.

사이클은 1 ~ 11, 11 -> 1이 유일해서 길이가 110000이 되는데 result를 초과해서 안되야한다.

근데 됨