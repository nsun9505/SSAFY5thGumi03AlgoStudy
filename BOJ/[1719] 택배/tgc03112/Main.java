import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		int[][] path = new int[N+1][N+1];
		
		for(int i=1;i<N+1;i++) {
			Arrays.fill(map[i], 11111);
			map[i][i]=0;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis =  Integer.parseInt(st.nextToken());
			
			map[start][end]=dis;
			map[end][start]=dis;
			
			path[start][end]=start;
			path[end][start]=end;
		}
		
//		for(int i=1;i<N+1;i++) {	//출력확인
//			for(int j=1;j<N+1;j++) {
//				System.out.print(path[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int k=1;k<N+1;k++) {	//경
			for(int i=1;i<N+1;i++) {	//출
				for(int j=1;j<N+1;j++) {	//도
					if(map[i][j]>map[i][k]+map[k][j]) {	//최단경로 갱신될 때
						map[i][j] = map[i][k]+map[k][j];
						path[i][j] = path[k][j];	//경유지 등록해줌
					}	
				}
			}
		}
		
		for(int i=1;i<N+1;i++) {	//출력확인
			for(int j=1;j<N+1;j++) {
				if(i==j)
					System.out.print("- ");
				else
					System.out.print(path[j][i]+" ");
			}
			System.out.println();
		}
	}
}