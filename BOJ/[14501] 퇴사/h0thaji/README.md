# [14501] 퇴사

## 분류
> 그리디 

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N; // 일할 수 있는 기간
	static int[] T,P, dp; // 상담 완료 기간, 상담 했을 때 받을 수 있는 금액, 메모이제이션 할 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];	
		P = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1];
		
		for (int i = 0; i < N; i++) { // 첫 날 부터 마지막 날까지 확인해야하므로 반복
			// i+T[i]가 N+1 작을 때(해당 일에 상담을 하고 끝나는 일이 일할 수 있는 기간보다 작을 때) , 즉 일할 수 있을 때
			if(i+T[i] < N+1) dp[i+T[i]] = Integer.max(dp[i+T[i]], dp[i]+P[i]);  // 일 끝나고 받을 수 있는 돈을 저장 , 해당 일에 받을 수 있는 경우에서 가장 큰 금액만 저장

			// 일할 수 없으면 
			dp[i+1] = Integer.max(dp[i+1], dp[i]); // 이때까지 받은 금액을 다음 날로 옮겨야함 , 단 다음 날에도 상담 해서 받은 돈이 있을 수 있으니 가장 많이 받은 금액으로 저장
		}
		
		sb.append(dp[N]); // N+1배열로 생성 했으므로 마지막값이 답
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}

```

## 문제풀이

- 오늘부터 N+1일째 되는 날 퇴사하는데 남은 N일 동안 최대한 많은 상담을 하여 최대 수익을 구하는 문제입니다 (백준이는 돈독올라서 일찍 죽을듯)

- 이 문제의 키포인트는 상담을 하냐, 안하냐 입니다. 또한 상담을 못할 수 있습니다.

  - 상담 할 경우

    - 해당 일에 상담을 하고 상담이 끝나는 날이 퇴사하기 전( < N+1)이면 상담이 가능합니다.

    - 상담이 가능하고 상담을 할 경우, 상담이 끝나는 날에 `이때까지 번 금액` + `해당 상담으로 번 금액` 을 저장해줍니다.

      - 단 , 해당 상담이 끝나는 날에 다른 경우로 번 금액이 저장되어 있다면 최대 수익을 구하는 문제이므로 `저장할 금액` , `저장되어있는 금액` 을 비교하여 큰 수를 저장시켜야 합니다.

      - ```java
        dp[i+T[i]] = Integer.max(dp[i+T[i]], dp[i]+P[i]);
        // i+T[i]는 해당 일에 상담하고 끝나는 날
        ```

    - 상담을 못 할 경우는 넘어갑니다.

  - 상담 안 할 경우

    - 상담을 안 할 경우 `이때 까지 번 금액` 을 다음 날(i+1)로 가지고 갑니다.

    - 단, 다음날(i+1)날에 다른 경우를 통해 벌었던 금액이 있을 수 있으므로 `저장할 금액` , `저장되어있는 금액` 을 비교하여 큰 금액을 저장합니다.

      - ```java
        dp[i+1] = Integer.max(dp[i+1], dp[i]);
        ```

        

- 위의 경우를 다 따져보면 N+1날에 최대 수익이 저장되어있으므로 dp[N]에 저장된 값이 결과가 됩니다.