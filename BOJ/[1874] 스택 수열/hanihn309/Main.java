package bkj_1874; // 210309

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main { // 백준 1874번 : 스택 수열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int perm = 0, value = 0; // perm : 만들어내야하는 수열을 하나씩 받을 변수, value : 1~n까지의 숫자
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			perm = Integer.parseInt(br.readLine());
			
			if (perm > value) { // 현재 수까지 stack에 push
				for(int j = value + 1; j <= perm; j++) {
					stack.push(j);
					sb.append("+\n");
				}
				value = perm;
			} else if (stack.peek() != perm) {
				System.out.println("NO");
				return;
			}
			
			stack.pop();
			sb.append("-\n");
		}
		System.out.println(sb.toString());
	}
}
