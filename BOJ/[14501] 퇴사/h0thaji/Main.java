package BOJ14501_퇴사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N; // 일할 수 있는 기간
	static int[] T,P, dp; // 상담 완료 기간, 상담 했을 때 받을 수 있는 금액, 메모이제이션 할 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];	
		P = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1];
		
		for (int i = 0; i < N; i++) { // 첫 날 부터 마지막 날까지 확인해야하므로 반복
			// i+T[i]가 N+1 작을 때(해당 일에 상담을 하고 끝나는 일이 일할 수 있는 기간보다 작을 때) , 즉 일할 수 있을 때
			if(i+T[i] < N+1) dp[i+T[i]] = Integer.max(dp[i+T[i]], dp[i]+P[i]);  // 일 끝나고 받을 수 있는 돈을 저장 , 해당 일에 받을 수 있는 경우에서 가장 큰 금액만 저장

			// 일할 수 없으면 
			dp[i+1] = Integer.max(dp[i+1], dp[i]); // 이때까지 받은 금액을 다음 날로 옮겨야함 , 단 다음 날에도 상담 해서 받은 돈이 있을 수 있으니 가장 많이 받은 금액으로 저장
		}
		
		sb.append(dp[N]); // N+1배열로 생성 했으므로 마지막값이 답
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
