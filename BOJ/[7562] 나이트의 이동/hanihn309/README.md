# [7562] 나이트의 이동

## 분류
> BFS

## 코드
package bkj_7562; // 210323

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 백준 7562번 : 나이트의 이동
	static class Point {
		int i;
		int j;
		
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	static int L, endI, endJ;
	static int[][] visited;
	static int[] dI = {-2, -1, 1, 2, 2, 1, -1, -2}, dJ = {1, 2, 2, 1, -1, -2, -2, -1};
	static Queue<Point> queue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++ ) {
			L = Integer.parseInt(br.readLine()); // 체스판 한 변의 길이 (체스판 크기 : L x L)
			
			visited = new int[L][L];
			queue = new LinkedList<>();
			
			for(int i = 0; i < L; i++) {
				Arrays.fill(visited[i], -1); // 길이 구하는 배열로도 사용하기 위해 -1로 전부 채워놓기
			}
			
			st = new StringTokenizer(br.readLine());
			int startI = Integer.parseInt(st.nextToken()), startJ = Integer.parseInt(st.nextToken());
			queue.offer(new Point(startI, startJ));
			visited[startI][startJ] = 0;
			
			st = new StringTokenizer(br.readLine());
			endI = Integer.parseInt(st.nextToken()); endJ = Integer.parseInt(st.nextToken());
		
			bfs();
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs() {
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			int currentI = p.i, currentJ = p.j;
			
			if (currentI == endI && currentJ == endJ) { // 나이트가 이동하려고 한 칸에 도착했다면
				sb.append(visited[currentI][currentJ] + "\n");
				break;
			}
			
			for(int k = 0; k < 8; k++) { // (currentI,currentJ)에 대해 이동할 수 있는 모든 점을 queue에 넣기
				int nextI  = currentI + dI[k], nextJ = currentJ + dJ[k];
				
				if (nextI < 0 || nextJ < 0 || nextI >= L || nextJ >= L) { // 범위 넘으면
					continue;
				}
				
				if (visited[nextI][nextJ] != -1) { // 이미 방문했던 곳이면
					continue;
				}
				
				queue.offer(new Point(nextI, nextJ));
				visited[nextI][nextJ] = visited[currentI][currentJ] + 1;
			}
		}
	}
}

## 문제풀이
- bfs문제입니다. 같은 레벨에 있는 모든 경우를 고려한 후, 다음 레벨로 넘어갑니다. 이 경우, 시작점에서부터 8방향으로 탐색해서 이동 가능한 모든 점을 queue에 넣으면 됩니다.
- queue에 값이 없을 때까지 while문을 돌면서 queue에서 각각 넣은 순서대로 값을 뽑아내 가능한 경우를 모두 탐색해 다시 offer해줍니다.
- 어짜피 체스판의 각 점은 (x, y) 두 점으로 이루어져있기 때문에 class를 만들지 않고 순서대로 2개씩 offer하고 2개씩 poll해도 되지만, 직관적으로 바로 보이기 위해 Point class를 선언해 queue에 넣어주었습니다.
- 이 때, 방문 체크 배열은 boolean형으로 하지 않고, 이동 거리를 저장하면서 방문 여부를 체크하기 위해 int형으로 선언했습니다. queue에 offer한다는 건 이동 가능하단 소리이므로 그 때 visited 배열의 값을 1 증가시켜 주면 됩니다.
- poll한 값이 나이트가 이동하고자 하는 좌표와 일치한다면, 그 때의 visited 배열의 값을 출력해주면 됩니다.