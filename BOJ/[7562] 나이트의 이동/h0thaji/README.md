# [7562] 나이트의 이동

## 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int END = -1; // 도착지점
	static int T, I, res; // 테스트케이스, 체스판 크기, 결과값(이동 횟 수)
	static int[][] chess; // 체스판 배열
	static Queue<coordinate> queue = new LinkedList<>(); // 탐색 할 큐
	
	static class coordinate{ // 좌표 클래스
		int y;
		int x;
		
		public coordinate(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) { // 테스트케이스 T 만큼 반복
			
			I = Integer.parseInt(br.readLine()); // 체스판 크기 입력
			chess = new int[I][I]; // 체스판 생성
			res = 0; // 결과값 초기화
			queue.clear(); // 큐 초기화 , bfs하다가 END를 마주치면 큐가 채워진 상태로 나옴으로
			
			st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken()); // 시작 Y 좌표
			int startX = Integer.parseInt(st.nextToken()); // 시작 X 좌표

			st = new StringTokenizer(br.readLine());
			int endY = Integer.parseInt(st.nextToken()); // 끝 Y 좌표
			int endX = Integer.parseInt(st.nextToken()); // 끝 X 좌표
			
			if(!((startY == endY) && (startX == endX))) { // 시작점과 끝점이 다르면
				queue.offer(new coordinate(startY, startX)); // 큐에 시작점 offer
				chess[startY][startX] = 1; // 시작점 방문체크
				chess[endY][endX] = END; // 체스판에 END 좌표 찍어두기 -1
				bfs(); // bfs				
			}
									
			sb.append(res).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	static int[][] dir = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}}; 
	// 1시 2시 , 4시 5시 , 7시 8시, 10시 11시
	// 움직일 방향
	private static void bfs() {
		
		int y,x, size; // y 좌표, x 좌표, 큐 사이즈
		while(!queue.isEmpty()) { // 큐가 비어 있지 않다면
			
			size = queue.size(); // 큐 사이즈
			res++; // 나이트 이동
			
			while(--size>=0) {	// 사이즈 만큼 반복			
				y = queue.peek().y; // 현재 큐의 맨 앞 좌표 y값
				x = queue.poll().x; // 현재 큐의 맨 앞 좌표 x값가져오고 poll
								
				for (int i = 0; i < 8; i++) { // 8방향 확인
					int ny = y + dir[i][0];
					int nx = x + dir[i][1]; // 좌표 ny,nx에 삽입
					
					if(check(ny,nx)) continue; // 체스판 크기를 넘어가거나 방문한 곳이면 continue;
					if(chess[ny][nx] == END) return; // END이면 return, 끝
					chess[ny][nx] = 1; // 방문 체크
					queue.offer(new coordinate(ny, nx)); // 이동할 수 있는 칸이니까 객체 생성후 큐 삽입
					
				}
				
			}
		
		}
		
	}
	private static boolean check(int y, int x) {
		
		return y < 0 || y >= I || x < 0 || x >= I || chess[y][x]==1;
	}

}


```

## 문제풀이

- 체스판의 출발 칸과 도착 칸이 입력으로 주어지고 출발 칸으로 부터 나이트가 도착 칸까지 최소 몇 번만에 이동할 수 있는지 출력하는 문제입니다.

- 해당문제는 dfs로 풀리지가 않았습니다. 첫 번째 경우로 출발을 하게 되면 안되는 경우면 파고파고파고파고파고파고파고 를 반복하다가 펑 터졌던것 같습니다.

- 그래서 bfs로 해결했습니다.

  - 시작점과 출발점 비교

    - 시작점과 출발점이 같다면 최소 0번만에 이동 가능하기에 bfs 탐색을 하지않고 바로 결과값을 출력시킵니다.
    - 다르다면 bfs탐색을 합니다.

  - bfs탐색

    - 탐색 전

      -  시작점을 큐에 삽입합니다.
      -  chess 배열에 시작점을 표시 -> 방문체크

    - 탐색 시작

      - 탐색을 시작했다는 것은 한번 움직였다는 것이니 이동 횟수 증가(res++)
      - 몇 번 이동했는지 확인 하기위해 큐 사이즈만큼 반복을 해줍니다 => 같은 깊이의 칸을 확인하기 위해서
      - 나이트가 움직일 수 있는 칸은 총 8칸이므로 다 탐색을 해줍니다.
        - chess배열의 크기를 벗어나거나 방문한 곳이면 continue
        - END (도착 지점) 이면 탐색을 멈춥니다. 
          - 최소 이동 횟 수를 찾는 거지만 bfs이므로 탐색을 전부 안해보고 탐색을 끝내면 됩니다.
          - 같은 깊이의 노드를 확인 하는 것이기 때문입니다. 즉 , 이동 횟 수 1번에 이동할 수 있는 칸들 전부 확인 , 2번에 이동할 수 있는 칸들 전부확인 ..... 3,4,5.. 이런식으로 이동 횟 수가 작은 것부터 이미 확인하면서 오기 때문입니다.
        - 이동 할 수 있는 칸이면 큐에 offer 해줍니다
      - 같은 깊이(이동 횟수)의 나이트가 움직일 수 있는 칸들을 확인이 끝났는데 END지점에 도착을 한 것이 아니라면 탐색을 반복합니다.

      