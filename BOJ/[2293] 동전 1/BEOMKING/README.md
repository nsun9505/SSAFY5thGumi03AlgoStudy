# [2293] 동전 1

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int dp[] = new int[k + 1];

        int coin[] = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            dp[0] = 1;
            for (int j = 1; j <= k; j++) {
                if (j - coin[i] < 0) continue;
                dp[j] = dp[j] + dp[j - coin[i]];
            }
        }
        System.out.println(dp[k]);
    }
}

```

## 문제풀이
DP 문제입니다.