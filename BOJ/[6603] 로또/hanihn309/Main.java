package bkj_6603_로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] num, result;
	static int k;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		String str = br.readLine();
		
		while(str != null) {
			st = new StringTokenizer(str);
			
			k = Integer.parseInt(st.nextToken());
			
			if(k == 0)
				break;
			
			num = new int[k];
			result = new int[6];
			
			for(int i = 0; i < k; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0);
			sb.append("\n");
			
			str = br.readLine();
		}
		
		System.out.println(sb.toString());
	}

	private static void combination(int cnt, int start) {
		if(cnt == 6) {
			for(int i = 0; i < 6; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i < k; i++) {
			result[cnt] = num[i];
			
			combination(cnt+1, i+1);
		}
	}

}
