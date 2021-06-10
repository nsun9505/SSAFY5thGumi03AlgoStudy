import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R,C,N;
	static char[][] map;
	static int[][] num;
	static int[] dx = {0,0,-1,1}; 
	static int[] dy = {-1,1,0,0}; 
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		num = new int[R][C];
		
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=str.charAt(j);
				//현재 폭탄3초가 될 때 터짐
				if(map[i][j]=='O') {
					num[i][j]=3;
				}
			}
		}
		
		int time=1;
		while(time<N) {
			time++;
			//폭탄 설치
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(map[i][j]=='.') {
						map[i][j]='O';
						num[i][j]=time+3;
					}
				}
			}
			if(time==N) 
				break;
			time++;
			bomb(time);
		}
//		System.out.println("===============================");
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void bomb(int time) {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(num[i][j]==time) {
					map[i][j]='.';
					for(int d=0;d<4;d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						if(nx<0 || ny<0 || nx>=R ||ny>=C) continue;
						map[nx][ny]='.';
					}
				}
			}
		}
	}
}