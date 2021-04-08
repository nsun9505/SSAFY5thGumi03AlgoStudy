import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][10];
        for(int i=1; i<=9; i++)
            dp[0][i] = 1;

        for(int i=1; i<N; i++){
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
            for(int j=1; j<9; j++)
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
        }

        int answer = 0;
        for(int i=0; i<10; i++)
            answer = (answer + dp[N-1][i]) % 1000000000;
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}