package bkj_17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) { // 회문 = 0, 유사 회문 = 1, 모두 아님 = 2
			String words = br.readLine(); // 문자열 받음
			
			sb.append(isPalindrome(words)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int isPalindrome(String words) {
		int result = 0, left = 0, right = words.length()-1;
		
		while(left <= right) {
			if (words.charAt(left) == words.charAt(right)) { // 대칭값이 같으면 계속 검사
				left++;
				right--;
			} else { // 대칭값이 다르다면 left나 right값을 각각 변화시켜서 (하나의 문자를 제거하는 개념) 유사 회문인지 검사
				int lf = left, rg = right;
				
				lf++;
				
				while(lf <= rg) {
					if (words.charAt(lf) == words.charAt(rg)) {
						lf++;
						rg--;
					} else {
						result++; // 대칭값이 다른경우가 또 나온다면 모두 아닌 경우이므로 result 증가시켜주고 break
						break;
					}
				}
				
				lf = left; rg = right; // 이번엔 right 값을 변화시켜서 유사회문인지 검사
				
				rg--;
				
				while(lf <= rg) {
					if (words.charAt(lf) == words.charAt(rg)) {
						lf++;
						rg--;
					} else {
						result++;
						break;
					}
				}
				return result;
			}
		}
		return result;
	}
}
