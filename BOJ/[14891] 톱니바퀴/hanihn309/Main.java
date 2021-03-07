package bkj_14891; // 210305

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준 14891번 : 톱니바퀴
	static StringBuilder[] wheel = new StringBuilder[5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 1; i <= 4; i++) { // N극 = 0, S극 = 1
			wheel[i] = new StringBuilder(br.readLine());
		}
		
		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		int sum = 0; // 톱니바퀴의 12시 방향이 S극이면 1번 톱니바퀴는 1점, 2번 톱니바퀴는 2점, 3번 톱니바퀴는 4점, 4번 톱니바퀴는 8점
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken()); // 회전시킨 톱니바퀴의 번호
			int direction = Integer.parseInt(st.nextToken()); // 회전 방향 : 시계방향 = 1, 반시계방향 = -1
		
			int[] turnIndex = new int[4]; // 회전시킬 톱니바퀴의 번호를 저장할 인덱스 배열
			int i = 0;
			
			turnIndex[i++] = num;
			
			int next = num-1;
			int standard = num;
			
			while(next >= 1) { // num번째 톱니를 기준으로 왼쪽을 다 회전시키기
				if (wheel[next].charAt(2) == wheel[standard].charAt(6)) { // 극이 같다면
					break; // 극이 같다면, 나보다 왼쪽 애들은 전부 회전하지 않음
				} else { // 극이 다르다면, 시계방향 또는 반시계 방향으로 돌림
					turnIndex[i++] = next;
				}
				next--;
				standard--;
			}
			
			next = num+1;
			standard = num;
			
			while(next <= 4) { // num번째 톱니를 기준으로 오른쪽을 다 회전시키기
				if (wheel[standard].charAt(2) == wheel[next].charAt(6)) { // 극이 같다면
					break; // 극이 같다면, 나보다 오른쪽 애들은 전부 회전하지 않음
				} else { // 극이 다르다면, 시계방향 또는 반시계 방향으로 돌림
					turnIndex[i++] = next;
				}
				next++;
				standard++;
			}
			
			int turnDirection = direction;
			
			for(int j = 0; j < 4; j++) {
				if (turnIndex[j] != 0) {
					if (turnIndex[j] <= num) { // num번째 톱니바퀴보다 왼쪽의 톱니바퀴들끼리 회전 방향 toggle
						rotation(turnDirection, turnIndex[j]);
						turnDirection *= (-1);
					} else { // num번째 톱니바퀴보다 오른쪽의 톱니바퀴들끼리 회전 방향 toggle
						direction *= (-1);
						rotation(direction, turnIndex[j]);
					}
				}
			}
		}
		
		for(int i = 1; i <= 4; i++) {
			if (wheel[i].charAt(0) == '1') {
				sum += Math.pow(2, i-1);
			}
		}
		
		System.out.println(sum);
	}
	
	static void rotation(int direction, int num) {
		StringBuilder sb = new StringBuilder("77777777");
		
		if (direction == 1) { // 시계방향이라면, 하나씩 오른쪽으로 이동하면 됨
			for(int i = 0; i < 7; i++) {
				sb.setCharAt(i+1, wheel[num].charAt(i));
			}
			sb.setCharAt(0, wheel[num].charAt(7));
		} else { // 반시계방향이라면, 하나씩 왼쪽으로 이동하면 됨
			for(int i = 0; i < 7; i++) {
				sb.setCharAt(i, wheel[num].charAt(i+1));
			}
			sb.setCharAt(7, wheel[num].charAt(0));
		}
		
		wheel[num] = sb;
	}
}
