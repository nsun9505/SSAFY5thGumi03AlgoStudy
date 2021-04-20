# [1654] 랜선 자르기

## 분류
> 이분탐색
>
> 파라메트릭 서치

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int K,N; // 가지고 있는 랜선의 개수, 원하는 랜선의 개수
	static long max = 0;
	static int[] lan;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lan = new int[K];
		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			max = max > lan[i] ? max : lan[i]; // 랜선 중 가장 긴 랜선
		}
		
		bw.write(String.valueOf(search()));
		bw.flush();
		bw.close();
		br.close();
	}
	private static long search() {
		long start = 1; // 나누려면 1부터 시작해야함
		long end = max; // 가장 긴 랜선

		while(start <= end) { // start가 end보다 작아 질 때 까지 반복
			long mid = (start + end) /2; // mid 값, 랜선 자를 길이
			long cnt = cut(mid); // 랜선 자르고 나올 개수

			if(cnt >= N) { // 자르고 난 랜선의 개수가 원하는 개수보다 많거나 같다면
				start = mid +1; // 자를 랜선의 크기를 높여 랜선 개수를 줄임

			}else { // 자르고 난 랜선의 개수가 원하는 개수보다 작다면
				end = mid-1; // 자를 랜선의 범위를 줄여 랜선 개수를 늘림
			}
		}
		return end; 
	}
	private static long cut(long mid) {
		long cnt = 0;
		for (int l : lan) {
			cnt += l/mid; // 해당 랜선을 나누면 개수가 나옴
		}
		return cnt;
	}


}

```

## 문제풀이

- K개의 랜선을 N개의 랜선으로 만들 수 있는 랜선의 최대 길이를 구하는 문제입니다.

- 랜선의 최대 길이는 2^31-1이기 때문에 1부터 최대길이까지 다 잘라보는 방식으로 풀면 시간초과가 날 것이기에 `이분탐색`과 `long` 타입을 이용해야합니다.

- 또한 주어진 일련의 값들 중이 아닌 주어진 범위 내에서 원하는 값, 원하는 조건에 가장 일치하는 값을 찾아내야 하기 때문에 `매개변수 탐색(Parametric Search)` 을 이용해야합니다.

- 탐색

  - 탐색할 범위는 1~max(2^31-1)가 됩니다.

    - 1부터 시작하는 이유는 0은 나누기가 불가하기 때문입니다.
    - max값은 주어진 값 중 가장 큰 값이 됩니다. 하지만 랜선의 최대 길이가 주어지기 때문에 최대 길이로 지정해도 됩니다.

  - 탐색은 start 점이 end 점보다 작거나 같을 때 까지만 합니다.

    - start점이 end점보다 커지게 되면 탐색불가. 

  - mid 값은 (start + end ) /2 가 되고 이 값은 랜선을 자를 길이가 됩니다.

  - mid의 길이만큼 해당 랜선(lan[i])를 나누면 자르고 난 랜선의 개수가 됩니다.

    - ```java
      for (int l : lan) cnt += l/mid; // 해당 랜선을 나누면 개수가 나옴
      ```

  - 자르고 난 랜선의 총 개수  >= 원하는 랜선의 개수 (cnt >= N) 일 경우

    - 자를 랜선의 길이를 늘려 랜선의 개수를 줄입니다.

    - ```java
      start = mid +1;
      ```

  - 자르고 난 랜선의 총 개수 < 원하는 랜선의 개수 (cnt < N)일 경우

    - 자를 랜선의 길이를 줄여 랜선의 개수를 늘립니다.

    - ```java
      end = mid-1;
      ```

  - 탐색이 종료되고 end값이 답이 됩니다.

