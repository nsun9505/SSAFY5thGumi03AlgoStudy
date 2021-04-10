# [2156] 포도주 시식

## 분류
> 그리디 

## 코드
```java
package bkj_2156_포도주_시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n];
		
		for(int i = 0; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		if (n == 1) {
			 System.out.println(wine[0]);
		} else if (n == 2) {
			System.out.println(wine[0] + wine[1]);
		} else {
			int[] D = new int[n];
			
			D[0] = wine[0]; D[1] = wine[0] + wine[1];

			// 3번째 wine 잔까지 최대 이익 => OOX, XOO, OXO 중 하나
			D[2] = Math.max(D[1], D[0] + wine[2]);
			D[2] = Math.max(D[2], wine[1] + wine[2]);
			
			// OOX, OXO, XOO
			// 1) OOX : i번째 잔 선택 안할 때 => D[i] = D[i-1]
			// 2) OXO : i번째 잔 선택 할 떄 => D[i] = D[i-2] + wine[i]
			// 3) XOO : i번째 잔 선택 할 떄 => D[i] = D[i-3] + wine[i-1] + wine[i]
			for(int i = 3; i < n; i++) {
				D[i] = Math.max(D[i-1], D[i-2] + wine[i]);
				D[i] = Math.max(D[i], D[i-3] + wine[i-1] + wine[i]);
			}
			System.out.println(D[n-1]);
		}
	}
}
```

## 문제풀이
- 어려워서 구글링해서 풀이 흐름을 좀 익혔습니다. 역시나 DP 문제답게 소스 자체는 짧네요.. 이해하는데 조금 걸렸습니다.
- 일단 연속된 3개의 잔은 선택될 수 없는데 최대로 많이 마실 수 있도록 구한다고 했으니, 연속된 3개 중에서 최대 개수인 2개를 골라내야 합니다. 포도주의 양을 저장할 수 있는 wine[ ] 배열을 생성했고, i번째 잔을 선택할 때 최대 용량을 저장하는 배열 D[ ]도 생성했습니다.
- 연속된 3개의 잔 중에서 2개를 골라내는 경우는 (선택을 O, 비선택을 X로 가정하면) "OOX", "OXO", "XOO"인 3가지 경우가 있습니다. 일반화된 식을 구해내기 전에, 첫번째 잔으로 구할 수 있는 최대 용량은 첫번째 잔을 마시는 용량이므로, D[0] = wine[0]입니다. 두 번째 잔으로 구할 수 있는 최대 용량은 첫 번째 잔과 두 번째 잔을 마시는 용량이므로, D[1] = wine[0] + wine[1]입니다.
- 여기까지 정해놓은 후, 세 번째 잔까지 왔을 때는 (1, 2), (1, 3), (2, 3) 번째 잔을 마시는 3가지 경우가 있으므로, MAX 함수를 이용해 구해줍니다.
- 이제 네 번째 잔부터는 FOR문을 돌며 일반화된 식을 이용해 최대 용량을 구해주면 됩니다. 현재 i (i는 4번째 잔부터 시작) 번째 잔을 선택하는 경우라고 칠 때, i번째 잔을 마시거나 마시지 않는 경우가 있습니다.
 1) i번째 잔을 마시지 않는 경우는, i-1번째까지 고른 최대 용량과 같으므로 D[i] = D[i-1]이 됩니다.
 2) i번째 잔을 마시는 경우는, (i-2, i), (i-3, i-1, i)번째 잔을 마시는 경우가 있습니다. (i-2, i)번째 잔을 마시는 경우는, i-2번까지 마신 최대 용량에, i-1번째 잔은 마시지 않고 i번째 잔을 마셔야 하기 때문에, D[i] = D[i-2] + wine[i]가 됩니다. (i-3, i-1, i)번째 잔을 마시는 경우는, i-3번까지 마신 최대 용량에, i-1번째, i번째 잔을 마시는 경우이므로 D[i] = D[i-3] + wine[i-1] + wine[1]이 됩니다.
- 어차피 D[ ] 배열에 저장된 값은, 각 경우에서 2개까지 마신 경우가 있으므로, 연속된 3개의 포도주가 시음된 경우는 없습니다. 저는 이 부분이 조금 헷갈렸습니다.
- 구해진 3가지 경우의 수를 max로 비교하면 됩니다.
- 마지막으로 주의해야할 점은, 문제에서 포도주 잔의 개수인 n이 1이상이므로 1개일 때는 wine[0]의 값이 나오게, 2개일 때는 wine[0] + wine[1]의 값이 나오도록 설정해주지 않으면 ArrayIndexOutOfBounds Error가 나옵니다...