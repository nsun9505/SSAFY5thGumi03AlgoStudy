import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938_서강그라운드 { // 210430
	static int n, m, r;
	static int[] items;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 지역의 개수
		m = Integer.parseInt(st.nextToken()); // 예은이의 수색 범위
		r = Integer.parseInt(st.nextToken()); // 길의 개수
		
		items = new int[n+1]; // 각 지역의 아이템 개수 저장 배열
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new int[n+1][n+1];
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			map[start][end] = distance;
			map[end][start] = distance; // 양방향이므로 start->end, end->start 모두 동일한 distance 값을 가짐
		}
		
		floyd(); // 모든 지역에서 모든 지역으로의 최단 경로를 저장해놓고 한 번에 비교하기 위해 플로이드-와샬 알고리즘 사용ㄴ
		
		int max = 0; // 각 지역을 기준으로 수색 범위 내의 아이템 수를 모두 더하는 counting 변수
		
		for(int i = 1; i <= n; i++) { // i번째 지역에서 출발했을 때, j번째 지역까지의 이동 거리가 수색 범위 내인 지역을 찾아냄
			int count = 0;
			
			for(int j = 1; j <= n; j++) {
				if(map[i][j] <= m) // i -> j 이동 거리가 예은이의 수색 범위 내라면
					count += items[j]; // 각 지역의 아이템 수를 저장
			}
			max = Math.max(max, count); // 최댓값 갱신
		}
		
		System.out.println(max);
	}

	private static void floyd() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i != j && map[i][j] == 0) // 인접해있지 않은 지역이라면 최솟값 갱신을 위해 최댓값으로 저장해놓기
					map[i][j] = 999;
			}
		}
		
		for(int k = 1; k <= n; k++) { // 경유지
			for(int i = 1; i <= n; i++) { // 출발지
				if(k == i) // 경유지 == 출발지라면 다음 출발지 선택
					continue;
				for(int j = 1; j <= n; j++) { // 도착지
					if(i == j || k == j) // 출발지 == 도착지 또는 경유지 == 도착지라면 다음 선택지
						continue;
					
					if(map[i][j] > map[i][k] + map[k][j]) // 저장된 최소 거리보다 k라는 경유지를 거쳐서 i->j로의 이동이 더 짧다면 갱신
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
	}
}