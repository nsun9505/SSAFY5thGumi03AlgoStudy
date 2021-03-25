package BOJ7562_나이트의이동;

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
	static int T, I, res; // 테스트케이스, 체스판 크기, 결과값
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
			res++; // 한번 움직일거니까
			
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
