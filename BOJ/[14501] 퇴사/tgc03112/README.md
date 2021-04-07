# [14501] 퇴사

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	//퇴사

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] T = new int[N+1];		//상담 완료까지걸리는 기간
		int[] P = new int[N+1];		//상담했을 때 받을 수 있는 금액 
		int[] dp = new int[N+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			T[i]=Integer.parseInt(st.nextToken());
			P[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			if(i+T[i]<=N) {	//일을 할 때(일을 할 수 있을 때)
				dp[i+T[i]]=Math.max(dp[i]+P[i], dp[i+T[i]]);
			}	//일을 안할 때 (i+1)날로 갈 때 
			dp[i+1]=Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[N]);
	}
}

```

## 문제 풀이
dp로 문제를 풀었습니다..짧은 코드와는 다르게 상당히..난해합니다^^

i번째 날에 일을 [하는경우] 와 [안하는경우] 를 생각해야합니다.

일을 하는경우 즉, 할 수 있는경우는 일을 했을때 쉬는날을 포함한 그 날짜가 최대 퇴사 N의 날을 넘으면 안됩니다.

1.일을 한다면 일을 했을때 (쉬는날을 다 건너뛰고) 도착한? 그 날짜에 이미 있는 값(다른 어떤 경우를 통해 저장된 값)과 지금 경우에 일을 해서 벌어갈 돈을 비교해줘야합니다. 그 중 큰 값을 저장해줍니다.

2.일을 안한다면 다음날(i+1)로 그대로 가는데 현재까지 내가 번 돈 dp[i]에 저장된 값과 다음날 dp[i+1]에 이미 있는 값을 비교해줍니다. 오늘까지 일한 돈, dp[i]의 값을 dp[i+1]에 그대로 가지고 가면 됩니다.(오늘 일을 안했다고 그 전에 일한 돈을 다 버릴 필요는 없으니까?) 그대로 가지고 가되 dp[i+1]에 있는 돈이 더 크다면?(다른 어떤 경우를 통해 저장된 돈) dp[i+1]의 값은 그대로 dp[i+1]로 놔둡니다.(다른 어떤 경우일지 모르는 그 경우가 ? 더 돈을 많이 벌 수 있는 유리한 상황이기 떄문)

글로적으니까 어렵네요 .. 전 그림 그려가며 풀었습니다 !!!!