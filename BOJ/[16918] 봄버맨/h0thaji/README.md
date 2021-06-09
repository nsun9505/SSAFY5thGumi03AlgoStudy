# [16918] 봄버맨

## 분류
>

## 코드
```java
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
		
		while(!q.isEmpty()) { // 폭탄 터짐
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
	private static boolean check(int y, int x) { //격자판 벗어나는지 체크	
		return y < 0 || y >= R || x < 0 || x >= C;
	}
	
	private static void planting() {// 폭탄 설치
		for(int i =0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == '.') { // 폭탄이 없는 곳에 폭탄설치
					arr[i][j] = 'O';					
				}else {
					q.offer(new Pos(i,j)); // 심어져있는 폭탄들은 다음에 터질것이므로 큐에 삽입
				}
			}
		}
		
	}

}

```

## 문제풀이

- 반복문을 1부터 시작하여 N(시간)번만큼 돌립니다.
  - 1부터 시작하는 이유는 처음 1초동안은 아무것도 하지 않기 때문입니다.
  - i가 짝수번째 일 때는 처음 설치한 폭탄이 터지는 시간이므로 4방향으로 폭탄을 터트립니다.
  - 이때, 모든 폭탄이 연쇄적으로 터지는 것이아닌 해당 폭탄 포함 4방향으로만 터집니다.
  - i가 홀수번째 일 때는 폭탄이 없는 공간에 폭탄을 설치하는 작업을 합니다
  - 또한 미리 설치되어있던 폭탄은 다음 초때 터질 것이기 때문에 큐에 삽입합니다.

