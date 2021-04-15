# [2508] 나무 자르기

## 분류
> 이분탐색

## 코드
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805_나무_자르기 { // 210415
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 나무의 수
		int M = Integer.parseInt(st.nextToken()); // 집으로 가져가려는 나무의 길이

		int[] tree = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(tree); // 오름차순 정렬

		int start = 0, end = tree[N-1]; // 절단기 높이의 시작점은 0, 끝 점은 제일 높은 나무 높이

		while(start <= end) {
			int middle = (start + end) / 2; // 중간 길이의 나무의 높이로 절단기를 설정해봄

			long sum = 0; // 잘린 나무 높이의 합들이 int 범위 넘어갈 수도 있으므로 long으로 선언

			for(int j = 0; j < N; j++) { // middle 높이보다 낮은 나무는 잘리지 않으니, middle보다 큰 애부터 계산
				if(tree[j] > middle)
					sum += (tree[j] - middle);
			}

			if(sum > M) { // 찾으려는 나무의 길이가 M보다 크다면 높이의 최댓값이니까  절단기 높이를 높여야 함
				start = middle + 1;
			} else if(sum < M){ // 찾으려는 나무의 길이가 M보다 작다면 절단기 높이를 줄여야 함
				end = middle - 1;
			} else { // 찾으려는 나무의 길이가 M과 같다면 더 이상 검색하지 않고 middle 출력하고 종료
				System.out.println(middle);
				return;
			}
		}
		
		// start와 end가 교차되어 종료된 상태이므로, end값 출력하면 됨
		System.out.println(end);
	}
}

```

## 문제풀이
- 살짝 응용된 이분탐색 문제입니다. 물론 첨에 만만하게 보고 완탐처럼 풀었다가 시간 초과 났습니다..
- 여기서 이분탐색을 나무의 높이를 기준으로 하는 것이 아니라, 절단기의 높이를 잡을 때 사용합니다.
- 실수한 포인트 : 절단기의 높이를 꼭 나무의 높이로 설정할 필요 없다
- 제일 먼저 기준이 되는 절단기의 높이는, 제일 큰 나무 길이의 절반 정도로 잡아서 판단합니다. 그 때 잘린 나무들의 길이의 합을 판단해서, 잘린 나무의 길이 > M이라면 너무 많이 잘린것이기 때문에 절단기의 높이를 더 높여야 합니다. 절단기의 높이를 구할 때 사용되는 시작 범위를 middle 범위보다 크게 잡아줍니다. 잘린 나무의 길이 < M이라면 너무 적게 잘린 것이기 때문에 절단기의 높이를 더 줄여야 합니다. 이때는 끝 범위를 middle보다 작게 잡아줍니다. 잘린 나무의 길이 == M이라면 원하는 값을 찾은 상태이므로, middle 값을 출력하고 종료하면 됩니다.
- 잘린 나무의 길이를 정확히 찾지 못하고 while문이 종료된 경우는, 찾으려는 나무의 길이가 M보다 작은 상태라 절단기의 높이를 줄여야 해서 end를 줄인 후 start와 end가 교차된 후 종료된 상태이므로 end값을 출력해주면 됩니다.
- 그리고 잘린 나무 높이들의 합이 int 범위를 넘어설 수 있기 때문에, long으로 선언해줍니다.