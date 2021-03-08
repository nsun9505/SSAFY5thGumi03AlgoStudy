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
