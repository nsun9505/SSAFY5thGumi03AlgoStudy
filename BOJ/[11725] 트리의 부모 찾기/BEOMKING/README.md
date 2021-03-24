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
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            tree.get(first).add(second);
            tree.get(second).add(first);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1));
        boolean visited[] = new boolean[N + 1]; // 해당 노드를 방문했는지
        int parent[] = new int[N + 1]; // 각 노드의 부모노드 저장

        while(!queue.isEmpty()){
            Node now = queue.poll();
            parent[now.child] = now.parent; // 현재 노드의 부모 노드
            visited[now.child] = true; // 현재 노드 방문 체크

            for (int i = 0; i < tree.get(now.child).size(); i++) {
                if(visited[tree.get(now.child).get(i)]) continue;
                queue.offer(new Node(now.child, tree.get(now.child).get(i)));
            }
        }
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i] + "\n");
        }
        System.out.print(sb.toString().trim());
    }
    static class Node{
        int parent;
        int child;

        public Node(int parent, int child) {
            this.parent = parent;
            this.child = child;
        }
    }
}
```

## 문제풀이
BFS 문제입니다.

처음 날로 먹으려고 한 줄씩 입력 받아 두 수 중 하나가 루트이거나 true이면 부모, 그 외는 자식으로 처리하고 true 값으로 변경했습니다. 하지만 입력 순서가 다르면 풀 수 없었습니다.

인접 리스트를 사용해서 입력 받을 때 해당 숫자 리스트에 서로를 추가해줍니다.

큐에 루트 노드 값을 넣고 루트 노드 값의 리스트에 있는 숫자들을 방문해줍니다.

숫자를 방문할 때 부모 값을 같이 넣어주기 때문에 큐에서 뺄 때 부모의 값을 확인해서 저장해줍니다. 

