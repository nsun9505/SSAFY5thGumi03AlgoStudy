import java.io.*;

public class Main {
    static int[] arr = new int[10001];
    static int[] dp = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        for(int i=3; i<=N; i++){
            dp[i] = Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]);
            dp[i] = Math.max(dp[i-1], dp[i]);
        }
        sb.append(dp[N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
