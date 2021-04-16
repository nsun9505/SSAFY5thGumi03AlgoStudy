# [2156] 포도주 시식

## 분류
> 그리디 

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N; // 포도주 잔의 개수,
	static int[] wine, dp; // 입력받을 포도주 양의 배열, dp 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		wine = new int[N+1];
		for (int i = 1; i <= N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[N+1];
		
		dp[1] = wine[1]; // 첫 잔마셨을 경우 저장
		if(N > 1) dp[2] = wine[1] + wine[2]; // 포도주의 수가 1이상일때, 두 잔 다 마셨을 경우 저장
		for (int i = 3; i <= N; i++) { // 3잔부터는 앞에 두개를 마셨는지, 하나만 마셨는지 등 경우를 따져서 마신 양의 최대값을 dp에 저장
			dp[i] = Integer.max(dp[i-1], Integer.max(dp[i-2]+wine[i], dp[i-3] + wine[i-1] + wine[i]));
		 }
		
		sb.append(dp[N]); // 마지막 dp배열 값만 출력 -> 정답
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}

```

## 문제풀이

- 해당 문제는 1~n까지의 번호가 붙어 있는 n개의 포도주 잔을 순서대로 일렬로 놓여있으며, 각 포도주 잔에 들어있는 포도주의 양이 주어졌을때, 가장 많은 양의 포도주를 마시는 경우의 수를 찾는 문제입니다. 또한 아래와 같은 규칙이 있습니다.

  1. 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
  2. **연속으로 놓여 있는 3잔을 모두 마실 수 없다.**

  

- 풀이

  - 포도주의 잔이 하나만 존재하는 경우

    - 한잔을 마시는 경우가 최대한 많은 양의 포도주를 마시는 경우입니다.

    - ```java
      dp[1] = wine[1];
      ```

    -  답 => N=1, dp[N]

  - 포도주의 잔이 두 잔이 존재하는 경우

    - 두 잔 다 마시는 경우가 최대한 많은 양의 포도주를 마시는 경우입니다.

    - ```java
      dp[2] = wine[1] + wine[2];
      ```

    - 답 => N = 2, dp[N]

  - 포도주의 잔이 세 잔 이상이 존재하는 경우

    - 3잔을 연속으로 마실 수 없기 때문에 첫번째 잔, 두번째 잔을 마셨는지 안마셨는지 경우를 따져야합니다.

    - 즉, `oox`, `oxo`, `xoo` 경우가 존재 하고 이 중 최대한 많은 양의 포도주를 마시는 경우를 찾아야 합니다.
      - oox (세 잔 중 세번째 잔을 안마신 경우)

        - 두번째 잔까지의 최대한 많이 마신 양 dp[2] (=wine[1]+wine[2])

        - ```java
          dp[i-1] // 세번째 잔을 안마신 경우 (i=3)
          ```

      - oxo(세 잔 중 두번째 잔을 안마신 경우)

        - 첫번 째 잔을 마신 경우 dp[i-2] , 세번 째 잔을 마시면 +wine[i] (i = 3)

        - ```java
          dp[i-2]+wine[i]
          ```

      - xoo(세 잔 중 첫번째 잔을 안마신 경우)

        - 첫번째 잔을 안마셨으니 첫번째 잔 앞에 경우 dp[i-3]

        - 두번째 잔 , 즉 i=3, wine[i-1]

        - 세번째 잔, i = 3 , wind[i]

        - ```java
           dp[i-3] + wine[i-1] + wine[i]
          ```

    - 위 세가지의 경우 중 최대값을 dp[i]에 저장합니다.

    - N까지 반복해서 값을 저장하면 dp[N]번째가 답이 됩니다.



