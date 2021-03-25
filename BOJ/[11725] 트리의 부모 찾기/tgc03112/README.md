# [11725] 트리의 부모 찾기

## 분류
> BFS
>
> DFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {	//트리의 부모 찾기 
	static int n;
	static int[] p;
	static boolean[] v;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		n = Integer.parseInt(br.readLine());
		p = new int[n+1];
		v = new boolean[n+1];
		list = new ArrayList[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=1;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			list[s].add(e);
			list[e].add(s);
		}
		v[1]=true;
		dfs(1);
		
		for(int i=2;i<p.length;i++) {
			System.out.println(p[i]);
		}
	}
	private static void dfs(int k) {
		for(int i :list[k]) {
			if(!v[i]) {
				p[i]=k;
				v[i]=true;
				dfs(i);
			}
		}
	}
}
```

## 문제풀이
트리의 최상위 루트는 무조건 1이고 노든 노드는 1아래로 연결되어 있습니다.

ArrayList를 사용해서 트리를 연결 해줬습니다.

노드를 하나씩 방문하면서 만약 방문하지 않은 노드라면 ? 위에서 dfs로 내려왔기 떄문에 현재 자신이 방문한 노드의 부모가 됩니다.

현재 방문한 노드 위치의 p배열에 나의 값 (방문한 노드의 부모가 될 값) 을 저장해주고 자식 노드에서 다시 dfs를 처리해줍니다.

dfs는 깊이 우선 탐색이기 때문에 부모부터 방문할 수 밖에 없는 특성을 이용했습니다.