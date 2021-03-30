package bkj_15652_N과M_4; // 210331

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준 15652 : N과 M (4) - 중복순열_비내림차순
	static int N, M;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		result = new int[M];
		
		permutationRepetNondecs(0, 1);
		
		System.out.println(sb.toString());
	}
	
	static void permutationRepetNondecs(int cnt, int start) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= N; i++) {
			result[cnt] = i;
			
			permutationRepetNondecs(cnt+1, i);
		}
	}

}
