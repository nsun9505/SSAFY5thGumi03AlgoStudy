# [1874] 스택 수열

## 분류
> 자료구조
>
> 스택

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon1874 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		Stack<Integer> stack = new Stack<Integer>();	//수열만들기
		
		int n = Integer.parseInt(br.readLine());	//수열n
		
		int start = 0;
		boolean flag = false;
		
		for(int t = 0;t<n;t++) {
			
			int num = Integer.parseInt(br.readLine()); 
			
			if(num > start) {
				start+=1;
				for(int i=start;i<=num;i++) {
					stack.push(i);
					sb.append('+').append('\n'); 	//stack에 넣을때 +
				}
				start = num;	//시작값 바꿔줌 
			}
			if(stack.peek() == num) {
				stack.pop();
				sb.append('-').append('\n');	//뺄 때 -				
			}
			else {	//수열 만들지 못하는 경우
				flag = true;	//출력문 위함
				break;
			}
		}
		if(flag) {
			System.out.println("NO");
		}
		else 
			System.out.println(sb);
	}
}
```

## 문제 풀이
문제 자체를 이해하는 시간이 굉장히 오래걸렸습니다.. 문제만 이해했다면 풀이는 어렵지않았던 것 같습니다.

수열 n 을 받았다면 stack 에는 무조건 오름차순인 1 ~ n 까지 순서로 stack 에 들어갑니다. 
   - start는 처음에 1부터 시작입니다. start 보다 입력받은 현재 값(num)이 크면 start ~ num 까지 입력합니다. (처음엔 무조건 1 ~ num까지 스택에 입력됩니다)
   - 입력한 뒤 start를 num으로 바꿔줍니다. 뒤에서 num+1 ~ 새로운 num 까지 stack에 넣기 위함입니다.
   - start ~ num까지 push 할 때마다 StringBuilder에 + 문자를 추가합니다.
   - num까지 입력했다면 peek()했을때 값은 num입니다.

입력받은 num이 start보다 작다면 그냥 stack.peek()을 해서 값을 보면 됩니다. 
   - peek()했을때 값이 num이라면 값을 꺼내고 -문자를 추가해줍니다.
   - 만약 num이 아니라면? 입력된 수열을 만들지 못하는 경우입니다. 이때, flag를 true로 바꾸어 마지막 출력문에서 NO를 출력할 수 있도록 합니다.