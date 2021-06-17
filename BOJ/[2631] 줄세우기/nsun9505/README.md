# [2631] 줄세우기

## 분류

> DP

## 코드

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int max = 1;
        int maxIdx = 0;
        for(int i=0; i<N; i++){
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            if(max < dp[i]){
                max = dp[i];
                maxIdx = i;
            }
        }

        int answer = N - dp[maxIdx];
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이

LIS를 사용해서 풀 수 있습니다.

LIS는 수열에서 증가하는 부분 수열 중에서 그 길이가 최대인 수열을 구하는 알고리즘입니다.

문제는 정렬되지 않은 숫자를 정렬되도록 하기 위해 최소로 움직이는 것을 알아내야 합니다.

가장 좋은 방법은 이미 정렬되어 있는 것은 움직이지 않고, 나머지는 자기 자리를 올바르게 찾아가야 하는 숫자들을 찾으면 되는 것입니다.

그래서 LIS를 사용해서 최장 증가 부분 수열의 길이를 알아내면 현재 수열에서 움직이지 않아도 되는 숫자들의 수를 알게 됩니다.

그러면 나머지 숫자들은 움직여야 하므로 N에서 최장 증가 부분 수열의 길이를 빼면 움직여야 하는 숫자들의 개수를 알게되므로 이것이 답이 됩니다.
