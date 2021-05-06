# [2293] 동전 1

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전_1 {// 210506
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 동전 종류의 개수
		int k = Integer.parseInt(st.nextToken()); // 가치의 합
		
		int[] D = new int[k+1]; // D[i]는 i원의 가치를 만들 수 있는 동전 종류의 조합 개수
		D[0] = 1; // '어떤 종류' 동전 하나를 내는 방법은 1가지니까 1로 초기화
		
		for(int j = 0; j < n; j++) {
			int coin = Integer.parseInt(br.readLine());
			
			for(int i = coin; i <= k; i++) {
				D[i] = D[i] + D[i- coin];
				// coin원으로 i원을 만드는 가짓수 = coin원 동전을 내지 않은 경우 + coin원 동전을 내는 경우
			}
		}
		
		System.out.println(D[k]);
	}

}

```

## 문제풀이
- DP문제입니다. 거스름돈 문제와 비슷하지만 조금 다르더라구요. 거스름돈 문제보다 훨씬 짧고 간단하게 작성 가능합니다 생각만 해내면...
- 거스름돈 문제는 예를 들어 1원 4원 6원이 있다면, 1원을 이용해서 낼 수 있는 경우와 4원, 6원을 이용해서 낼 수 있는 각각의 경우가 최솟값이라면 그때 갱신해가며 탐색합니다.
- 이 문제는 모든 경우의 수를 누적해가는 문제이므로, 일단 큰 단위의 동전으로 작은 단위의 동전을 만들 수 없으므로 상향식으로 찾아내야 하기 때문에 1차원 D[ ] 배열을 선언해 가짓수를 누적해나가면 됩니다.
- 현재 들고있는 동전 coin원을 기준으로, coin원부터 만들고자 하는 k원까지 가짓수를 찾아가며 누적해갑니다. coin원으로 i원을 만드는 가짓수는 coin원을 내는 경우와 내지 않는 경우가 있으므로, coin원을 내지 않는 것은 현재 coin원 이전까지 누적된 가짓수와 똑같습니다. 그리고 coin원을 내는 경우는, coin원을 내고 남은 나머지 i-coin원을 만들 수 있는 가짓수(이전까지 누적되어 저장되어 있음)입니다. 두 경우를 더해나가는 과정을 반복하면, 마지막 k원일 때 총 가짓수가 저장되게 됩니다.
- 생각해내기 .. 쉬운 듯 어려워서 구글링 도움 ^^~ 
