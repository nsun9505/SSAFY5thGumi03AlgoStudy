package BOJ11047_동전0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,K,res; // 동전의 종류, 가치의 합, 동전 개수의 최소값
	static int[] coin; // 입력받을 동전 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		for (int i = N-1; i > -1; i--) { // 금액이 큰 동전부터 찾기위해 입력받은 값을 거꾸로 배열에 담아줌
			// 동전은 작은 금액부터 입력이 된다.
			coin[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N && K!=0; i++) { // 가치가 큰 동전 부터 작은 동전 순, K가 0이면 동전 개수의 최소값을 찾은 것이기 때문에 반복문 종료
			if(K >= coin[i]) {	// K가 해당 동전보다 크거나 같으면 나눌 수 있으므로
				res += K/coin[i]; // 결과값에 동전의 수를 더해줌
				K %= coin[i]; // 해당 동전의 가치로 나누고 나머지가 총 가치가 됨			
			}
		}
		
		sb.append(res);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
