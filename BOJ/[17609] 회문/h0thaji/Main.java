package BOJ17609_회문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			sb.append(tqghlans(br.readLine())).append("\n");
		
		}
		System.out.println(sb);
	}
	private static int tqghlans(String s) {
		int cnt = 0;
		int l = 0,r = s.length()-1;

		while(l<=r) {
			if(s.charAt(l) != s.charAt(r)) {
				
					for (int start = l+1, end = r; start <= end; start++, end--) {
						if(s.charAt(start)==s.charAt(end)) continue;
						cnt++;
						break;
					}
					
					for (int start = l, end = r-1; start <= end; start++, end--) {
						if(s.charAt(start)==s.charAt(end)) continue;
						cnt++;
						break;
					}
					
					cnt++;
					break;
			}else {
				l++;
				r--;
			}
		}

		if(cnt == 0) return cnt; // 회문인경우
		else if(cnt > 2) return 2; // 2초과이면 회문X
		else return 1; // cnt가 2거나 1일경우 유사회문 ,  summuus, xabba ,sumumuus
		
	}
}
////	반례 : sumumuus
////	private static int tqghlans(String s) {
////		int cnt = 0;
////		int l = 0,r = s.length()-1;
////
////		while(l<=r) {
////			if(cnt >= 2) return 2;
////			//if(cnt == 2) return cnt;
////			//반례:abaaxxbbb
////			if(s.charAt(l) != s.charAt(r)) {
////				
////				if(s.charAt(l+1) == s.charAt(r)) {
////					l++;
////				}else if(s.charAt(l) == s.charAt(r-1)) {
////					r--;
////				}else {
////					cnt++;
////				}
////				cnt++;
////			}else {
////				l++;
////				r--;
////			}
////		}
////
////		if(cnt == 1) return cnt;
////		else return cnt;
////		
////	}
//	
//	
//
//}
