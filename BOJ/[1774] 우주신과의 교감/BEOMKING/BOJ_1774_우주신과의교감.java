package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1774_우주신과의교감 {
    static int N, M;
    static int[] parent;
    static int[][] Vertex;
    static ArrayList<Location> arrayList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        Vertex = new int[N + 1][2];
        arrayList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            Vertex[i][0] = Integer.parseInt(input[0]);
            Vertex[i][1] = Integer.parseInt(input[1]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double wei = Math.sqrt(Math.pow(Math.abs(Vertex[i][0] - Vertex[j][0]), 2) + Math.pow(Math.abs(Vertex[i][1] - Vertex[j][1]), 2));
                arrayList.add(new Location(i, j, wei));
            }
        }
        Collections.sort(arrayList);

        parent = new int[N + 1];
        makeSet();

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            if(find(from) != find(to)) union(from, to);
        }

        int count = M;
        double result = 0;

        for (Location location: arrayList) {
            if(union(location.from, location.to)){
                result += location.weight;
                if(++count == N - 1) break;
            }
        }
        System.out.println(String.format("%.2f", result));
    }
    static class Location implements Comparable<Location>{
        int from, to;
        double weight;

        public Location(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Location o) {
            if(weight - o.weight > 0) return 1;
            else if(weight - o.weight == 0) return 0;
            else return -1;
        }
    }
    static void makeSet(){
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int a, int b){
        int x = find(a);
        int y = find(b);
        if(x == y) return false;
        parent[y] = x;
        return true;
    }
}
