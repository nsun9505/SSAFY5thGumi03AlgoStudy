import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_수_찾기 { // 210415
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] numN = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numN[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] numM = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			numM[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색을 위해 오름차순 정렬
		Arrays.sort(numN);
		
		for(int i = 0; i < M; i++) {
			int start = 0, end = N-1;
			while(start <= end) {
				int middle = (start + end) / 2;
				
				if (numM[i] < numN[middle]) { // 찾으려는 숫자보다 middle이 크다면
					end = middle - 1;
				} else if (numM[i] > numN[middle]){ // 찾으려는 숫자보다 middle이 작다면
					start = middle + 1;
				} else { // 숫자를 찾았다면
					sb.append("1\n");
					break;
				}
			}
			if(start > end) {
				sb.append("0\n");
			}
		}
		System.out.println(sb.toString());
	}
}