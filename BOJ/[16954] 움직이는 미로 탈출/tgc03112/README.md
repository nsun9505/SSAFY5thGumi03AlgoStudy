# [16954] 움직이는 미로 탈출

## 분류

> BFS

## 코드

```java
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

```

## 문제풀이

bfs로 문제를 풀었습니다.

처음 출발지점 (7,0)부터 시작해 도착지점 (0,7)에 도착할 수 있으면 res를 1로 바꿔줍니다.

저는 종료조건을 (0,7)에 도착하면 종료되게 했지만 그냥 맨 윗줄에만 도달하면 1를 리턴해줘도됩니다. (더이상 내려올 벽이 없기 때문)

욱제를 먼저 bfs로 움직여주고 벽을 한줄씩 내려주는 방식으로 진행했습니다.

실수한부분은 습관적으로 8방 탐색으로 코드를 짰는데 여기서는 욱제가 현재 위치에 서있을 수도 있다는 점입니다. 이 점으로 고려해서 코드를 짜줘야됩니다.
