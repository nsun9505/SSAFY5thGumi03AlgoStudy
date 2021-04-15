# [1920] 수찾기

## 분류
> 이분탐색

## 코드
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_수_찾기 { // 210415
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] numN = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numN[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] numM = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			numM[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색을 위해 오름차순 정렬
		Arrays.sort(numN);
		
		for(int i = 0; i < M; i++) {
			int start = 0, end = N-1;
			while(start <= end) {
				int middle = (start + end) / 2;
				
				if (numM[i] < numN[middle]) { // 찾으려는 숫자보다 middle이 크다면
					end = middle - 1;
				} else if (numM[i] > numN[middle]){ // 찾으려는 숫자보다 middle이 작다면
					start = middle + 1;
				} else { // 숫자를 찾았다면
					sb.append("1\n");
					break;
				}
			}
			if(start > end) {
				sb.append("0\n");
			}
		}
		System.out.println(sb.toString());
	}
}

```

## 문제 풀이
- 이분탐색 문제입니다. 이분탐색은 오름차순으로 정렬되어 있을 때 검색하기 빠른 방법인데, 개념도 그렇게 어렵진 않습니다.
- 검색할 N 배열에서 시작과 끝 범위를 잡은 후 그 중간 값을 찾아, 찾으려는 숫자가 그 중간 값보다 큰지 작은지를 판단해서 검색 범위를 조정해주는 것만하면 끝입니다.
- if문은 3개인데, 찾으려는 숫자보다 middle이 크면 검색의 끝 범위를 줄여주고, 찾으려는 숫자보다 middle이 작으면 검색의 시작 범위를 늘려주고, 두 수가 같다면 검색이 성공한 것입니다. 단, 시작 범위가 끝 범위보다 작은 동안 도는 while문을 다 벗어났는데 시작 범위가 끝 범위보다 큰 상태라면 숫자를 찾지 못한 상태이므로 0을 반환해줍니다.