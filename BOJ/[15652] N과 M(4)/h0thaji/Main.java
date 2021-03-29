package BOJ15652_N과M4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[] res;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[M]; // 뽑을 애들 담을 배열
		dfs(0,1); // 재귀함수
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int cnt,int start) { // 뽑은 개수 , 시작 index
		if(cnt == M) { // M개를 뽑아야하므로 뽑은 개수가 M개이면
			for (int i = 0; i < M; i++) { // 결과 배열 반복문
				sb.append(res[i]).append(" "); // res배열 append
			}
			sb.toString().trim(); // 마지막 공백 없애주고 줄바꿈
			sb.append("\n");
			return; // 백트래킹
			
		}
		
		for (int i = start; i <= N; i++) { // 시작부터 N까지
			
			res[cnt] = i; // 결과배열에 넣어줌
			dfs(cnt+1,i); // start가 1일때는 1부터 2일때는 2부터 니까 i넣어줌 cnt는 하나뽑았으니 +1을 해줌
		}
		
	}

}
