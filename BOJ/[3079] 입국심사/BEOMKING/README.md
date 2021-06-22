# [3079] 입국심사

## 분류

> 이분탐색

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 각 심사대 수
        int M = Integer.parseInt(st.nextToken()); // 친구 수
        int jt[] = new int[N];
        for (int i = 0; i < N; i++) {
            jt[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jt);

        long jtmax = jt[N - 1]; // 심사대 중 최장 시간
        long max = M * jtmax; // 최장 시간과 친구 수를 곱하면 최대 걸리는 시간
        long result = M * jtmax;
        long min = 0;
        long mid;

        while(min <= max){
            mid = (max + min) / 2;
            long sum = 0; // 최대 친구 수
            for (int i = 0; i < N; i++) {
                sum += mid / jt[i];
            }
            if(sum >= M) {
                result = Math.min(mid, result);
                max = mid - 1;
            }
            if(sum < M) {
                min = mid + 1;
            }
        }
        System.out.print(result);
    }
}
```

## 문제풀이

이분탐색 문제입니다.

DP 문제인가 했었는데 입력 값들이 굉장히 크기 때문에 안됩니다.

상근이의 친구들이 여러 심사대를 통과하는 시간의 최소값을 구해야합니다.

특정 시간이 있다면 그 시간 사이에 통과할 수 있는 사람들의 수를 구할 수 있고 그 수가 M명이 되는 최소의 시간을 구하면 해결할 수 있습니다.

이분 탐색을 위한 최소값 0, 최대 값은 심사대 중 최장 시간에서 친구의 수를 곱하면 구할 수 있습니다.

(입력으로 int M, int jtmax(심사대 최장 시간)을 하고 long max = M * jtmax 를 했을 경우 오버플로우가 발생합니다. 두 개의 인수 중 하나는 long형으로 해야 long형으로 곱할 수 있습니다.)

임의의 시간 / 현재 심사대의 시간 => 현재 심사대가 최대 처리할 수 있는 인원이므로 모든 심사대에서 이 연산을 한다면 임의의 시간의 최대 처리 인원을 알 수 있습니다.
