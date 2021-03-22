# [11725] 트리의 부모 찾기

## 분류
> BFS
>
> DFS

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N+1];

        // 각 노드의 부모를 저장할 배열
        int[] parents = new int[N+1];

        // 인접 리스트 초기화
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        StringTokenizer st = null;
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 간선 생성
            graph[u].add(v);
            graph[v].add(u);
        }

        // BFS에 사용할 Queue 선언
        Queue<Integer> queue = new LinkedList<>();
        // 1의 부모는 1로 초기화
        parents[1] = 1;

        // 1부터 BFS 탐색 시작
        queue.offer(1);

        // BFS!!!!
        while(!queue.isEmpty()){
            int cur = queue.poll();

            // 현재 노드에 연결된 노드들을 찾아봄.
            for(int next : graph[cur]){
                // parents[next]가 0이라면 아직 방문되지 않은 노드
                // parents[next]가 0이 아니라면 이미 방문되어서 부모가 할당됨
                if(parents[next] != 0)
                    continue;
                // 현재 노드에서 갈 수 있는 노드는
                // 현재 노드의 자식 노드이므로, 자식 노드의 부모를 현재 노드로 만들어준다.
                parents[next] = cur;

                // 큐에 넣어준다.
                queue.offer(next);
            }
        }

        for(int i=2; i<=N; i++)
            sb.append(parents[i] + "\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
노드의 부모를 찾기 위해 BFS를 사용했습니다.

BFS는 인접한 노드를 탐색하므로 트리에서 한 노드의 인접한 노드는 부모 노드이거나 자식 노드입니다.

문제에서 1번이 루트노드라고 했고, 1번부터 출발하므로 1번에 인접한 노드들은 모두 자식노드입니다!

1번 노드에서 갈 수 있는 노드들의 부모를 1로 설정하면 됩니다.

그리고 자식 노드들을 큐에 담고, 그 자식 노드들에 연결된 자식 노드들의 부모를 정해주는 식으로 가면 문제를 해결할 수 있습니다.