package BOJ1920_수찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, x; 
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); // 정렬
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) { // M만큼 반복
			x = Integer.parseInt(st.nextToken());
			
			int start = 0;
			int end = N-1;
			boolean check = false; // 해당값이 있는 지 없는 지 판단해줄 boolean
			while(start <= end) { // start의 범위가 end보다 작거나 같을 때 반복
				int mid = (start+end) / 2; // mid 값은 start와 end의 가운데 값이 되어야 하므로
				
				if(arr[mid] < x) start = mid+1; // 찾을 값이 해당 mid값 보다 크다면 start를 mid+1 변경
				else if(arr[mid] > x) end = mid - 1; // 찾을 값이 해당 mid값 보다 작다면 end를 mid-1로 변경
				else { // 같다면 해당 값이 있다는 것이므로 check = true;
					check = true;
					break;
				}

			}
			
			if(check) sb.append("1").append("\n"); // 있다면 1 출력
			else sb.append("0").append("\n"); // 없다면 0 출력
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}
