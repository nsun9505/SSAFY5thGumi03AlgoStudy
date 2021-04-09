package bkj_14501_퇴사; // 210407

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 퇴사까지 남은 날
		int[] T = new int[N+1]; // 상담하는데 걸리는 기간
		int[] P = new int[N+1]; // 상담 금액
		
		for(int i = 1; i <= N; i++)  {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] profit = new int[N+2];
		
		for(int i = 1; i <= N; i++) {
			// i날에 일 안하는 경우
			profit[i+1] = Math.max(profit[i+1], profit[i]);
						
			// i날에 일 하는 경우
			if(i+T[i] > N+1) // N일 초과하면 일 시작하면 안됨
				continue;
			
			profit[i+T[i]] = Math.max(profit[i+T[i]], profit[i] + P[i]);
		}
		System.out.println(Math.max(profit[N+1], profit[N]));
	}
}