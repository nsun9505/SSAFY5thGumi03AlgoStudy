# [9370] 미확인 도착지

## 분류
> 그래프 이론
>
> 다익스트라

## 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int INF = 10000000;
    static int N, M, T;
    static int S, G, H;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int loop = Integer.parseInt(br.readLine());
        for(int t=0; t<loop; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];
            for(int i=1; i<=N; i++)
                graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int midEdge = 0;
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if((a == G && b == H) || (a == H && b == G))
                    midEdge = c;

                graph[a].add(new Edge(b, c));
                graph[b].add(new Edge(a, c));
            }

            int[] startDist = dijkstra(S);
            int midEdgeVer1, midEdgeVer2;
            // 둘 중 어디가 더 짧은지
            if(startDist[G] < startDist[H]){
                midEdgeVer1 = G;
                midEdgeVer2 = H;
            } else {
                midEdgeVer1 = H;
                midEdgeVer2 = G;
            }

            // 더 먼 곳부터 다시 다익스트라를 돌림
            int[] midDist = dijkstra(midEdgeVer2);
            list.clear();
            // start -> 반드시 지나친 간선에 포함된 정점 중 거리가 짧은 정점 -> 반드시 지나친 간선 가중치 -> 반드시 지나친 간선에 포함된 정점 중 거리가 먼 정점 ~ 도착 예상 지점
            // 위 값이 start에서 target으로 갈 때 최단 경로와 똑같은지 비교하고 같으면 정답에 포함되므로 list에 저장
            for(int i=0; i<T; i++){
                int target = Integer.parseInt(br.readLine());
                int d = startDist[midEdgeVer1] + midEdge + midDist[target];
                if(startDist[target] == d)
                    list.add(target);
            }
            Collections.sort(list);

            for(int vertex : list)
                sb.append(vertex + " ");
            if(t < loop-1)
                sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 다익스트라
    public static int[] dijkstra(int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.clear();
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(edge.weight != dist[edge.vertex])
                continue;

            for(Edge next : graph[edge.vertex]){
                int d = next.weight + edge.weight;
                if(d < dist[next.vertex]){
                    pq.offer(new Edge(next.vertex, d));
                    dist[next.vertex] = d;
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge>{
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
```

## 문제풀이
최단 경로를 구하는 문제입니다.

문제는 최단 경로이긴 하지만, 반드시 지나가야 하는 간선이 포함되어 있어야 하는 예상 도착지점들을 찾는 것입니다.

일단 출발지(S)를 해서 다익스트라를 돌립니다.

그리고 S를 기준으로 구한 최단 경로 값에서 반드시 지나가야 하는 간선에 포함된 두 정점 중 어떤 것이 더 먼지 찾습니다.
   - 두 정점 중 짧은 거리는 midEdgeVer1
   - 두 정점 중 먼 거리는 midEdgeVer2
   
더 먼 정점을 기준(midEdgeVer2)으로 다시 다익스트라를 돌립니다.
   - 문제에서 볼 때는 1-3 간선 중에서는
   - midEdgeVer1 = 1
   - midEdgeVer2 = 3

그러면 이제 start -> midEdgeVer1 + midEdgeVer1과 midEdgeVer2 거리 + midEdgeVer2 -> target 더한 값이 start -> target 최단거리인지 검사합니다.
   - 만약 최단거리와 동일하다면 반드시 지나치는 간선을 포함하므로 정답에 포함시키면 됩니다.