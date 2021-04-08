# [10844] 쉬운 계단의 수

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][10];
        for(int i=1; i<=9; i++)
            dp[0][i] = 1;

        for(int i=1; i<N; i++){
            // 0과 9는 이전에 나온 1의 개수나 8의 개수랑 동일
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
            // 나머지 1~8은 -1, +1 한 숫자의 개수를 더한 것과 동일
            for(int j=1; j<9; j++)
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
        }

        int answer = 0;
        // N번째에 있는 숫자들을 모두 더하면서 1000000000로 나눈 나머지를 계속 더해주면 됩니다.
        for(int i=0; i<10; i++)
            answer = (answer + dp[N-1][i]) % 1000000000;
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
규칙을 찾으면 빨리 풀 수 있습니다!

처음에 0을 제외한 모든 숫자를 나올 수 있으니깐 1~9까지로 9개입니다.

수의 길이가 2인 경우 두 번째부터는 0도 나올 수 있습니다.

그리고 수의 길이가 1인 경우는 9개, 2인 경우는 어떻게 될지 한 번 살펴보겠습니다.

수의 길이가 1인 경우에서 다음에 올 수 있는 수들을 한 번 카운트해봅니다.
   - 1 -> 0, 2
   - 2 -> 1, 3
   - 3 -> 2, 4
   - 4 -> 3, 5
   - 5 -> 4, 6
   - 6 -> 5, 7
   - 7 -> 6, 8
   - 8 -> 7, 9
   - 9 -> 8

i번째 자리에 어떤 수 K가 나왔다면 i+1번째 자리에는 K-1과 K+1이 나올 것입니다.
   - 왜냐하면 계단 수는 인접한 모든 자리수의 차이는 1이 나기 때문입니다.

그런데 0과 9는 i+1번째 자리에 올 수 있는게 하나 뿐입니다.
   - 0은 1만 오고, 9는 8만오게 됩니다.
   - 그러면 0과 9는 따로 처리하면 됩니다.

나머지 숫자인 1 ~ 8은 -1, +1한 수가 이전에 나온만큼 다음 자리에 나오게 됩니다.
   - 1이 첫번째 자리에 나왔다면 두 번째 자리에는 0과 2가 하나씩 나오고
   - 2가 첫번째 자리에 나왔다면 두 번째 자리에는 1과 3이 하나씩 나오고
   ...
   - 이런식으로 진행해보면 규칙이 보입니다.

그래서 점화식을 도출하면
    ```
        dp[i][0] = dp[i-1][1]; // 이전에 1이 나왔던 횟수
        dp[i][9] = dp[i-1][8]; // 이전에 8이 나왔던 횟수

        // 1~8은 이전 자리에서 -1한 값과 +1한 값을 더하기 
        for(int num=1; num<10; num++)
            dp[i][num] = (dp[i-1][j-1] + dp[i-1][j+1]);
    ```

마지막으로 dp[N][0~9]를 모두 더해서 답으로 출력하면 됩니다.