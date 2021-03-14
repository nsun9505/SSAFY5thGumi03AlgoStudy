# [5430] AC - JAVA

## 분류
> 구현

## 코드
```java
package BOJ5430_AC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int T,N; // 테스트케이스 , 수행당할 배열 크기
	static String P;// 수행할 함수
	static String[] arr; // 수행당할 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine()); // 테스트케이스 입력

		for (int tc = 0; tc < T; tc++) { //테이스케이스만큼 반복문

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

```

## 문제 풀이

- 해당문제는 R,D가 올 때마다 배열을 직접적으로 뒤집고 삭제하게 되면 시간초과가 났습니다.

- 그러므로 아래와 같은 방법으로 풀었습니다.

  - N크기의 배열과 배열에 들어갈 수들을 입력받습니다 => arr[N]

    - 수를 입력받을 때 [1,2,3,4]와 같은 형태로 받으므로 int형으로 바로 받을 수 가 없어서 넣어줄 배열을 String 형으로 만들고, substring과 split을 이용해 해 arr배열에 삽입하였습니다.

      

  - 수행할 함수 P ( R(뒤집기), D(버리기) )를 확인합니다.

    - R(뒤집기) 는 arr배열을 뒤집습니다 => [1,2,3,4] -> [4,3,2,1]

      - 처음에는 R일 경우 시작점(0)과 끝점(N-1)을 하나씩 다 바꿨는데 시간초과가 났습니다.
      - 그래서 R일경우 boolean형인 'reverse'를 사용하여 뒤집혔는지 체크하였습니다

    - D(버리기) 는 arr배열의 첫 인덱스 수를 버립니다. => [1,2,3,4] -> [2,3,4]

      - 시작점 start(0), 끝점 end(N-1)를 int형 변수로 선언했습니다.

      - reverse = true 인 경우 뒤집힌 경우이므로 end-- 해주었습니다.

      - reverse = false 인 경우 뒤집히지 않은 경우이므로 start++ 해주었습니다.

      - 또한 arr의 size가 0일때 D를 수행하게되면 error가 출력되야 하므로 N--를 해줌으로서 N<0일 경우 error 이므로 함수 P를 확인하는 반복문을 break해줍니다.

      - 단 , N=0이고 R을 수행하는 경우는 error가 아님.

        

  - 수행할 함수 P를 수행합니다. 이를 전부 직접 arr를 건드려 수행할 필요없이 뒤집혔다면 뒤집힌대로 start 에서 end 까지 출력만 해주고 , 뒤집히지 않았다면 end 에서 start까지 출력만 해주면 되었습니다.

    - N<0 이면 error를 출력시킵니다
    - 출력은 입력때와 같이([1,2,3,4]) 출력시켜야하기 때문에 [ 를 append해줍니다
    - reverse = false이면 뒤집지 않았기 때문에 start 부터 end까지 arr를 append 해줍니다
    - reverse = true 이면 뒤집혔기 때문에 end부터 start까지 arr를 append해줍니다
    - 또한 ',' append를 같이 해주기 때문에 마지막에 찍히는 ','를 deleteCharAt을 통해 제거 해줍니다.
    - 단, N=0 이면 []을 출력해야하므로 이를 수행하지 않습니다.
    - 출력은 입력때와 같이([1,2,3,4]) 출력시켜야하기 때문에 ] 를 append해줍니다

    

  - 그냥 이 문제를 풀면서 주말에 할 일없는 선영이가 미웠습니다..