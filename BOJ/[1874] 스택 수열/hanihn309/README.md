
# [1874] 스택 수열

## 분류
> 자료구조
>
> 스택

## 코드
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


## 문제 풀이
- stack을 이용하는데, 입력은 무조건 오름차순이라는 조건때문에 간단하게 작성 가능.
- stack에 넣어야 하는 수를 value라는 변수로 잡았는데, push하는 for문에서 value값부터 현재 수열의 요소에 해당하는 수까지 넣으려면 초기 value값을 0으로 잡기.
- 만들어야하는 수열의 요소에 해당하는 값이 value값보다 크다면, 일단 그 요소 값까지 계속 push.
- 그 다음 push 수를 기억해야하기 때문에, value에 현재 수열의 요소 저장
- 만약 stack의 top값이 현재 만들어내야하는 수열의 값과 같지 않다면, pop해도 동일한 수열을 만들 수 없으므로 NO를 출력하고 종료.
- 수열의 요소까지 push를 해줬든 안해줬든, pop을 해줘야 수열이 만들어지니까 pop해주기
