import java.util.PriorityQueue;

public class Solution {
    static int parent[];
    public int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        for(int i=0; i<n; i++)
            parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<costs.length; i++)
            pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));

        int numOfEdge = 0;
        while(!pq.isEmpty() && numOfEdge < n-1){
            Edge edge = pq.poll();

            if(union(edge.v1, edge.v2)){
                numOfEdge++;
                answer += edge.weight;
            }
        }

        return answer;
    }

    public static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b)
            return false;

        if(a > b){
            int tmp = a;
            a = b;
            b = tmp;
        }

        parent[b] = a;
        return true;
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
