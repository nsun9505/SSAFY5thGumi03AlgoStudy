

# [11725] 트리의 부모 찾기

## 분류
> BFS
>
> DFS

## 코드
```java

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


```

## 문제풀이

- 노드의 개수가 주어지고 루트 노드 1을 가지는 트리를 생성해서 자신의 부모 노드를 출력시키는 문제였습니다.

- 단순히 배열과 반복문을 이용해서 풀어봤지만 역시나 시간초과가 나왔습니다...

- 연결된 두 정점을 입력받는 것을 보니 아래와 같은 그래프 모양이 나와 BFS를 이용하여 풀었습니다.

  - ![image-20210325205708336](C:\Users\HAJI\AppData\Roaming\Typora\typora-user-images\image-20210325205708336.png)

- 인접행렬로 만들었지만 간선의 비해 노드수가 많아지다보니 이번에는 메모리초과가 떴습니다!!

- #### BFS / 인접리스트 사용하여 풀이했습니다.

  - 입력받은 노드들은 인접해있으므로 인접리스트에 삽입하였습니다.

    - 위 소스 예) 

      ```java
      /*7
      1 6
      6 3
      3 5
      4 1
      2 4
      4 7*/
      adjList[n1] = new Node(n2,adjList[n1]); // n1 노드는 n2 노드와 인접해있다.
      adjList[n2] = new Node(n1,adjList[n2]); // n2 노드는 n1 노드와 인접해있다.
      ```

      - 1 6을 입력받게 되면

        adjList[1] = new Node(6,NULL) ; 이런 형식으로 Node 객체가 생성되고, adjList[6] 도 마찬가지고 vertex는 1, next는 NULL 이됩니다.

        그렇게 인접리스트가 생성하는 반복문이 끝나면  adjList[6] 에는 아래와 같은 data를 저장하게 됩니다.

        - ![image-20210325210609532](C:\Users\HAJI\AppData\Roaming\Typora\typora-user-images\image-20210325210609532.png)

  - bfs 탐색을 시킵니다.

    - 루트 노드가 1부터니 1부터 시작합니다.

    - bfs 탐색 시 이런 형태로 탐색이 됩니다 (순서와 상관없이 그림..) , 트리라고 가정하고 설명하겠습니다

      ![image-20210325211101663](C:\Users\HAJI\AppData\Roaming\Typora\typora-user-images\image-20210325211101663.png)

    - 백준 사이트 테케 1번 예시)

      1. adjLIst[1] 은 4와 6을 자식 노드를 가지고 있습니다. => 마지막 next Node는 NULL이므로 반복문을 빠져나옵니다. (위 인접리스트 구조 참고)
      2. 해당 tree[4] , tree[6] 배열에 q에서 poll된 값을 넣습니다 => 해당 부모노드이기 때문입니다.
      3. 4, 6 노드를 큐에 삽입합니다.

      - 위 내용을 반복하면 답 :  4 6 1 3 1 4 가 나옵니다.
      - *tree[index] 가 0일 때, 즉 해당 노드가 부모를 가지고 있지 않을 때만 부모 노드를 삽입하고 1부터 4,6 / 2,7,3 / 5 순으로 탐색을 하기 때문에 부모 노드가 꼬이는 일은 없습니다.(부모 노드는 하나만 가질 수 있기에!)

  