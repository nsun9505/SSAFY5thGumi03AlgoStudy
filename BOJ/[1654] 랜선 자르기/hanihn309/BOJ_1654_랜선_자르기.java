import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654_랜선_자르기 { // 210421
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
		int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
		int[] LAN = new int[K];
		
		for(int i = 0; i < K; i++) {
			LAN[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(LAN);
		
		long max = LAN[K-1]; // 가장 긴 랜선의 길이를 max 길이값으로 초기화
		long min = 1; // 랜선의 길이는 자연수이기 때문에 min 길이값은 1로 초기화
		long middle = 0;
		
		while(min <= max) {
			int count = 0;
			
			middle = (min + max) / 2; // middle 길이로 LAN선을 잘라봄
			
			for(int i = 0; i < K; i++) {
				count += (LAN[i] / middle); // 각 LAN선별로 잘린 LAN선의 개수를 counting
			}
			
			if(count >= N) { // N개를 만들 수 있는 랜선의 최대 길이를 구하는 문제이므로, ==로 break 걸지 않고 middle(자를 랜선의 길이) 길이를 늘려서 계속 검사해 봄
				min = middle + 1; // min을 늘리면 middle도 늘어남
			} else if (count < N) {
				max = middle - 1; // max를 줄이면 middle도 줄어듬
			}
		}
		
		System.out.println(max); // while문을 벗어난 순간은 min과 max가 교차된 순간이므로, max값이 LAN선의 최대 길이
	}
}