package bkj_16236; // 210318

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 백준 16236번 : 아기 상어
	static class Shark { // 상어의 정보를 저장하는 클래스
		int i, j; // 위치
		int size = 2; // 상어의 크기
		int count = 0; // 상어가 잡아먹은 물고기의 수 (자기의 크기와 같은 수의 물고기를 먹으면 크기가 커진 후, count는 초기화)
	}
	
	static class Fish implements Comparable<Fish> { // 잡아먹을 물고기의 위치 or 상어가 이동할 위치를 저장하는 클래스
		int i, j; // 위치
		int distance; // 물고기와 상어 사이의 거리
		
		public Fish(int i, int j, int distance) { // 잡아먹을 물고기라면 distance까지 같이 저장
			super();
			this.i = i;
			this.j = j;
			this.distance = distance;
		}
		
		public Fish(int i, int j) { // 상어가 이동할 위치라면 위치 정보만 저장
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.distance == o.distance) {
				if (this.i == o.i) {
					return this.j - o.j;
				} else {
					return this.i - o.i;
				}
			} else {
				return Integer.compare(this.distance, o.distance);
			}
		}
	}
	
	static Shark shark;
	static int N, result;
	static int[][] map;
	static int[] dI = {-1, 0, 1, 0}, dJ = {0, 1, 0, -1}; // 위부터 시계방향으로 이동
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		shark = new Shark();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 9) { // 아기 상어의 위치 찾기
					shark.i = i; shark.j = j; // 상어의 위치 저장
					map[shark.i][shark.j] = 0; // 상어의 위치는 다시 0으로 바꿔줌
				}
			}
		}
		
		while(dfs()) { }
		
		System.out.println(result);
	}

	private static boolean dfs() {
		Queue<Fish> queue = new LinkedList<>(); // 사방 탐색으로 상어가 이동할 수 있는 위치를 저장하는 queue
		
		int[][] visited = new int[N][N]; // 방문 체크를 위한 배열이지만, distance 관리를 위해 int형으로 선언
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], -1); // distance 관리를 위해 visited 배열은 -1로 초기화
		}
		
		// 현재 위치를 queue에 offer
		queue.offer(new Fish(shark.i, shark.j)); // while문 안에서, queue의 원소를 하나씩 빼면서 사방탐색
		visited[shark.i][shark.j] = 0; // 현재 위치는 이동 거리로 count 되지 않으니까 0으로.
		
		ArrayList<Fish> list = new ArrayList<>(); // 상어가 먹을 수 있는 물고기의 정보를 저장해놓는 list
		
		Fish current; // current를 기준으로 사방탐색 실행
		int nextI, nextJ;
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int k = 0; k < 4; k++) {
				nextI = current.i + dI[k]; nextJ = current.j + dJ[k];
				
				if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) { // 범위 체크
					continue;
				}
				
				if (visited[nextI][nextJ] != -1) { // 방문 체크
					continue;
				}
				
				if (shark.size < map[current.i][current.j]) { // 상어보다 큰 물고기가 있는지 체크
					continue;
				}
				
				// 여기까지 내려오면 일단 방문은 할 수 있음. (물고기가 있는 경우 or 물고기가 없는 경우 모두 가능한 상태)
				visited[nextI][nextJ] = visited[current.i][current.j] + 1; // 현재 위치에서 next 위치까지는 거리가 1 큼
				queue.offer(new Fish(nextI, nextJ)); // 이동한 위치에서 다시 사방탐색을 해야하니까, queue에 넣어주기
				
				
				// 물고기가 있는 경우 && 물고기를 먹을 수 있는 경우
				if (map[nextI][nextJ] != 0 && shark.size > map[nextI][nextJ]) {
					list.add(new Fish(nextI, nextJ, visited[nextI][nextJ])); // 먹이 list에 추가
				}
			}
		}
		
		// 먹을 수 있는 먹이를 list에 모두 담은 후
		
		if (list.size() == 0) { // 먹을 수 있는 먹이가 없는 경우
			return false;
		}
		
		Collections.sort(list); // 먹을 수 있는 물고기가 2마리 이상일 때 조건 : 거리가 가장 가까운 순 > 가장 위쪽 > 가장 왼쪽
		Fish feed = list.get(0); // 정렬을 했다면, list의 제일 앞에 있는 값이 먼저 먹을 먹이
		
		result += feed.distance; // 상어와 먹이가 떨어진 거리만큼 초 증가
		
		shark.i = feed.i; shark.j = feed.j; // 잡아먹었으니, 위치를 먹이의 위치로 변경
		shark.count++; // 상어가 먹은 먹이의 갯수 늘려주기
		
		map[feed.i][feed.j] = 0; // 잡아먹었으니, 해당 먹이의 위치는 0으로.
		
		if (shark.size == shark.count) { // 상어의 크기와 같은 수로 먹이를 먹었다면, 상어의 크기 늘려주기
			shark.size++;
			shark.count = 0; // 크기가 늘어나면, 먹은 먹이의 수는 다시 count 해야하므로 0으로 초기화
		}
		
		return true;
	}
}