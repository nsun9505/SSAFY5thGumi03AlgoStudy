package BOJ14719_빗물;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int H,W;
	static int[][] arr;
	static int res = 0;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		arr = new int[H][W];
		st = new StringTokenizer(br.readLine());
		
		// 블록이 있는 곳은 0으로 빗물이 있는 곳은 1로 채운다.
		for(int i = 0; i < W; i++) {
			int k = H-Integer.parseInt(st.nextToken());
			for(int j = 0; j < k; j++) {
				arr[j][i] = 1;
			}
		}
		
		// 0과 0 사이에 있는 곳은 빗물이 차있는 곳만 더해주면 된다
		for(int i =0; i < H; i++) {
			int zero = -1; // 벽이 있는 배열 인덱스를 저장해주는 변수
			
			for(int j = 0; j < W; j++) {
				if(arr[i][j] == 0) { // 0일때
					if(zero != -1) res+= j - zero - 1; // -1, 처음 0이 아니면 해당 위치의 0과 이전위치의 0을 빼고 -1해준다
					
					zero = j; // 해당 위치로 바꾸어준다.
					
				}
			}
					
		}
		
		bw.write(String.valueOf(res));
		bw.flush();
		bw.close();
		br.close();

	}



}
