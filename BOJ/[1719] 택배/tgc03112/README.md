# [1719] 택배

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
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		int[][] path = new int[N+1][N+1];
		
		for(int i=1;i<N+1;i++) {
			Arrays.fill(map[i], 11111);
			map[i][i]=0;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis =  Integer.parseInt(st.nextToken());
			
			map[start][end]=dis;
			map[end][start]=dis;
			
			path[start][end]=start;
			path[end][start]=end;
		}
		
//		for(int i=1;i<N+1;i++) {	//출력확인
//			for(int j=1;j<N+1;j++) {
//				System.out.print(path[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int k=1;k<N+1;k++) {	//경
			for(int i=1;i<N+1;i++) {	//출
				for(int j=1;j<N+1;j++) {	//도
					if(map[i][j]>map[i][k]+map[k][j]) {	//최단경로 갱신될 때
						map[i][j] = map[i][k]+map[k][j];
						path[i][j] = path[k][j];	//경유지 등록해줌
					}	
				}
			}
		}
		
		for(int i=1;i<N+1;i++) {	//출력확인
			for(int j=1;j<N+1;j++) {
				if(i==j)
					System.out.print("- ");
				else
					System.out.print(path[j][i]+" ");
			}
			System.out.println();
		}
	}
}
```

## 문제풀이
2차원 배열을 두 개 만들어서 이동하는 시간을 저장하는 배열 하나, 지나가는 경유지  k 저장하는 배열 하나를 만들어 줍니다.

map에 입력받을 떄 path 배열에는 출발지를 입력해줍니다.

1->2로 연결 되어 있다는것은 최초에 1정점에서 시작해야 2로 갈수있다는것을 의미합니다.

플로이드 와샬을 돌리면서 최단경로가 갱신될 떄 path[i][j] 의 값을 path[k][j] 로 바꿔줍니다. k를 경유하면서 j도착지로 가는 정점을 저장해줍니다.

플로이드 와샬을 모두 돌리고 나면 path를 출력해주는데 이떄 path[j][i]로 출력해줍니다. 최근 경로를 뒤집으면 반대로 맨 처음 가야하는 정점이 출력되기 때문입니다.