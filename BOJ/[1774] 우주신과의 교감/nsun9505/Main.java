import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Position[] vertexes;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        vertexes = new Position[N+1];
        parent = new int[N+1];

        for(int i=1; i<=N; i++){
            parent[i] = i;
            st = new StringTokenizer(br.readLine());
            vertexes[i] = new Position();
            vertexes[i].x = Double.parseDouble(st.nextToken());
            vertexes[i].y = Double.parseDouble(st.nextToken());
        }

        int numOfEdge = M;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            union(v1, v2);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            for(int j=i+1; j<=N; j++){
                double dist = Math.sqrt(Math.pow(vertexes[i].x - vertexes[j].x, 2) + Math.pow(vertexes[i].y - vertexes[j].y, 2));
                pq.offer(new Edge(i, j, dist));
            }
        }

        double answer = 0.0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(union(edge.v1, edge.v2)){
                answer += edge.dist;
                numOfEdge++;
            }
        }
        sb.append(String.format("%.2f", answer));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y)
            return false;
        parent[x] = y;
        return true;
    }

    static class Position{
        double x;
        double y;

        public Position() { }

        public Position(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        double dist;

        public Edge(int v1, int v2, double dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.dist > o.dist)
                return 1;
            return -1;
        }
    }
}