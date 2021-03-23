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
	
	static int L, min, endI, endJ;
	static int[][] board;
	static int[][] visited;
	static int[] dI = {-2, -1, 1, 2, 2, 1, -1, -2}, dJ = {1, 2, 2, 1, -1, -2, -2, -1};
	static Queue<Point> queue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++ ) {
			L = Integer.parseInt(br.readLine()); // 체스판 한 변의 길이 (체스판 크기 : L x L)
			board = new int[L][L];
			visited = new int[L][L];
			min = Integer.MAX_VALUE;
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
		
			sb.append(min + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs() {
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			int currentI = p.i, currentJ = p.j;
			
			if (currentI == endI && currentJ == endJ) { // 나이트가 이동하려고 한 칸에 도착했다면
				min = Math.min(min, visited[currentI][currentJ]);
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