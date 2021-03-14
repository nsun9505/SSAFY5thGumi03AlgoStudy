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

