package BOJ14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {

	static int[][] wheel = new int[4][8], idx = {{0,2,6},{0,2,6},{0,2,6},{0,2,6}}; // 입력받을 톱니바퀴, 각 톱니바퀴의 index (idx[i][0] 12시 톱니, idx[i][1] 3시톱니 , idx[i][2]은 9시톱니)
	static int[] dir; // 돌릴 방향
	static boolean[] v; // 방문 확인
	static int K; // 회전 횟수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int cnt = 0;
		for (int i = 0; i < 4; i++) { // 톱니바퀴 입력
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				wheel[i][j] = input.charAt(j)-'0';
				cnt += wheel[i][j]; // 모든 톱니바퀴의 극이 같은지 확인
			}
		}

		K = Integer.parseInt(br.readLine()); //회전 횟수
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());

			if(cnt == 32 && cnt == 0) break; // 32면 모든 톱니바퀴가 S극 , 0이면 N극으로 같기때문에 톱니바퀴는 회전을 하나 안하나 결과값이 달라지진 않으니 break;
			int n = Integer.parseInt(st.nextToken())-1; // 톱니바퀴 번호 , 모든 배열이  0부터 시작하기 때문에 -1을 해준다
			int d = Integer.parseInt(st.nextToken());// 톱니바퀴 회전 방향
			//톱니바퀴 번호, 방향 (-1,1)
			//톱니바퀴번호에 따라서 
			v = new boolean[4]; // 방문확인배열 초기화 , 회전하는지 안하는지 체크
			dir = new int[4]; // 회전방향배열 초기화

			dir[n] = d; // 해당 톱니바퀴 회전 방향
			same(n,d); // 맞물린 톱니바퀴가 극이 다른지 체크
			rotation(); // 회전

		}

		output(); // 결과 출력

	}



	public static void same(int n,int d) { //맞물린 톱니바퀴가 극이 다른지 체크
		int pre = n-1; // 전 번호의 톱니바퀴
		int next = n+1; // 다음 번호의 톱니바퀴
		v[n] = true; // 해당 톱니바퀴 방문체크

		if(pre >= 0 && !v[pre] && wheel[pre][idx[pre][1]] != wheel[n][idx[n][2]]) { // 인덱스를 벗어나지 않고 , 방문을 하지않았으며, 전 톱니바퀴랑 맞물린지 확인이니 idx[i][1], 즉 전 톱니바퀴의 3시방향톱니와 idx[i][2], 현재 톱니바퀴 9시방향 톱니가 다른지 비교
			same(pre, -d); // 극이 다르니 이전 톱니바퀴도 반대방향으로 회전한다는 뜻 
			dir[pre] = -d; // 이전 톱니바퀴 회전방향 , 맞물린 톱니바퀴는 반대방향이므로 -를 해줌.
		}
		if(next < 4 && !v[next] && wheel[next][idx[next][2]] != wheel[n][idx[n][1]]) { // 인덱스를 벗어나지 않고 , 방문을 하지않았으며, 다음 톱니바퀴랑 맞물린지 확인이니 idx[i][2], 즉 다음 톱니바퀴의 9시방향 톱니와 idx[i][1], 현재 톱니바퀴 3시방향톱니가 다른지 비교
			same(next, -d); // 극이 다르니 다음 톱니바퀴도 반대방향으로 회전한다는 뜻 
			dir[next] = -d; // 다음 톱니바퀴 회전방향 , 맞물린 톱니바퀴는 반대방향이므로 -를 해줌.
		}
		return;
	}

	public static void rotation() { //회전 , 배열자체를 건들필요없이 인덱스만 바꿔주면됨
		for(int i = 0; i < 4; i++) {
			if(v[i]) { // i번재 톱니바퀴가 돈다면 
				for (int j = 0; j < 3; j++) { // j=0 결과값, =1 톱니바퀴 3시방향 톱니, =2 톱니바퀴 9시방향 톱니
					idx[i][j] = (idx[i][j] + (8+(-dir[i]))) % 8; // 시계방향으로 돌면 1이므로, 해당 인덱스에서 +9를 한뒤 %8을 하면 시계방향으로 돌듯이 인덱스가 바뀜
//					반시계방향으로 돌면 -1이므로, 해당 인덱스에서 +7을 한뒤 %8을 하면 반시계방향으로 돌듯이 인덱스가 바뀜
				}
			}
		}
		return;
	}

	public static void output() {
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			sum+= (wheel[i][idx[i][0]])*Math.pow(2, i); // 1점,2점,4점,8점 톱니바퀴의 번호가 올라갈수록 해당 극에 따라 2제곱으로 점수가 더해짐
		}
		System.out.println(sum); // 출력
	}

}
