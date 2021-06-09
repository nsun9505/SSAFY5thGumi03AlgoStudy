# [14719] 빗물

## 분류
>

## 코드
```java
package BOJ14719_빗물;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int H,W;
	static int[][] arr;
	static int res = 0;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		arr = new int[H][W];
		st = new StringTokenizer(br.readLine());
		
		// 블록이 있는 곳은 0으로 빗물이 있는 곳은 1로 채운다.
		for(int i = 0; i < W; i++) {
			int k = H-Integer.parseInt(st.nextToken());
			for(int j = 0; j < k; j++) {
				arr[j][i] = 1;
			}
		}
		
		// 0과 0 사이에 있는 곳은 빗물이 차있는 곳만 더해주면 된다
		for(int i =0; i < H; i++) {
			int zero = -1; // 벽이 있는 배열 인덱스를 저장해주는 변수
			
			for(int j = 0; j < W; j++) {
				if(arr[i][j] == 0) { // 0일때
					if(zero != -1) res+= j - zero - 1; // -1, 처음 0이 아니면 해당 위치의 0과 이전위치의 0을 빼고 -1해준다
					
					zero = j; // 해당 위치로 바꾸어준다.
					
				}
			}
					
		}
		
		bw.write(String.valueOf(res));
		bw.flush();
		bw.close();
		br.close();

	}



}

```

## 문제풀이

- 블럭에 둘러 쌓인 곳에만 빗물이 차므로 0(블럭)으로 둘러 쌓인 곳을 찾아 둘러 쌓인 길이 만큼 더해주어 문제를 해결했습니다.
- 이차원 배열에 블럭을 제외한 곳을 1로 채웁니다.(블럭은 0)
- 이차원 배열의 1열씩 검사를 합니다.
  - 0을 발견하면 해당 0의 위치(**j**)와 이전 0의 위치(**zero**)를 빼주고 -1을 해줍니다.
    - zero = 1, j = 3 이면 3 - 1  = 2 이 되는데 현재 0의 위치는 빼주어야하므로 -1을 해줍니다.
  - 해당 값을 res에 더해줍니다. 이것을 W만큼 반복해줍니다.