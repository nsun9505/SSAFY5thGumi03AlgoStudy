# [14891] 톱니바퀴

## 분류
> 구현
>
> 시뮬레이션

## 코드
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


## 문제 풀이
또 많은 시행착오를 겪었습니다... 시뮬레이션을 해야하나 말아야하나 고민을 하다가, 짜다보니 해야한다는 생각이 들어서 시뮬레이션에 맞춰 코드를 작성했습니다. 일단 여러번 풀다 막혀보니 중요한 포인트가 보이더라구요. num번째 톱니바퀴를 회전을 해야하지만, 회전 한 다음 옆의 톱니바퀴와 극을 맞춰보는게 아니라, 일단 현재 상태에서 극의 상태를 비교해서 회전 시킬 톱니바퀴를 다 고른 후 다음 num 톱니바퀴를 고르기 직전에 회전을 시켜야 한다는 것입니다. 그리고 내 옆의 톱니바퀴와 극이 같아서 돌지 않는다면, 돌지 않는 옆의 모든 톱니바퀴들은 회전 하지 않는다는 점을 이용해 while문에서 break의 조건으로 사용했습니다. 그 다음은 실제로 시뮬레이션을 위해 톱니바퀴의 상태를 돌려야하는데 처음에는 index 배열을 따로 두고 하려고 했지만 머리가 넘 아파서 그냥 바퀴 상태 자체를 돌려야겠다고 생각했고, 배열을 돌리는 것보다 StringBuilder에서 제공하는 setCharAt 메소드를 사용하기로 결정했습니다.