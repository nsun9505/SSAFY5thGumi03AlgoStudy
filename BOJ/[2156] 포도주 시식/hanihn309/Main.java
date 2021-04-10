package bkj_2156_포도주_시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n];
		
		for(int i = 0; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		if (n == 1) {
			 System.out.println(wine[0]);
		} else if (n == 2) {
			System.out.println(wine[0] + wine[1]);
		} else {
			int[] D = new int[n];
			
			D[0] = wine[0]; D[1] = wine[0] + wine[1];

			// 3번째 wine 잔까지 최대 이익 => OOX, XOO, OXO 중 하나
			D[2] = Math.max(D[1], D[0] + wine[2]);
			D[2] = Math.max(D[2], wine[1] + wine[2]);
			
			// OOX, OXO, XOO
			// 1) OOX : i번째 잔 선택 안할 때 => D[i] = D[i-1]
			// 2) OXO : i번째 잔 선택 할 떄 => D[i] = D[i-2] + wine[i]
			// 3) XOO : i번째 잔 선택 할 떄 => D[i] = D[i-3] + wine[i-1] + wine[i]
			for(int i = 3; i < n; i++) {
				D[i] = Math.max(D[i-1], D[i-2] + wine[i]);
				D[i] = Math.max(D[i], D[i-3] + wine[i-1] + wine[i]);
			}
			System.out.println(D[n-1]);
		}
	}
}