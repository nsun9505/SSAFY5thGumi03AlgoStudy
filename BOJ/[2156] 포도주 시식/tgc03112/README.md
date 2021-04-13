# [2156] 포도주 시식

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		if(N==1) {
			System.out.println(arr[0]);
			System.exit(0);
		}
		if(N==2) {	//처리안해주면 런타임에러남
			System.out.println(arr[0]+arr[1]);
			System.exit(0);
		}
		dp[0]=arr[0];
		dp[1]=arr[0]+arr[1];
		
		dp[2]=Math.max(dp[1], dp[0]+arr[2]);
		dp[2]=Math.max(dp[2], arr[1]+arr[2]);	//index2 -> 세번째는 0.1.2 중에서 처리
		
		for(int i=3;i<N;i++) {
			dp[i]=Math.max(dp[i-2]+arr[i], dp[i-1]);
			dp[i]=Math.max(dp[i], dp[i-3]+arr[i-1]+arr[i]);	
		}

		System.out.println(dp[N-1]);
	}
}
```

## 문제 풀이
제출할 때 N이 1과 2일 때 처리 안해줘서 에러났습니다.

1 2 3 세 개가 있을 때 1 2 먹거나 2 3 먹거나 1 3 먹거나 세가지 경우중 가장 큰 경우를 dp에 넘겨주면 됩니다.