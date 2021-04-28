# [11265] 끝나지 않는 파티

## 분류
> 그래프 이론
> 플로이드-와샬

## 코드
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11265_끝나지_않는_파티 {
	static int N, M;
	static int[][] party;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 파티장의 크기
		M = Integer.parseInt(st.nextToken()); // 손님의 수
		
		party = new int[N+1][N+1]; // 파티장의 연결 정보 (이동하는데 걸리는 시간)
		
		for(int i = 1; i <= N; i++) { // 파티장의 연결 정보 저장
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 정점->모든 정점에 대한 최단 경로를 구해놓고 한 번에 모든 손님의 이동 여부를 출력할 것이므로 플로이드-와샬 알고리즘 사용
		floyd(); // current -> next 최단 경로의 가중치합 <= time라면 Enjoy other party, >라면 Stay here
				
		for(int i = 0; i < M; i++) { // 손님의 파티장 이동 정보 저장
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken()); // 현재 손님이 위치한 파티장 번호
			int end = Integer.parseInt(st.nextToken()); // 다음 파티가 열리는 파티장 번호
			int time = Integer.parseInt(st.nextToken()); // 다음 파티가 열리는데 걸리는 시간

			if(party[start][end] <= time) // 현재 위치에서 다음 파티장까지의 최단 시간이 파티가 열리는데 걸리는 시간보다 작거나 같다면 이동 성공
				sb.append("Enjoy other party\n");
			else // 크다면 이동 못함
				sb.append("Stay here\n");
		}
		System.out.println(sb.toString());
	}

	private static void floyd() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j && party[i][j] == 0) // 자기 자신으로의 이동이 아닌데 바로 이동할 수 없는 경우라면 최소 비용 계산을 위해 큰 값으로 초기화
					party[i][j] = 9999999;
			}
		}
		
		for(int k = 1; k <= N; k++) { // 경유지
			for(int i = 1; i <= N; i++) { // 출발지
				if(i == k) // 경유지와 출발지가 같다면 다음 선택지
					continue;
				for(int j = 1; j <= N; j++) { // 도착지
					if(i == j || k == j) // 출발지와 도착지가 같거나, 경유지와 도착지가 같다면 다음 선택지
						continue;
					
					if(party[i][j] > party[i][k] + party[k][j]) // 현재 저장된 최소 비용보다, k를 경유지로한 최소 비용이 더 작다면 갱신
						party[i][j] = party[i][k] + party[k][j];
				}
			}
		}
	}
}

```

## 문제풀이
- 처음에는 손님의 각 정보를 받을 때마다 다익스트라를 돌려야하는건가 생각해서 그렇게 짰다가, 다시보니 파티장의 크기가 500까지니까 플로이드-와샬 알고리즘을 사용해서 마지막에 한번에 손님의 정보를 이용해 출력하는 것이 낫겠다고 판단했습니다.
- 플로이드-와샬은 모든 정점에서 모든 정점으로의 최소 비용으로 도착하는 경우를 다 구하면 되는 알고리즘입니다. 큰 흐름은 어떤 한 정점을 경유지로하여, 모든 경우에서 이 정점을 경유지로 했을 때와 아닐때의 최소비용을 비교해서 update를 해주면 됩니다. 
- 그래서 기본적인 플로이드-와샬 알고리즘으로 작성해서, 마지막에 이 최소비용과 손님에게 주어진 시간 정보를 비교해서 Enjoy other party 또는 Stay here을 출력해주면 됩니다.