package BOJ2156_포도주시식;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N; // 포도주 잔의 개수,
	static int[] wine, dp; // 입력받을 포도주 양의 배열, dp 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		wine = new int[N+1];
		for (int i = 1; i <= N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[N+1];
		
		dp[1] = wine[1]; // 첫 잔마셨을 경우 저장
		if(N > 1) dp[2] = wine[1] + wine[2]; // 포도주의 수가 1이상일때, 두 잔 다 마셨을 경우 저장
		for (int i = 3; i <= N; i++) { // 3잔부터는 앞에 두개를 마셨는지, 하나만 마셨는지 등 경우를 따져서 마신 양의 최대값을 dp에 저장
			dp[i] = Integer.max(dp[i-1], Integer.max(dp[i-2]+wine[i], dp[i-3] + wine[i-1] + wine[i]));
		 }
		
		sb.append(dp[N]); // 마지막 dp배열 값만 출력 -> 정답
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
