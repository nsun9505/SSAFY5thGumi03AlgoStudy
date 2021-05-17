import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660_구간_합_구하기_5 { // 210517
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 표의 크기
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		int[][] map = new int[N+1][N+1];
		int[][] D = new int[N+1][N+1]; // (1,1) 부터 (i,j)까지 합을 각각 저장해놓은 2차원 배열
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				D[i][j] = D[i-1][j] + D[i][j-1] - D[i-1][j-1] + map[i][j];
				// 현재 D의 값은, 위의 사각형 + 왼쪽의 사각형 - 두 번 겹쳐진 사각형 + 현재 값. 그림으로 이해하면 편함!
			}
		}
		
		for(int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = D[x2][y2] - D[x1-1][y2] - D[x2][y1-1] + D[x1-1][y1-1];
			// 내가 찾고자하는 (x1,y1) ~ (x2, y2)까지의 누적합은,
			// (1,1)~(x2,y2)까지의 누적합 - (1,1)~(x1-1,y2)까지의 누적합 - (1,1)~(x2,y1-1)까지의 누적합 + (1,1)~(x1-1,y1-1)까지의 누적합
			
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
	}
}