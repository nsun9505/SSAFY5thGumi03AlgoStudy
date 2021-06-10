package BOJ1613_역사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, S;
	static boolean[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken())+1;
		K = Integer.parseInt(st.nextToken());
		
		arr = new boolean[N][N];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = true;
		}
		
		for(int k = 1; k < N; k++) {
			for(int i = 1; i < N; i++) {
				for(int j = 1; j < N; j++) {
					if(arr[i][k] && arr[k][j]) arr[i][j] = true;
				}
			}
		}
		
		S = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(arr[a][b] == arr[b][a]) sb.append(0).append("\n");
			else {
				sb.append(arr[a][b]? -1:1).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
