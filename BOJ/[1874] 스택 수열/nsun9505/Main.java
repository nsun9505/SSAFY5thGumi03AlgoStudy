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