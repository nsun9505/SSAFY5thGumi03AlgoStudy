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
        int[] parents = new int[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        StringTokenizer st = null;
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        parents[1] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int next : graph[cur]){
                if(parents[next] != 0)
                    continue;
                parents[next] = cur;
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
