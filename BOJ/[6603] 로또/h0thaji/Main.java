package BOJ6603_로또;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int K; // 총 선택 할 수
	static int[] S; // 입력한 로또번호 담을 배열
	static boolean[] v; // 방문체크
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		while(true) { // 0이 나올때까지 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()); // 총 선택 할 수 입력
			if(K == 0) break; // K가 0이면 break

			S = new int[K]; // 입력할 로또 번호 배열 생성
			v = new boolean[K]; // 방문체크 배열 생성
			for (int i = 0; i < K; i++) { // 로또 번호 입력
				S[i]=Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0); // 로또 뽑기, 뽑은 횟수, 뽑은 건 안뽑아도 되니
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int cnt, int start) {
		
		if(cnt == 6) { // 6개 뽑았으면
			for (int i = 0; i < K; i++) {
				if(v[i]) { // true면 K개중 뽑힌 수이므로
					sb.append(S[i]).append(" "); // 결과 출력 stringbuilder에 append
				}
			}
			sb.append("\n"); // 줄바꿈
			return;
		}
		
		for (int i = start; i < K; i++) { 
			v[i] = true; // 해당 번호 뽑았으니 true
			dfs(cnt+1,i+1); // 다음 번호 뽑으러, cnt+1, 해당 번호 다음부터 확인하면되니 i+1
			v[i] = false; // 해당 번호 뽑은 경우가 끝났으니 false
		}
	}

}
