import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 { // 210421

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		long S = Long.parseLong(st.nextToken()); // 부분합이 S
		
		int[] array = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = 0, result = 0;
		int min = N+1;
		
		while(true) {
			if(result >= S) {
				result -= array[left];
				min = Math.min(min, right - left);
				left++;
			} else if (right == N) {
				break;
			} else {
				result += array[right];
				right++;
			}
		}
		
		if(min == N+1)
			System.out.println(0);
		else
			System.out.println(min);
	}
}