# [11725] 트리의 부모 찾기

## 분류
> BFS
>
> DFS

## 코드
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

## 문제풀이
- 그냥 union & find 형식으로 짰다가 시간 초과 나서 약간의 구글링의 도움을 받아 ^^~.. dfs로 풀었습니다.
- 기본 형태는 최상위 루트가 1로 잡혀있기 때문에, 연결 정보를 전부 읽어서, 각 노드에 리스트로 쭉 연결해줬습니다.
- 리스트로 연결한다음에 최상위인 1부터 천천히 하향식으로 검사해나갈건데, 부모와 자식 관계를 신경쓰지 않고 연결리스트로 저장했기 때문에, 하향식 특성상 이미 방문했다면 부모라는 의미기 때문에 방문 배열을 만들어 방문 체크를 같이 해줬습니다.
- 나에게 연결된 노드이고, 방문하지 않았다면 그 노드는 나의 자식 노드이기 때문에 부모 노드를 '나'로 수정하는 작업을 반복적으로 해주면 생각보다 간단하게 구현 가능했습니다.