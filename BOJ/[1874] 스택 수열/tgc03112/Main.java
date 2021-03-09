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












