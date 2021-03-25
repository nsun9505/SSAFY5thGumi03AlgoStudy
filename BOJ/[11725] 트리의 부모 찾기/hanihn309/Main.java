package bkj_11725_트리의_부모_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer>[] list;
	static int[] parents;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		
		parents = new int[N+1]; // 부모 노드 번호를 저장할 배열
		visited = new boolean[N+1]; // dfs로 타고 내려갈 때, 하위 노드에서 상위 노드의 부모를 바꾸지 않게하기 위해 방문 체크 배열 만듦
		list = new LinkedList[N+1]; // 각 노드에 연결된 노드들을 전부 추가해서 검사
		
		for(int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b); list[b].add(a);
			// 둘 다 추가해줘야 나중에 최상위 루트인 1부터 하향식으로 검사할 때, dfs를 이용해 타고 내려갈 수 있음.
		}
		
		findParents(1); // 최상위 루트인 1부터 dfs로 타고 내려가면서 검사
		
		for(int i = 2; i <= N; i++) {
			sb.append(parents[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void findParents(int i) {
		visited[i] = true; // 현재 노드는 상위 노드이니까 방문 체크
		
		for(int child : list[i]) { // 검사하려는 현재 i번째 노드에 연결된 모든 노드를 검사하면서, 자식 노드면 해당 자식 노드의 부모 배열을 값을 i로 수정해줌
			if (!visited[child]) {
				parents[child] = i;
				findParents(child);
			}
		}
	}
}