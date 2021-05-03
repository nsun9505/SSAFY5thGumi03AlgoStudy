# [14938] 서강그라운드

## 분류
> 그래프 이론
>
> 다익스트라
>
> 플로이드-와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][n];
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<n;i++) {
			Arrays.fill(map[i], 9999);
			map[i][i]=0;
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int dis = Integer.parseInt(st.nextToken());
			
			map[start][end] = dis;
			map[end][start] = dis;
		}

		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				if(k==i) continue;
				for(int j=0;j<n;j++) {
					if(j==k || j==i) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		int max = 0;
		int sum = 0;
		
		for(int i=0;i<n;i++) {
			sum = 0;
			for(int j=0;j<n;j++) {
				if(m>=map[i][j]) {
					sum+=arr[j];
				}
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}
```

## 문제풀이
플로이드 와샬로 풀었습니다.

map의 모든 값을 큰 수(9999)로 지정해놓고 플로이드 와샬을 돌려서 경유했을때 갈 수 있는 거리로 세팅해줍니다.

그다음 for문을 돌면서 map[i][j]가 갈 수 있는 거리인 m이하이면 sum에 누적해줍니다.

한 정점마다 sum의 최대값을 max에 저장합니다.