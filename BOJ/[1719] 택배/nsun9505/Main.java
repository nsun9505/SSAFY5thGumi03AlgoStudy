import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N+1][N+1];
        int[][] prev = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if(d < dist[u][v]){
                dist[u][v] = d;
                dist[v][u] = d;
                prev[u][v] = v;
                prev[v][u] = u;
            }
        }

        for(int k=1;k<=N;k++){
            for(int i=1;i<=N; i++){
                for(int j=1;j<=N; j++){
                    int d = dist[i][k] + dist[k][j];
                    if(d < dist[i][j]){
                        dist[i][j] = d;
                        prev[i][j] = prev[i][k];
                    }
                }
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(prev[i][j] == 0) sb.append("- ");
                else sb.append(prev[i][j] + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}