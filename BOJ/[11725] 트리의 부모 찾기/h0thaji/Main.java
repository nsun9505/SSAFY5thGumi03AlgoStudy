package BOJ11725_트리의부모찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N; // 노드 개수
	static int[] tree; // 부모 노드 저장 배열
	static Node[] adjList; // 인접리스트
	
	static class Node{ // 노드
		int vertex;
		Node next;
		
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		adjList = new Node[N+1]; // 1부터 시작이므로 N+1 크기로 인접리스트 생성
		tree = new int[N+1]; // 1부터 시작이므로 N+1 크기의 배열 생성
		
		for (int i = 0; i < N-1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			adjList[n1] = new Node(n2,adjList[n1]); // n1 노드는 n2 노드와 인접해있다.
			adjList[n2] = new Node(n1,adjList[n2]); // n2 노드는 n1 노드와 인접해있다.
		}

		bfs();
		
		for (int i = 2; i < N+1; i++) { // 2번부터 N 까지
			sb.append(tree[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void bfs() {		
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(1); // 루트 노드는 1이므로 1부터시작		
		tree[1] = 1; // 1 노드의 부모는 없으므로 1삽입
		
		while(!q.isEmpty()) { // q가 빈 공간일때까지
			int n = q.poll(); // q에 있는 vertex값 n으로 poll
			for (Node tmp = adjList[n]; tmp != null ; tmp = tmp.next) { // tmp = poll 된 인접리스트, null이라는 얘기는 마지막, 인접해있는 다른 노드도 확인 해야 하기 때문에 next로 이동
				if(tree[tmp.vertex] == 0 ) { // 방문을 하지않았을 때
					tree[tmp.vertex] = n; // 1부터 bfs를 하였기 때문에 차례대로 tree[vertex]의 부모 노드는 n이 됨. 
					q.offer(tmp.vertex); // 해당 vertex가 자식이 있는 지 확인
				}
			}
		}
	}

}
