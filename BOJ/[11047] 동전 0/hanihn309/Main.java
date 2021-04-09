package bkj_11047_동전_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 동전 종류의 개수
		int K = Integer.parseInt(st.nextToken()); // 가치의 합
		
		int[] coin = new int[N]; // 각 동전의 가치를 저장하는 배열
		int limit = N; // 만들고자 하는 금액보다 초과되는 동전부터는 검사 안해도 되니까 그 떄의 인덱스 찾아주기
		
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			
			if (coin[i] > K)
				limit = i;
		}
		
		int[] D = new int[K+1]; // 각 금액에 대한 최소 동전의 개수
		
		for(int i = 1; i <= K; i++) {
			int min = Integer.MAX_VALUE;
			
			for(int j = 0; j < limit; j++) { // K원보다 적은 금액의 동전의 개수만큼 for문을 돌면서
				if(i >= coin[j] && D[i-coin[j]] + 1 < min) // 각 금액을 만들 수 있는 최소 동전의 개수 구하기
					min = D[i-coin[j]] + 1;
			}
			
			D[i] = min; // min 값을 다 찾으면 그 때 넣어주기
		}
		System.out.println(D[K]);
	}
}
