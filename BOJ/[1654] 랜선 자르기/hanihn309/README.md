# [1654] 랜선 자르기

## 분류
> 이분탐색
>
> 파라메트릭 서치

## 코드
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654_랜선_자르기 { // 210421
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
		int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
		int[] LAN = new int[K];
		
		for(int i = 0; i < K; i++) {
			LAN[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(LAN);
		
		long max = LAN[K-1]; // 가장 긴 랜선의 길이를 max 길이값으로 초기화
		long min = 1; // 랜선의 길이는 자연수이기 때문에 min 길이값은 1로 초기화
		long middle = 0;
		
		while(min <= max) {
			int count = 0;
			
			middle = (min + max) / 2; // middle 길이로 LAN선을 잘라봄
			
			for(int i = 0; i < K; i++) {
				count += (LAN[i] / middle); // 각 LAN선별로 잘린 LAN선의 개수를 counting
			}
			
			if(count >= N) { // N개를 만들 수 있는 랜선의 최대 길이를 구하는 문제이므로, ==로 break 걸지 않고 middle(자를 랜선의 길이) 길이를 늘려서 계속 검사해 봄
				min = middle + 1; // min을 늘리면 middle도 늘어남
			} else if (count < N) {
				max = middle - 1; // max를 줄이면 middle도 줄어듬
			}
		}
		
		System.out.println(max); // while문을 벗어난 순간은 min과 max가 교차된 순간이므로, max값이 LAN선의 최대 길이
	}
}

```

## 문제풀이
- 이분탐색 문제이고, 나무 자르기와 비슷한 것 같습니다.
- LAN선의 길이를 얼마로 잘라야 최소 N개의 랜선이 나올지를 구하는 문제입니다. 자를 LAN선의 길이의 기준을 잡기 위해, LAN선 길이를 오름차순으로 해준 후, middle = (1 + 가장 긴 LAN선의 길이) / 2를 해서 자를 LAN선의 길이의 초기 값을 잡아주었습니다.
- 그리고 이제 잘라보면서 판단을 하는데, 각 LAN선을 middle길이로 나눈 몫이 잘린 LAN선의 길이이므로, 나눈 몫을 counting해주고 그 값이 N개보다 작은지 큰지 판단했습니다. N개를 만들 수 있는 랜선의 최대 길이를 구하는 문제이므로, count가 N과 같을 때 break를 걸지 않고, 크거나 같을때 모두 min 값을 늘려가며 middle(자를 LAN선의 길이)를 조금씩 늘려가며 최대 길이를 찾아줍니다.
- 그리고 while문은 min은 늘고 max는 줄어 교차된 순간 벗어났을 것이므로, max값이 LAN선의 최대 길이가 됩니다.