# [11660] 구간 합 구하기 5

## 분류

> DP

## 코드

```java
package BOJ.BOJ11660;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sum = new int[N*N+1];
        int index = 1;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                sum[index] = sum[index-1] + num;
                index++;
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int result = 0;
            for(int x = x1; x<=x2; x++){
                int start = N * (x-1) + y1;
                int end = N * (x-1) + y2;
                result += sum[end] - sum[start-1];
            }

            sb.append(result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이

문제 조건을 잘못 읽으면 틀립니다!

이전에도 이런 문제 풀어본 경험이 있는데 막 풀다가
시간이 좀 걸렸습니다.

주의할 점은 y1과 y2입니다.

예를 들어, (2,2) ~ (3,1)과 같은 입력은 주어지지 않습니다.

왜냐하면 조건에서 y1 <= y2이라고 했으므로 위에 주어진 2(y1) <= 1(y2)이므로 조건에 맞지 않습니다!

그러면 (2, 2) ~ (3, 3)로 해보겠습니다.

x1 <= x2, y1 <= y2 모두 성립하므로 답을 찾으면 됩니다.

(2, 2), (2, 3), (3, 2), (3, 3) 원소를 모두 더 해주면 됩니다.

N이 4라고 하면 (2, 2), (2, 3), (2, 4), (3, 1), (3, 2), (3, 3)이 아니라

(3, 1)을 뺀 구간합 입니다.

즉, **_행은 x1 ~ x2이면서 각 행에서 y1 ~ y2 사이의 구간 합_**을 구하면 되는 것입니다.

그리고 2차원 배열 인덱스를 1차원 배열 인덱스로 변환해서 구간합을 구했습니다.

각 원소는 1000이하 이므로 N이 최대 1024가 되어도 구간합은 int 범위를 넘어서지 않으므로 구간합을 구해서 저장한 배열 sum은 int 형으로 선언해도 괜찮습니다.
