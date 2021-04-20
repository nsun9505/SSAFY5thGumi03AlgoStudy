# [2508] 나무 자르기

## 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,M, max = 0; // 나무의 수 , 필요한 나무 길이, N개의 나무 중 가장 긴 나무 길이
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			max=Math.max(max, tree[i]); // 나무 중 가장 큰 놈
		}

		bw.write(String.valueOf(search()));
		bw.flush();
		bw.close();
		br.close();
	}
	private static int search() { // 이분탐색
		int start = 0;
		int end = max;
		int res = 0;
		while(start <= end) { // start의 값이 end값보다 작거나 같을 때 까지 반복
			int mid = (start + end) / 2; // mid 값
			long sum = 0; // 자른 나무길이 더해줄 변수
			
			for (int i = 0; i < N; i++) { // 자른 나무길이 더하기
				if(mid < tree[i]) { // mid보다 큰 놈만 자를 수 있음
					sum += tree[i] - mid; // 잘라서 총 길이 구해줌
				}
			}
			
			if(sum == M) return mid; // 원하는 길이만큼만 가져갈 것이니 같다면 mid가 답
			else if(sum > M) { // 자른 총 길이보다 원하는 길이보다 크다면
				start = mid+1; // start는 mid+1
				res = Math.max(res, mid); // 결과값은 원하는 길이만큼 가져가면 좋겠지만 원하는 길이가 정확히 떨어지지 않을 수 있으니
				// 그 중에서 mid값이 가장 큰 값

			}
			else end = mid-1; // 자른 길이가 원하는 길이보다 작다면
			
		}
		return res;
	}

}


```

## 문제풀이

- N개의 나무를 같은 길이로 잘라서 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최대값을 구하는 문제입니다.

- 주어지는 N개의 범위가 1,000,000, 까지 이므로 단순 탐색으로는 구할 수 가 없고 `이분탐색` 을 이용합니다.

- 또한 입력할 수 있는 나무의 높이는 최대 1,000,000,000 이므로 자른 나무의 길이 합이 int의 범위를 벗어날 수 도 있으므로 `long` 타입을 사용해야합니다. (int의 범위 -2,147,483,648 ~ 2,147,483,648)

- 나무를 자를 수 있는 최대 범위는 입력받은 값 중 가장 큰 값이므로 입력받을 때 max값을 구해주어 첫 탐색에서의 end값으로 지정해주었습니다.

  - N의 범위가 주어지기 때문에 max값을 굳이 안 구해줘도 됨.

- 이분탐색

  - 0~ 나무의 최대 크기가 탐색할 총 범위가 됩니다.

    - start = 0, end = max, mid = (start + end) / 2

  - mid의 값이 `절단기의 높이`가 되며 나무를 잘라서 `자른 나무의 길이`(sum)를 구해줍니다.

    - 절단기 보다 낮은 나무는 자를 수 없으므로 if문으로 걸러주었습니다.

    - ```java
      // mid보다 큰 놈만 자를 수 있음
      if(mid < tree[i]) sum += tree[i] - mid; // 잘라서 총 길이 구해줌
      ```

  - 자른 나무의 길이 == 필요한 나무길이 (sum == M) 일 경우

    - 원하는 길이만큼만 가져가면 되니 mid가 답이 됩니다.

    - ```java
      if(sum == M) return mid;
      ```

  - 자른 나무의 길이 > 필요한 나무길이 (sum > M) 일 경우

    - 자른 나무의 길이가 더 크므로 절단기의 높이를 높여줘야합니다.

      ```java
      start = mid+1;
      ```

    - 또한 원하는 길이만큼만 가져가면 좋겠지만 원하는 길이가 정확히 떨어지지 않을 수 있고 필요한 만큼만 집으로 가져가기 때문에 절단기의 높이가 가장 높았을 때 최소한의 나무를 가져갈 수 있습니다.

      ```java
      res = Math.max(res, mid);
      ```

  - 자른 나무의 길이 < 필요한 나무길이(sum < M) 일 경우

    - 자른 나무의 길이가 필요한 나무길이보다 작으므로 절단기의 높이를 낮춰야합니다.

      ```java
      end = mid-1;
      ```

  - start > end , 탐색이 끝났다면 res값이 답이 되어 리턴해줍니다.