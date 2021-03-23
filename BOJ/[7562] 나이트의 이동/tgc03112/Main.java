import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{	//나이트의 이동
	static int l;
	static int nightX;
	static int nightY;
	static int targetX;
	static int targetY;
	static boolean[][] v;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			l = sc.nextInt();	//체스판 한 변의 길이
			
			nightX = sc.nextInt();
			nightY = sc.nextInt();
			
			targetX = sc.nextInt();
			targetY = sc.nextInt();
			
			v = new boolean[l][l];

			System.out.println(bfs());
		}
	}

	private static int bfs() {
		Queue<Data> q = new LinkedList<Data>();
		q.add(new Data(nightX, nightY,0));
		v[nightX][nightY]=true;
		
		int[] dx = {-2,-1,1,2,2,1,-1,-2};
		int[] dy = {1,2,2,1,-1,-2,-2,-1};
		while(!q.isEmpty()) {
			Data d = q.poll();
			if(d.x==targetX && d.y==targetY) return d.cnt;
			int nx,ny,cnt;
			for(int i=0;i<8;i++) {
				nx = d.x+dx[i];
				ny = d.y+dy[i];
				cnt = d.cnt+1;
				if(nx<0 || ny<0 || nx>=l || ny>=l) continue;
				if(v[nx][ny]) continue;
				q.add(new Data(nx, ny, cnt));
				v[nx][ny]=true;
			}
		}
		return 0;
	}
}
class Data{
	int x;
	int y;
	int cnt;
	public Data(int x, int y,int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}