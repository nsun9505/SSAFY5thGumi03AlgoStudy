import java.util.*;
import java.io.*;

public class Main {
	static int L,R,C;
	static char building[][][];	
	static boolean vistied[][][];	
	static boolean res;
	static Queue<position> q;
	static int dz[] = {0,0,0,0,1,-1};
	static int dx[] = {-1,0,1,0,0,0};
	static int dy[] = {0,1,0,-1,0,0};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());	//상범 빌딩의 층 수
			R = Integer.parseInt(st.nextToken());	//행
			C = Integer.parseInt(st.nextToken());	//열
			
			if(L==0 && R==0 && C==0) {
				break;
			}
			
			res = true;
			q = new LinkedList<position>();
			building = new char[L][R][C];
			vistied = new boolean[L][R][C];
			
			for(int k=0;k<L;k++) {
				for(int i=0;i<R;i++) {
					String str = br.readLine();
					for(int j=0;j<C;j++) {
						building[k][i][j] = str.charAt(j);
						if(building[k][i][j]=='S') {
							position start = new position(k, i, j, 0);
							vistied[k][i][j] = true;
							q.add(start);
						}
					}
				}
				String tmp = br.readLine();	//한 줄
			}
			bfs();
			if(res) {
				System.out.println("Trapped!");
			}
		}
	}

	private static void bfs() {

		while(!q.isEmpty()) {
			position now = q.poll();
			int z = now.z;
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			for(int d=0;d<6;d++) {
				int nz = z+dz[d];
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nz<0||nx<0||ny<0||nz>=L||nx>=R||ny>=C) {
					continue;
				}
				if(building[nz][nx][ny]=='E') {	//도착
					res=false;
					cnt++;
					System.out.println("Escaped in "+cnt+" minute(s).");
					q.clear();
					break;
				}
				if(building[nz][nx][ny]=='.' && !vistied[nz][nx][ny]) {	//안도착
					position pos = new position(nz, nx, ny, cnt+1);
					vistied[nz][nx][ny]=true;
					q.add(pos);
				}
			}
		}
	}
}

class position{
	int z,x,y,cnt;

	public position(int z, int x, int y, int cnt) {
		this.z = z;
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}