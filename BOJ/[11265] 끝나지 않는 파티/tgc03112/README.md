# [11265] 끝나지 않는 파티

## 분류
> 그래프 이론
>
> 플로이드-와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0;k<N;k++) {	//경
			for(int i=0;i<N;i++) {	//출
				if(i==k) continue;
				for(int j=0;j<N;j++) {	//도
					if(j==k || j==i) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			if(map[start-1][end-1]<=time) {
				System.out.println("Enjoy other party");
			}
			else {
				System.out.println("Stay here");
			}
		}
	}
}
```

## 문제풀이
플로이드 와샬로 쉽게 풀 수 있었습니다.

입력을 받고 플로이드 와샬로 모든 경유지를 고려해서 최단거리를 구한 뒤 조건에 맞게 출력문을 바로바로 출력해줬습니다~!