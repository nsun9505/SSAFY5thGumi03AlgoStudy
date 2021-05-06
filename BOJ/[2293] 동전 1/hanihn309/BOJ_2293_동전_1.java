import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전_1 {// 210506
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 동전 종류의 개수
		int k = Integer.parseInt(st.nextToken()); // 가치의 합
		
		int[] D = new int[k+1]; // D[i]는 i원의 가치를 만들 수 있는 동전 종류의 조합 개수
		D[0] = 1; // '어떤 종류' 동전 하나를 내는 방법은 1가지니까 1로 초기화
		
		for(int j = 0; j < n; j++) {
			int coin = Integer.parseInt(br.readLine());
			
			for(int i = coin; i <= k; i++) {
				D[i] = D[i] + D[i- coin];
				// coin원으로 i원을 만드는 가짓수 = coin원 동전을 내지 않은 경우 + coin원 동전을 내는 경우
			}
		}
		
		System.out.println(D[k]);
	}

}
