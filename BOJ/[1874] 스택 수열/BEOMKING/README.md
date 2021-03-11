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
import java.util.*;

public class Main {
    static Stack<Integer> stack = new Stack<>();
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stack.push(0);
        int value = 0;
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine()); // 입력 수열
            if(number > stack.peek()){ // 현재 쌓여있는 최고 수보다 더 크다면
                for (int j = value + 1; j <= number; j++) { // 처리해야할 수열까지 + 연산
                    stack.push(j);
                    sb.append('+');
                    sb.append('\n');
                    value = stack.peek(); // 현재까지 스택에 쌓은 수 저장
                }
            }else{
                if(number != stack.peek()) { // 스택의 다음 수가 입력 수열과 다르다면
                    sb.delete(0, sb.length());
                    sb.append("NO");
                    break;
                }
            }
            stack.pop();
            sb.append('-');
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}


```

## 문제 풀이
- 처리해야할 수열을 차례로 입력 받으면서 연산을 진행하는 문제
- 처리해야할 수(number)를 스택의 상단의 수와 비교해서 number가 크다면 스택에 number 만큼 쌓는다.
- number가 작다면 스택의 상단과 비교해서 같지 않다면 연산이 불가함을 뜻한다.