# [17609] 회문

## 분류
> 구현
>
> 문자열

## 코드
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


## 문제 풀이
처음에는 양쪽 나눠서 아스키 코드값의 합으로 판단하려고 했는데 각각 다른 값의 합들이 같을 수도 있다는 반례를 신경 못써서 다시 짰습니다..... list에 문자열을 넣어서 실제로 제거하면서 검사하려고도 했는데 너무 까다로운 것 같아서 문자열 그대로 charAt으로 인덱스를 비교하는 방법을 쓰기로 했습니다. 포인트는 양쪽에서 검사를 하다가 서로 다른 값이 나와서 그 중에 하나를 제거한다고 해도, 기존 문자열들의 순서는 그대로 유지되니까 제거한 곳부터 다시 인덱스를 줄이고 늘여가면서 회문인지 아닌지 검사하는 것 같습니다. 그리고 회문이면 0, 유사 회문이면 1, 모두 아니라면 2로 결과가 1씩 증가하는 형태니까, 검사하다가 아닌 경우를 만나면 결과값을 1씩 증가하게 짜면 더 깔끔하게 바로 결과를 구할 수 있을 것 같습니다!