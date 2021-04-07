import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int answer = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			if(i + day <= N) {
				dp[i+day] = Math.max(dp[i] + money, dp[i+day]);
				answer = Math.max(dp[i+day], answer);
			}
			
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			answer = Math.max(answer, dp[i+1]);
		}
		
		sb.append(answer);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
