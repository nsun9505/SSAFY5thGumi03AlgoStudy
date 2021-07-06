# [6593] 상범빌딩

## 분류

## 코드
```java
package BOJ6593_상범빌딩;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	
	static int L,R,C;
	static char[][][] B;
	static Queue<Pos> q;
	static boolean[][][] v;
	static Pos END;
	static class Pos{
		int f, y, x;
		
		public Pos(int f, int y, int x) {
			this.f = f;
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
						
			if(L == 0 && R == 0 && C == 0) break;
			
			B = new char[L][R][C];
			q = new LinkedList<>();
			v = new boolean[L][R][C];
			
			for(int f = 0; f < L; f++) {
				String input;
				for(int y = 0; y < R; y++) {
					input = br.readLine();
					B[f][y] = input.toCharArray();
					for(int x = 0; x < C; x++) {						
						if(B[f][y][x] == 'S') {
							q.offer(new Pos(f,y,x));
							v[f][y][x] = true;
						}
						else if(B[f][y][x] == 'E') END = new Pos(f,y,x);
					}
				}
				input = br.readLine();
			}
			
			sb.append(bfs());
			
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
		

	}
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{0,0},{0,0}};
	static int[] upDown = {-1,1};
	private static String bfs() {
		int t = 0;
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			while(size-- > 0) {
				
				Pos p = q.poll();
				int f = p.f;
				int y = p.y;
				int x = p.x;
				
				if(f == END.f && y == END.y && x == END.x) return "Escaped in "+t+" minute(s).\n";
				
				for(int i = 0; i < 4; i++) {
					int ny = y + dir[i][0];
					int nx = x + dir[i][1];
					
					if(dirCheck(ny,nx)) continue;
					
					isPossible(f,ny,nx);
				}
				
				for(int i = 0; i < 2; i++) {
					int nf = f + upDown[i];
					if(floorCheck(nf)) continue;
					
					isPossible(nf,y,x);
				}
				
			}
			t++;
		}
		
		return "Trapped!\n";
	}
	
	private static void isPossible(int f, int y, int x) {
		if(v[f][y][x] || B[f][y][x] == '#') return;
		
		q.offer(new Pos(f,y,x));
		v[f][y][x] = true;
	}
	private static boolean dirCheck(int ny, int nx) {	
		return ny < 0 || nx < 0 || ny >= R || nx >= C;
	}
	private static boolean floorCheck(int nf) {
		// TODO Auto-generated method stub
		return nf < 0 || nf >= L;
	}

}

```

## 문제풀이

- 3차원 배열과 bfs를 활용하였습니다