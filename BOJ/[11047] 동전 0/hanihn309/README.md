# [11047] 동전 0

## 분류
> 그리디 

## 코드
```java
package bkj_11047_동전_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 동전 종류의 개수
		int K = Integer.parseInt(st.nextToken()); // 가치의 합
		
		int[] coin = new int[N]; // 각 동전의 가치를 저장하는 배열
		int limit = N; // 만들고자 하는 금액보다 초과되는 동전부터는 검사 안해도 되니까 그 떄의 인덱스 찾아주기
		
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			
			if (coin[i] > K)
				limit = i;
		}
		
		int[] D = new int[K+1]; // 각 금액에 대한 최소 동전의 개수
		
		for(int i = 1; i <= K; i++) {
			int min = Integer.MAX_VALUE;
			
			for(int j = 0; j < limit; j++) { // K원보다 적은 금액의 동전의 개수만큼 for문을 돌면서
				if(i >= coin[j] && D[i-coin[j]] + 1 < min) // 각 금액을 만들 수 있는 최소 동전의 개수 구하기
					min = D[i-coin[j]] + 1;
			}
			
			D[i] = min; // min 값을 다 찾으면 그 때 넣어주기
		}
		System.out.println(D[K]);
	}
}

```

## 문제풀이
- 거스름돈 문제와 똑같기 때문에 어렵진 않습니다.
- 만들고자 하는 돈은 K원이고, 가지고 있는 동전은 N개이며 각각 가치는 주어졌습니다. 그렇다면 각 동전의 가치를 저장하는 배열 coin[ ]을 만들어주고, 각 금액에 해당하는 최소 동전의 개수를 저장하는 배열 D[ ]도 만들어 주었습니다.
- 또한 차이는 가지고 있는 동전의 금액 중에, K원을 초과하는 금액으론 K원을 만들 수 없기 때문에 for문을 돌릴 때 제한 범위로 잡아주기 위해 limit라는 변수를 만들어 저장해주었습니다.
- D[ ]에 하나씩 검사하며 넣기 위해, 1원부터 K원까지 for문을 돌고, 동전도 limit까지 for문을 돌면서 해당 금액으로 만들 수 있는 최소 동전의 개수를 구해주며 min값을 갱신해나갑니다. 
- D[i-coin[j]] + 1이라는 값의 의미는 현재 i원에서, coin[j]원을 하나 뽑은 후 남은 금액에 해당하는 D[ ]값은 이미 이전에서 검사해놓은 최소 동전의 개수이므로, 그 개수에 coin[j]원을 1개 더 뽑아 만든다는 의미입니다.
- 그렇게 전부 검사하고 나면, min값에 최소 동전 개수가 저장되어 있기 때문에, D[i] 값에 넣어주면 됩니다.