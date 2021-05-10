# [2293] 동전 1

## 분류

> 다이나믹 프로그래밍

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] arr = new int[n+1];
		int[] dp = new int[k+1];
		dp[0] = 1;

		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			arr[i]=Integer.parseInt(st.nextToken());
		}

		for(int i=1;i<=n;i++) {
			for(int j=1;j<=k;j++) {
				if(j>=arr[i])
					dp[j] += dp[j-arr[i]];
			}
		}
		System.out.println(dp[k]);
	}
}
```

## 문제풀이

dp문제입니다 .. dp[0]=1이라는 초기값이 필요합니다. 만약 arr[i] 가 2인데 j가 2라면 0일때 아무 경우도 없는 상태를 포함하는 경우 또한 들어가야 됩니다.

가장 작은 동전부터 사용해서 경우의 수를 계산해주고 그 다음 동전을 사용 했을 경우 그 경우의 수를 누적하는 방식으로 진행하면 됩니다

검색을 통해 1차원 배열을 그려가면서 해결했습니다 ~~!
