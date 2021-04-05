package BOJ1062_가르침;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N,K, res = 0;
	static String[] word;
	//a c n t i
	static boolean[] v = {true,false,true,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,true,false,false,false,false,false,false};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		word = new String[N];
		
		if(K < 5) sb.append(res);
		else if(K == 26) sb.append(N);
		else {
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				word[i] = str.substring(4,str.length()-4);
				
			}
			
			dfs(0,0);
			sb.append(res);
		}
			
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int cnt, int start) {
		if(cnt == K-5) {
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				boolean ok = true;
				for (int j = 0; j < word[i].length(); j++) {
					if(!v[word[i].charAt(j) -'a']) {
						ok = false;
						break;
					}
					
				}
				if(ok) sum++;
			}
			
			res = Math.max(sum, res);
			return;
		}
		
		for (int i = start; i < 26; i++) {
			if(!v[i]) {
				v[i] = true;
				dfs(cnt+1,i);
				v[i] = false;
			}
		}
		
	}

}
