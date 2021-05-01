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

```

## 문제풀이
- 저는 뭐가 더 효율적인 방법인지 모릅니다.. 그냥 통과되면 일단 맞구나 싶어요..
- "끝나지 않는 파티", "서강그라운드" 두 문제는 플로이드-와샬로 풀었기 때문에, 이번 문제는 다익스트라로 풀었습니다. 사실 무슨 알고리즘으로 할지 선택할만큼 잘하는게 아니라 그냥 다익스트라일 것 같아서 푸는겁니다 ^^
- 이 문제는 최단 경로를 구할 때, 제일 먼저 거쳐야하는 정점을 출력해야하는 문제입니다. 그래서 답은 일단 각 정점에서 인접한 정점들 중에 나올겁니다.
- 주의해야할 점은, 처음에 인접한 정점들 중 나올 것이기 때문에 우선순위 큐를 사용할 때, 내 정점에서 인접한 정점들만 queue에 넣고 풀면될거라고 생각했는데 그러면 최단 경로를 구할 수 없었습니다. 반례는 문제에서도 나와있듯이 1번 집하장에서 6번 집하장으로 갈 수 있는 최단 경로는, "1-2-6"이 아니라 "1-2-5-6"입니다.
- 코드는 다익스트라의 틀을 그대로 가져왔습니다. 조금 차이가 있다면, 각 집하장 모두를 검사해야 하므로, for문 안에서 최소 비용 배열과 queue를 매번 새롭게 만들어준다는 것입니다.
- queue를 뽑아내서 검사를 할 때, qeeue에서 뽑아낸 정점은 경유지가 되므로, 이미 저장된 최소 비용보다 이 경유지를 거치는 경로가 더 최소일 때 값들을 갱신해주어야 합니다. 가장 먼저 거쳐야하는 집하장을 저장해놓은 배열인 pass의 값들을 갱신할 때 주의해야할 점은, 나와 인접한 정점들에게 가는 pass 값은 인접한 정점값이어야 하고, 인접하지 않은 정점들에게 갈때의 pass 값은 경유지까지의 pass값이어야 한다는 것입니다. 설명이 어려운 듯하지만 몇번만 써서 보면 바로 알 수 있습니다.
- 이렇게 queue를 다 뽑아내고, 다시 for문을 돌리면 완성~~!