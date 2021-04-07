# [14501] 퇴사

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int answer = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
            // i번째 날에 일을 하는 경우
			if(i + day <= N) {
				dp[i+day] = Math.max(dp[i] + money, dp[i+day]);
				answer = Math.max(dp[i+day], answer);
			}
			
            // i번째 날에 일을 안 하는 경우
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			answer = Math.max(answer, dp[i+1]);
		}
		
		sb.append(answer);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
```

## 문제 풀이
코드는 매우 간단합니다!

i번째 날에 일을 하는 경우는 `i+day 날에 i번째 날에 일을 했을 때 얻는 돈` + `이전에 일을 해서 i번째 날에 받은 돈`을 i+day에 넣어주면 됩니다.
   - 대신, i번째 날에 일을 해도 i+day에 이미 더 큰 값이 있다면 갱신할 필요가 없습니다.

문제는 i번째 날에 일을 안 한 경우입니다.

i번째 날에 일을 하지 않았다면, 다음 날로 넘어가는 것인데 그냥 넘어가는 것이 아니라 i번째날까지 일했을 때 받은 돈을 다음 날로 옮겨야 합니다.
   - 대신 i+1번째 날에도 이전에 일을 해서 받은 돈이 있을 수 있으므로, dp[i+1]과 dp[i] 둘 중 더 큰 것을 dp[i+1]에 넣어줘야 합니다.