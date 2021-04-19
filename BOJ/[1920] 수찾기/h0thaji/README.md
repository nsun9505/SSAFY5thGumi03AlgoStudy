# [1920] 수찾기

## 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, x; 
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); // 정렬
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) { // M만큼 반복
			x = Integer.parseInt(st.nextToken());
			
			int start = 0;
			int end = N-1;
			boolean check = false; // 해당값이 있는 지 없는 지 판단해줄 boolean
			while(start <= end) { // start의 범위가 end보다 작거나 같을 때 반복
				int mid = (start+end) / 2; // mid 값은 start와 end의 가운데 값이 되어야 하므로
				
				if(arr[mid] < x) start = mid+1; // 찾을 값이 해당 mid값 보다 크다면 start를 mid+1 변경
				else if(arr[mid] > x) end = mid - 1; // 찾을 값이 해당 mid값 보다 작다면 end를 mid-1로 변경
				else { // 같다면 해당 값이 있다는 것이므로 check = true;
					check = true;
					break;
				}

			}
			
			if(check) sb.append("1").append("\n"); // 있다면 1 출력
			else sb.append("0").append("\n"); // 없다면 0 출력
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}


```

## 문제 풀이

- N개의 수를 A배열(arr[N])에 입력받아 A배열에 입력받을 M개의 수 x가 있는지 없는지 확인하는 문제입니다.

- 단순 비교를 하여 탐색할 경우 시간초과를 벗어날 수 없으므로 이분탐색이 필요합니다.

  - 단, 배열 A(arr)가 오름차순으로 정렬이 되어있어야합니다.
  - 즉, NlogN의 시간복잡도만 필요합니다.

- 이분탐색

  - 첫 범위는 모든 범위에서 시작됩니다.

    - start = 0, end = N-1, mid = (start+end) / 2

  - 해당 mid의 값 (arr[mid])이 찾을 값(x) 보다 작거나 크다면 찾을 범위를 줄입니다.

    - ```java
      if(arr[mid] < x) start = mid + 1;
      // 찾을 값(x)이 해당 mid값(arr[mid]) 보다 크다면 start를 mid+1 변경
      ```

    - ```java
      if(arr[mid] > x) end = mid - 1;
      // 찾을 값(x)이 해당 mid값(arr[mid]) end를 mid-1로 변경
      ```

  - 값(x)을 찾거나 모든 범위 탐색이 끝날 때 (start <= end) 까지 범위를 반으로 쪼개면서 탐색을 합니다.

    - 해당 mid값이(arr[mid])이 찾을 값(x)와 같다면 A배열(arr)에 x값이 있다는 뜻이므로 이분탐색을 종료 합니다.

    