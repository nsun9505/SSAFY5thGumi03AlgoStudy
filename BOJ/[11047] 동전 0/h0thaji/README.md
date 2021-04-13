# [11047] 동전 0

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

	static int N,K,res; // 동전의 종류, 가치의 합, 동전 개수의 최소값
	static int[] coin; // 입력받을 동전 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		for (int i = N-1; i > -1; i--) { // 금액이 큰 동전부터 찾기위해 입력받은 값을 거꾸로 배열에 담아줌
			// 동전은 작은 금액부터 입력이 된다.
			coin[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N && K!=0; i++) { // 가치가 큰 동전 부터 작은 동전 순, K가 0이면 동전 개수의 최소값을 찾은 것이기 때문에 반복문 종료
			if(K >= coin[i]) {	// K가 해당 동전보다 크거나 같으면 나눌 수 있으므로
				res += K/coin[i]; // 결과값에 동전의 수를 더해줌
				K %= coin[i]; // 해당 동전의 가치로 나누고 나머지가 총 가치가 됨			
			}
		}
		
		sb.append(res);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}

```

## 문제풀이

- 입력받은 동전의 종류로 K만큼의 가치의 합을 만들려고 하는데 이때 필요한 동전 개수의 최솟값을 구하는 문제입니다.
- 동전의 개수를 최소화 시키려면 입력받은 동전의 종류를 큰 가치의 순으로 나누어 동전의 개수를 최소화 시킬 수 있습니다. K가 0이되면 동전 개수의 최솟값을 찾았기 때문에 반복문 종료.
  - 입력받을 동전의 종류는 가치가 작은 순으로 입력 받기때문에 입력받을 동전을 저장할 배열에는 가치가 큰 순으로 저장해줍니다.
  - K(만들려고하는 가치의 합)의 크기가 해당 동전의 가치(coin[i])보다 크거나 같다면 해당 동전으로 만들 수 있으므로 만들 수 있는 동전의 수를 구합니다.
  - 동전의 수를 구하면 해당 동전으로 만들 수 있는 가치를 K에서 빼줍니다.
    - 해당 동전으로 K를 나누고 난 나머지가 K가 됩니다. (K %= coin[i])