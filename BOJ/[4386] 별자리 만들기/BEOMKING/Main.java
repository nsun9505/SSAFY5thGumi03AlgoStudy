import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, parent[];
    static Star[] star;
    static ArrayList<Info> info;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        star = new Star[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            float y = Float.parseFloat(st.nextToken());
            float x = Float.parseFloat(st.nextToken());
            star[i] = new Star(y, x);
        }

        info = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dis = distance(star[i], star[j]);
                info.add(new Info(i, j, dis));
            }
        }
        Collections.sort(info);
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double result = 0;
        for (int i = 0; i < info.size(); i++) {
            Info now = info.get(i);
            if(find(now.start) != find(now.end)){
                result += now.dis;
                union(now.start, now.end);
            }
        }
        System.out.println(result);
    }
    static class Star{
        float y, x;
        public Star(float y, float x) {
            this.y = y;
            this.x = x;
        }
    }
    static class Info implements Comparable<Info>{
        int start, end;
        double dis;
        public Info(int start, int end, double dis) {
            this.start = start;
            this.end = end;
            this.dis = dis;
        }
        @Override
        public int compareTo(Info o) {
            if(dis - o.dis < 0) return -1;
            return 1;
        }
    }
    static double distance(Star first, Star second){
        return Math.sqrt(Math.pow(first.y - second.y, 2) + Math.pow(first.x - second.x, 2));
    }
    static int find(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    static void union(int y, int x){
        x = find(x);
        y = find(y);
        if(y != x){
            parent[y] = x;
        }
    }
}