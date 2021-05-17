# [11660] 구간 합 구하기 5 

## 분류
>


## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660_구간_합_구하기_5 { // 210517
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 표의 크기
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		int[][] map = new int[N+1][N+1];
		int[][] D = new int[N+1][N+1]; // (1,1) 부터 (i,j)까지 합을 각각 저장해놓은 2차원 배열
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				D[i][j] = D[i-1][j] + D[i][j-1] - D[i-1][j-1] + map[i][j];
				// 현재 D의 값은, 위의 사각형 + 왼쪽의 사각형 - 두 번 겹쳐진 사각형 + 현재 값. 그림으로 이해하면 편함!
			}
		}
		
		for(int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = D[x2][y2] - D[x1-1][y2] - D[x2][y1-1] + D[x1-1][y1-1];
			// 내가 찾고자하는 (x1,y1) ~ (x2, y2)까지의 누적합은,
			// (1,1)~(x2,y2)까지의 누적합 - (1,1)~(x1-1,y2)까지의 누적합 - (1,1)~(x2,y1-1)까지의 누적합 + (1,1)~(x1-1,y1-1)까지의 누적합
			
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
	}
}
```

## 문제 풀이
- 단순하게 매번 (x1,y1) ~ (x2,y2)까지 for문을 돌려서 답을 구하면 시간초과가 나기 때문에, DP로 풀어야 하는 문제입니다.
- DP니까 각 2차원 배열에 누적된 값을 저장해야하고, 이 저장된 값을 다음 값을 구할 때 써야하므로 어떻게 잡아야하나 고민하다가, 각 배열의 값은 모두 (1,1)~(i, j)의 구간합으로 이루어지도록 계산해서 저장했습니다.
- 그림으로 그리면 제일 편한데, 식이라 조금 어렵게 느껴질 수도 있습니다. 그림에 (1,1) (x1, y1) (x2, y2)를 표시해놓고 사각형 모양을 어떻게 빼면 각 배열의 값을 구할 수 있는지 그려보면 어렵지 않습니다.
- 그림으로 표현하자면 현재 D의 값은, 위의 사각형 + 왼쪽의 사각형 - 두 번 겹쳐진 사각형 + 현재 값이 됩니다.
- 그렇게 누적으로 D의 값을 다 구했다면, 구하고자 하는 (x1, y1) ~ (x2, y2)의 값 또한 그림으로 생각하면 똑같이 풀립니다.
내가 찾고자하는 (x1,y1) ~ (x2, y2)까지의 누적합은,  (1,1)~(x2,y2)까지의 누적합 - (1,1)~(x1-1,y2)까지의 누적합 - (1,1)~(x2,y1-1)까지의 누적합 + (1,1)~(x1-1,y1-1)까지의 누적합과 같기 때문입니다.