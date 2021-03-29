package BOJ1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int MAX_RANGE = 100001;
	static int N,K,res;
	static boolean[] v = new boolean[MAX_RANGE];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N!=K) bfs();
		
		sb.append(res);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		v[N] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			res++;
			
			while(size-->0) {
				int current = q.poll();
				
				int pre = current - 1;
				int next = current + 1;
				int jump = current * 2;
			
				if(pre == K || next == K || jump == K) return;
				
				
				if(pre >= 0 && !v[pre]) {
					q.offer(pre);
					v[pre] = true;
				}
				if(next < MAX_RANGE && !v[next]) {
					q.offer(next);
					v[next] = true;
				}
				if(jump < MAX_RANGE && !v[jump]) {
					q.offer(jump);
					v[jump] = true;
				}
				
			}
			
		}
		
	}
	

}
