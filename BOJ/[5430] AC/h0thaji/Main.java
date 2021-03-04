package BOJ5430_AC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int T,N; // 테스트케이스 ,배열에 들어있는 수
	static String P;// 수행할 함수
	static String[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine()); // 테스트케이스 입력

		for (int tc = 0; tc < T; tc++) {

			P = br.readLine(); // 수행할 함수 P
			N = Integer.parseInt(br.readLine()); // 배열 입력 수
			

			String input = br.readLine(); // 배열에 들어갈 수 [1,2,3,4]
			input = input.substring(1,input.length()-1); //substring을 이용해 [ / ] 잘라줌 => 1,2,3,4

			arr = input.split(","); // String[] arr에 , split하여 넣어줌
			
			boolean reverse = false; // R을 판단하는 boolean

			int start = 0, end = N-1; //시작점 , 끝점

			int pSize = P.length(); // 수행할 함수 P의 문자열 길이

			for (int i = 0; i < pSize; i++) { // P만큼 반복문

				if(N < 0) break; // 배열에 담긴 수가 -1이하가 되면 더이상 수행할 수가 없으므로 break;
				switch(P.charAt(i)) { // 수행할 P
				case 'R': // R일경우 거꾸로 출력을 해야하므로 거꾸로인지 확인
					reverse = !reverse; // reverse의 반대값을 넣어줌 true 이면 false , false이면 true
					break;
				case 'D':
					if(reverse) end--; // 거꾸로 출력이면 끝점을 빼줌
					else start++; // 거꾸로 출력이 아니라면 시작점을 더해줌
					N--; // D를 한번 할때마다 배열에 담긴 수를 빼줌으로 N--
					break;
				}
			}
			
			if(N<0) { // N이 -1이하라는 이유는 N이 0인데 D를 수행했으므로 error 출력
				sb.append("error\n");	
				continue;
			}

			//출력을 입력받았을때와 같이 [1,2,3,4]를 해줘야하므로
			sb.append("["); // append [
			if(!reverse) { //정상 출력일때
				for (int i = start; i <= end; i++) { //시작점부터 끝점까지

					sb.append(arr[i]).append(","); //append 1,2,3,4
				}
			}else { // 거꾸로 출력일때
				for (int i = end; i >= start; i--) { //끝점부터 시작점까지

					sb.append(arr[i]).append(","); // append 4,3,2,1
				}
			}

			if(N != 0) sb.deleteCharAt(sb.length()-1); // 마지막에 붙는 ,를 제거 단, N=0이면 []을 출력시켜줘야하므로 ,제거를 하지않음
			sb.append("]\n"); // append ]\n
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
