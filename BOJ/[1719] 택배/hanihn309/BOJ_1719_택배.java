import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1719_택배 { // 210501
	static int n, m;
	static int[][] edge, pass;
	
	private static class Node implements Comparable<Node> { // 간선 정보를 저장하는 클래스
		int vertex;
		int time;

		public Node(int vertex, int time) {
			super();
			this.vertex = vertex;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 집하장의 개수
		m = Integer.parseInt(st.nextToken()); // 집하장 간 경로의 개수
		
		edge = new int[n+1][n+1]; // 집하장 간 연결 정보를 저장하기 위한 배열
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			edge[first][second] = time;
			edge[second][first] = time; // 양방향 이동이 가능하므로 둘 다 저장해주기
		}
		
		pass = new int[n+1][n+1]; // 최단 경로로 이동하려면 가장 먼저 거쳐야하는 집하장의 번호 저장하는 배열
		
		dijkstra(); // 각 집하장에서 다른 모든 집하장까지 가기 위한 최단 경로를 구해야 하므로 다익스트라 알고리즘 사용
		
		print();
	}

	private static void print() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(pass[i][j] == 0)
					System.out.print("-" + " ");
				else
					System.out.print(pass[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void dijkstra() {
		for(int i = 1; i <= n; i++) {
			int[] D = new int[n+1]; // i번째 집하장에서 출발해서 각 집하장으로 도착할 때의 최소 비용 저장 배열
			
			Arrays.fill(D, Integer.MAX_VALUE); // 최솟값 갱신을 위해 각 비용을 큰 값으로 설정
			D[i] = 0; // i번 집하장을 시작점으로 두고, 해당 최소 비용을 0으로 두기
			
			PriorityQueue<Node> queue = new PriorityQueue<Node>(); // 가장 작은 비용부터 정렬해서 queue에 넣기 위해 우선순위큐 사용
			queue.offer(new Node(i, D[i])); // i번 집하장을 시작점으로 두기
			
			while(!queue.isEmpty()) {
				Node current = queue.poll();
				
				for(int k = 1; k <= n; k++) { // queue에 들어있는 i와 인접한 점 current.vertex가 경유지가 되어 i->k의 최소 비용 검사 
					if(edge[current.vertex][k] != 0 && D[k] > current.time + edge[current.vertex][k]) {
						// 경유지와 도착지 k가 인접하며, 저장된 최소 비용보다 경유지를 거친 비용이 더 최소라면
						D[k] = current.time + edge[current.vertex][k];
						
						if(edge[i][k] != 0 && pass[i][k] == 0) // i와 k가 인접한데 아직 i->k로의 경로 중 가장 먼저 거쳐야할 집하장이 검색되지 않은 상태라면
							pass[i][k] = k; // 경유지 없이 직접 경로라는 의미이므로, k로 저장
						else
							pass[i][k] = pass[i][current.vertex]; // 경유지까지 올 때 가장 먼저 거쳐야할 집하장이 곧 i에서 k로 갈때 가장 먼저 거쳐야 할 집하장
						
						queue.offer(new Node(k, D[k]));
					}
				}
			}
		}
	}
}