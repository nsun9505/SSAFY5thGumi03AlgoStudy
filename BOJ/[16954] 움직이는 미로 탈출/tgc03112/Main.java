import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int res;
	static int[] dx = {0,1,0,-1,0,1,-1,-1,1};
	static int[] dy = {0,0,1,0,-1,1,1,-1,-1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[8][8];
		visited = new boolean[8][8];
		res = 0;
		
		for(int i=0;i<8;i++) {
			String str = br.readLine();
			for(int j=0;j<8;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		bfs();
		
		System.out.println(res);
	}

	private static void bfs() {
		Queue<position> q = new LinkedList<position>();
		
		q.add(new position(7, 0));
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				position now = q.poll();
				if(map[now.x][now.y] == '#') {	//벽이라면 넘어가
					continue;
				}
				
				for(int d=0;d<9;d++) {
					int nx = now.x+dx[d];
					int ny = now.y+dy[d];
					
					if(nx<0 || ny<0 || nx>=8 || ny>=8) {
						continue;
					}
					if(nx==0 && ny==7) {	//목적지에 도착
						res = 1;
						return;
					}
					//벽이 아니면 이동할 수 있는 위치
					if(map[nx][ny] != '#'){
						q.add(new position(nx,ny)); 
					}
				}
			}
			//벽 내리는 코드
			for(int i=7;i>=0;i--) {
				for(int j=7;j>=0;j--) {
					if(i-1<0) {
						map[i][j]='.';
					}
					else {
						map[i][j] = map[i-1][j];
					}
				}
			}
		}
	}
}

class position{
	int x,y;

	public position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
