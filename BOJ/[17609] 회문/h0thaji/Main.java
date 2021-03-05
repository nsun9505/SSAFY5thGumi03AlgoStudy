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
			sb.append(tqghlans(br.readLine())).append("\n"); // readLine을 tqghlans으로 넘겨주고 받은 값을 sb에 append 시켜줌
		
		}
		System.out.println(sb);
	}
	private static int tqghlans(String s) {
		int cnt = 0;
		int l = 0,r = s.length()-1;
		// 왼쪽 , 오른쪽으로 포인터를 나눠줌
		while(l<=r) { //왼쪽 인덱스가 오른쪽인덱스보다 커져버리면 stop
			if(s.charAt(l) != s.charAt(r)) { //두문자가 다르다면
				
					//왼쪽 +1 인덱스와 오른쪽이 같은지 확인
					for (int start = l+1, end = r; start <= end; start++, end--) {
						if(s.charAt(start)==s.charAt(end)) continue; // 같다면 continue;
						//다르다면 cnt++
						cnt++;
						break;
					}
					
					//왼쪽 인덱스와 오른쪽+1 인덱스 값이 같은지 확인
					for (int start = l, end = r-1; start <= end; start++, end--) {
						if(s.charAt(start)==s.charAt(end)) continue; //같다면 continue;
						//다르다면 cnt++;
						cnt++;
						break;
					}
					//두문자가 달랐으니 cnt++
					cnt++;
					break;
			}else { //두문자가 같다면 l++,r--
				l++;
				r--;
			}
		}

		if(cnt == 0) return cnt; // 회문인경우 > return 0
		else if(cnt > 2) return 2; // 2초과이면 회문X > return 2 xabbay
		else return 1; // cnt가 2거나 1일경우 유사회문 ,  summuus, xabba ,sumumuus  > return 1
		
	}
}
//	반례 : sumumuus
//	private static int tqghlans(String s) {
//		int cnt = 0;
//		int l = 0,r = s.length()-1;
//
//		while(l<=r) {
//			if(cnt >= 2) return 2;
//			//if(cnt == 2) return cnt; 반례:abaaxxbbb
//			
//			if(s.charAt(l) != s.charAt(r)) {
//				
//				if(s.charAt(l+1) == s.charAt(r)) {
//					l++;
//				}else if(s.charAt(l) == s.charAt(r-1)) {
//					r--;
//				}else {
//					cnt++;
//				}
//				cnt++;
//			}else {
//				l++;
//				r--;
//			}
//		}
//
//		if(cnt == 1) return cnt;
//		else return cnt;
//		
//	}
//	
//	
//
//}
