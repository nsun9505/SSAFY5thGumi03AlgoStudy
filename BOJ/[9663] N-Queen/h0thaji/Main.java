package BOJ9663_NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, res; // 체스판크기와 퀸의 개수, 결과값
	static boolean[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // N 입력
		
		
		for (int i = 0; i < N; i++) { // 모든 퀸이 서로 공격을 못하는 경우 찾기
			arr = new boolean[N][N]; // 퀸 위치 표시 배열
			arr[0][i] = true; // 첫 퀸을 첫줄에 먼저 위치시킴
			dfs(1); // 탐색
		}
		System.out.println(res);
	}
	static int[][] dir = {{-1,-1},{-1,0},{-1,1}}; // 11시,12시,1시 방향
	// 다음 퀸의 위치를 놓을 때 왼쪽 대각선 위, 위, 오른쪽 대각선 위만 확인하면 됨
	// 같은 열(x좌표 위치)에 위치할 수 없고 열
	private static void dfs(int y) {
		if(y == N) { // 마지막째 줄, 즉 N개의 퀸을 다 위치 시켰으면 
			res++; // 모든 퀸이 서로 공격을 못하므로 경우 ++
			return; 
		}
		
		for (int x = 0; x < N; x++) { // 
			boolean ok = true;
			
			dirFor:for (int d = 0; d < 3; d++) {
				int ny = y + dir[d][0]; // 새 y좌표
				int nx = x + dir[d][1]; // 새 x좌표
				
				while(!check(ny,nx)) {	//체스판 끝까지 확인해야함 , 범위를 벗어나지 않으면			
					if(arr[ny][nx]) { // 놓으려는 위치에 다른 퀸이 겹치면
						ok= false;
						break dirFor; // 해당 위치에는 퀸을 못 놓으므로 break
					}	
					
					// 다음 위치확인
					ny = ny + dir[d][0]; 
					nx = nx + dir[d][1];
				}
											
			}
			if(ok) { // 놓을 수 있는 자리면
				arr[y][x] = true; // 해당 열에 퀸 배치
				dfs(y+1); // 다음 행에 올 퀸 탐색
				arr[y][x] = false; // 해당 열에 올 퀸의 경우의 수를 다 확인했으니 false
			}
		}
		
	}
	private static boolean check(int y, int x) { // 체스판 범위 넘어가는 지 체크	
		return y < 0 || x < 0 || y >= N || x >= N;
	}

}
