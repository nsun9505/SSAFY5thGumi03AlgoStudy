import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int indegree[];
    static List<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        graph = new LinkedList[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new LinkedList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for(int j=1; j<K; j++){
                int cur = Integer.parseInt(st.nextToken());
                indegree[cur]++;
                graph[prev].add(cur);
                prev = cur;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(indegree[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(cur+"\n");

            for(int next : graph[cur]){
                indegree[next]--;
                if(indegree[next] == 0)
                    queue.offer(next);
            }
        }

        boolean isOk = true;
        for(int i=1; i<=N; i++){
            if(indegree[i] > 0){
                isOk = false;
                break;
            }
        }

        if(!isOk){
            sb.setLength(0);
            sb.append(0);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}