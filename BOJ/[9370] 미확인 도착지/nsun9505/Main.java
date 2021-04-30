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
            if(startDist[G] < startDist[H]){
                midEdgeVer1 = G;
                midEdgeVer2 = H;
            } else {
                midEdgeVer1 = H;
                midEdgeVer2 = G;
            }

            int[] midDist = dijkstra(midEdgeVer2);
            list.clear();
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