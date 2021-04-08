# [2156] 포도주 시식

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.*;

public class Main {
    static int[] arr = new int[10001];
    static int[] dp = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        for(int i=3; i<=N; i++){
            // 1번 연속 먹는 경우와 2번 연속 먹는 경우
            dp[i] = Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]);
            // 0번 연속 먹는 경우
            dp[i] = Math.max(dp[i-1], dp[i]);
        }
        sb.append(dp[N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
계단 오르기 문제랑 비슷했던 문제 같습니다.

문제에서 3번 연속 먹을 수 없기 때문에, 먹을 수 있는 경우의 수는 0번, 1번, 2번 연속으로 먹을 수 있습니다.
   - i번째 포도주를 먹는 경우는 `0번 연속`, `1번 연속`, `2번 연속`이라는 말입니다.

`0번 연속`의 경우 i번째 포도주를 먹지 않겠다는 말입니다.
   - 그리고 dp는 i일 때의 최적해이므로 i번째에 먹지 않아도 dp[i]는 최적해를 가져야 합니다.
   - 그래서 i를 먹지 않겠다는 것은 i-1번째 포도주를 먹었거나 먹지 않았을 경우와 같기 때문에 `dp[i] = dp[i-1]`이 됩니다.

i번째 포도주를 `1번 연속` 먹는 경우
   - 이 경우는 i-1(직전 포도주)번째 포도주를 먹지 않는 것입니다.
   - 그러면 i번째가 1번 연속이 되려면, i-2번째를 먹고 i번째를 먹으면 1번 연속하는 경우입니다.
   - i-3은 이미 i-2에서 i-2를 먹지 않았을 경우에 i-3이 들어가기 때문에 i-2 밑으로는 볼 필요가 없습니다.

i번째 포도주를 `2번 연속` 먹는 경우
   - i번째가 2번 연속이 되려면 무조건 i-1번째를 먹어야 합니다.
   - 그리고 i-2번째는 먹지 말아야 합니다. i-2번째를 먹으면 i-2, i-1, i 처럼 3번 연속이 되기 때문에 i-2는 먹으면 안 됩니다.
   - 그러면 i-1이 1번째 연속이 되려면 어떻게 해야 할까요
   - i-1이 1번째가 되러면 i-3에서 오면 되는 것입니다.
   - 그러면 `dp[i] = i-3에서 제일 많이 마신 포도주 + i-1번째 포도주 + i번째 포도주`를 계산하면 `2번 연속`먹는 경우를 생각할 수 있습니다.

0번, 1번, 2번 먹는 경우에 대해서 점화식을 써보면 다음과 같습니다.
```
    continueOne = dp[i-2] + arr[i]
    continueTow = dp[i-3] + arr[i-1] + arr[2]
    dp[i] = max(continueOne, continueTwo, dp[i-1]);
```
   - `continueOne` : i번째를 1번 연속 먹는 경우
   - `continueTwo` : i번째를 2번 연속 먹는 경우
   - `dp[i-1]` : i번째를 0번 연속 먹는 경우

위 점화식을 코드로 작성하고 제출하면 끝!