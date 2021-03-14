# [1874] 스택 수열

## 분류
> 자료구조
>
> 스택

## 코드
```java
package BOJ1874_스택수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static int N; 
	static int[] arr; // 입력된 수열 담을 배열
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int idx = 0;		
		for (int i = 0; i < N; i++) { // 배열에 입력된 수열 삽입
			arr[i] = Integer.parseInt(br.readLine());						
		}			
		
		Stack<Integer> per = new Stack<>(); //스택생성 
		
		for (int i = 1 ; i <= N; i++) { // 1 ~ N 까지 확인
			per.push(i); // 1~N까지 push
			sb.append("+").append("\n"); // push해주면 + 출력
			while(!per.isEmpty() && per.peek() == arr[idx]) { // stack이 비어있지 않고 해당 스택과 해당 배열이 같다면
				per.pop(); //pop
				idx++; // 다음 배열 확인위해 ++
				sb.append("-").append("\n"); // pop햇으니 - 출력
			}
		}
		
		if(per.isEmpty()) System.out.println(sb);
		else System.out.println("NO");
		
	}

}

```

## 문제 풀이

- 입력된 순열을 순서대로 배열에 담고 배열과 1~N까지의 수를 비교를 해서 push과 pop연산을 해줄때 '+','-'를 출력되도록 풀었습니다.
  - 1~N(오름차순으로 반드시 push를 해야하므로) 까지 push와 동시에 '+'를 append를 해줍니다.
  -  per stack이 비어있지 않고 per.peek()값과 비교할 idx번째 arr과 같다면 pop과 '-'를 append 해주고 다음 idx번째 arr를 비교해야하므로 idx++를 해주었습니다.
  - 이렇게 N번 push을 해주고나면 반복문은 끝납니다.
- 입력된 순열이 완성이 되었다면 per stack은 비어있게 되므로 sb를 출력시킵니다.
- per stack이 비어있지 않다면 입력된 순열이 완성이 안됐으므로 "NO"를 출력시킵니다.