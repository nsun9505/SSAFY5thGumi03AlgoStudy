import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	//퇴사

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] T = new int[N+1];		//상담 완료까지걸리는 기간
		int[] P = new int[N+1];		//상담했을 때 받을 수 있는 금액 
		int[] dp = new int[N+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			T[i]=Integer.parseInt(st.nextToken());
			P[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			if(i+T[i]<=N) {	//일을 할 때(일을 할 수 있을 때)
				dp[i+T[i]]=Math.max(dp[i]+P[i], dp[i+T[i]]);
			}	//일을 안할 때 (i+1)날로 갈 때 
			dp[i+1]=Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[N]);
	}
}
