# [1874] 스택 수열

## 분류
> 자료구조
>
> 스택

## 코드
```java
import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        stack.push(0);
        for(int i=0; i<N; i++){
            int number = Integer.parseInt(br.readLine());

            while(stack.isEmpty() || stack.peek() < number) {
                stack.push(num++);
                sb.append("+\n");
            }

            if(stack.peek() > number) {
                sb.setLength(0);
                sb.append("NO");
                break;
            }
            sb.append("-\n");
            stack.pop();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
수열의 i번쨰 값이 stack에 들어갈 때까지 num을 증가시키면서 push합니다.
   - num은 1 ~ N 사이의 수를 의미합니다.
   - 이미 i번째 값이 stack에 들어가있을 수도 있습니다.
   - i번째 값까지 넣는다는 것은 수열의 i번째 값 이하의 값들이 모두 stack에 들어가게 됩니다.

스택에 수열의 i번째 값이 들어갔다면, 다시 스택에서 빼면 됩니다.

stack의 top이 수열의 i번째 값보다 크다면 이는 주어진 수열을 만족하지 못하므로 NO를 출력하면 됩니다.
   - 예를 들어, 수열의 1번쨰 값이 5라서 1~5를 stack에 넣습니다.
   - 스택의 top과 number가 일치하므로 5를 빼냅니다.
   - 수열의 2번째가 3이라면, 현재 스택에서 빠져나갈 수 있는 것은 4입니다.
   - 만약 4를 그냥 빼내고, 3을 가져온다면, 1~n으로 이루어진 수열에서 4는 빠지게 되므로 조건을 만족시키지 못하므로 no를 출력하면 됩니다.

