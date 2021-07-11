import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Position[] positions;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        positions = new Position[N];
        parent = new int[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            positions[i] = new Position(i, x, y);
            parent[i] = i;
        }


        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                double x = Math.pow(positions[i].x - positions[j].x, 2);
                double y = Math.pow(positions[i].y - positions[j].y, 2);
                double weight = Math.sqrt(x + y);

                pq.offer(new Edge(i, j, weight));
            }
        }

        int numOfEdge = 0;
        double answer = 0;
        while(numOfEdge < N-1){
            Edge edge = pq.poll();

            int a = find(edge.v1);
            int b = find(edge.v2);

            if(a != b){
                parent[b] = a;
                numOfEdge++;
                answer += edge.weight;
            }
        }
        sb.append(String.format("%.2f", answer));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int X){
        if(X == parent[X])
            return X;
        return parent[X] = find(parent[X]);
    }

    static class Position{
        int index;
        double x;
        double y;

        public Position(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        double weight;

        public Edge(int v1, int v2, double weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
}
