package BOJ16918_봄버맨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R,C,N;
	static char[][] arr;
	static Queue<Pos> q = new LinkedList<Pos>();
	
	static class Pos{
		int y,x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];				
		
		//폭탄입력
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				arr[i][j] = input.charAt(j);			
			}
		}
		
		//폭탄 터짐 및 설치
		// i 는 1부터 시작 , 1초동안은 아무것도 안하기 때문
		for(int i = 1; i < N; i++) {
			
			if(i % 2 == 0) { // 짝수면 터짐
				boom();						
			}else { // 홀수면 설치
				planting();
			}	
		}
		
		//격자판 출력
		for(int i =0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static void boom() {
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int y = p.y;
			int x = p.x;
			
			arr[y][x] = '.';
			for(int i = 0; i < 4; i++) {
				int ny = y + dir[i][0];
				int nx = x + dir[i][1];
				
				if(check(ny,nx)) continue;
				
				if(arr[ny][nx] == 'O') {
					arr[ny][nx] = '.';
				}
			}
		}
		
	}
	private static boolean check(int y, int x) {		
		return y < 0 || y >= R || x < 0 || x >= C;
	}
	
	private static void planting() {
		for(int i =0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == '.') {
					arr[i][j] = 'O';					
				}else {
					q.offer(new Pos(i,j));
				}
			}
		}
		
	}

}
