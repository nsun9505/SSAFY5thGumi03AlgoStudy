# [1806] 부분합

## 분류
> 투포인트

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 { // 210421

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		long S = Long.parseLong(st.nextToken()); // 부분합이 S
		
		int[] array = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = 0, result = 0;
		int min = N+1;
		
		while(true) {
			if(result >= S) {
				result -= array[left];
				min = Math.min(min, right - left);
				left++;
			} else if (right == N) {
				break;
			} else {
				result += array[right];
				right++;
			}
		}
		
		if(min == N+1)
			System.out.println(0);
		else
			System.out.println(min);
	}
}
```

## 문제 풀이
- 왜 틀렸는지 몰겠지만 많이 틀렸슴니다..
- 투 포인터 문제라 개념은 어렵지 않습니다. left와 right 변수를 주고, left부터 right까지의 합이 S보다 크거나 같으면 합에서 left값 하나를 빼준 후, 원소 개수를 min 비교하고 left를 하나 늘려줍니다. 합이 S보다 작다면 합에서 right값 하나를 더해준 후, right를 하나 늘려줍니다. 이 비교를 해준 후, right값이 N과 같다면 끝까지 갔다는 의미이므로 while문을 벗어납니다.
- 벗어난 후, min값이 초기값과 같다면 S을 구할 수 없다는 의미이므로 0을 출력해주고, 아니라면 구한 min 값을 출력해줍니다.