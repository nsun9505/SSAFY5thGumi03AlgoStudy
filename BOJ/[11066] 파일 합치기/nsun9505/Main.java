import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int INF = 1000000000;
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N+1];
            int[] sum = new int[N+1];
            int[][] dp = new int[N+1][N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + arr[i];
            }

            for(int i=1; i<N; i++)
                dp[i][i+1] = arr[i] + arr[i+1];

            for(int r = 2; r<N; r++){
                for(int i=1; i + r <= N; i++){
                    int j = i + r;
                    dp[i][j] = INF;
                    for(int k=i; k<j; k++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1]);
                    }
                }
            }
            sb.append(dp[1][N]+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}