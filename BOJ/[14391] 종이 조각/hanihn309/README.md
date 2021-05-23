# [14391] 종이 조각 

## 분류
>


## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14391_종이_조각 { // 210523
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 종이의 세로 크기
		int M = Integer.parseInt(st.nextToken()); // 종이의 가로 크기
		
		int[] num = new int[N*M]; // 2차원 배열에 적힌 수들을 1차원 배열에 저장
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				num[i*M + j] = str.charAt(j) - '0';
			}
		}
		
		int max = 0; // 얻을 수 있는 점수의 최댓값
		
		// 2진수에서 0에 해당하는 값은 가로 종이 조각에 포함, 1에 해당하는 값은 세로 종이 조각에 포함한다고 가정
		// k는 0, 1, 10, 11, 100, 101, 110, 111, ... ~ 111110, 111111
		// 만약 k가 11111이라면, 1열, 2열, 3열이 각각 숫자로 이루어진 3개의 직사각형 종이 조각이 나온 것
		// 만약 k가 110(000110)이라면, 1행, 2행 1열, 2행 2열, 2행 3열로 이루어진 4개의 직사각형 종이 조각이 나온 것
		for(int k = 0; k < (1 << N*M); k++) {
			int sum = 0;
			
			// 각 인덱스를 모두 탐색하며 가로에 해당하는 종이 조각 계산 
			for(int i = 0; i < N; i++) {
				int current = 0;
				for(int j = 0; j < M; j++) {
					int checkIndex = i*M + j;
					
					// 1 << checkIndex는 checkIndex에 해당하는 자리가 1인 이진수 값을 의미
					// 그 자리와 k를 & 연산했을 때 결과가 0이라면 k에서 그 자리는 0이라는 의미 = 가로 종이라는 의미
					if((k & (1 << checkIndex)) == 0) {
						current = current * 10 + num[checkIndex];
						// 연달아 가로 종이에 포함되는 새로운 수가 나오면 현재 저장해놓은 current수에 10을 곱해 한 단계 윗자리로 올려주고, 지금 수를 더해주기
					} else { // 쭉 탐색하다가 만약 1이 나온다면 세로 종이 조각이라는 의미이므로, 자릿수 계산하는 과정을 멈추고 합계에 더해준 후 자릿수 초기화 
						sum += current;
						current = 0;
					}
				}
				sum += current; // 만약 해당 i행이 모두 가로 종이라서 else에서 sum+=current를 못해줄 수 있으니 한번 더 해주기
			}
			
			// 각 인덱스를 모두 탐색하며 세로에 해당하는 종이 조각 계산
			for(int j = 0; j < M; j++) { // 세로 종이 조각 계산이니까 j열 먼저 for문을 돌려줘야 함. 그래야 checkIndex 계산의 i*M + j에서 i행씩 계속 더해져서 세로줄 검사가 가능
				int current = 0;
				for(int i = 0; i < N; i++) {
					int checkIndex = i*M + j;
					
					// 1 << checkIndex는 checkIndex에 해당하는 자리가 1인 이진수 값을 의미
					// 그 자리와 k를 & 연산했을 때 결과가 0이 아니라면 k에서 그 자리는 1이라는 의미 = 세로 종이라는 의미
					// 여기를 == 1로 하면 안되는 이유는, 내가 지금 검사하고자 하는 checkIndex의 자리가 1이고, 그 값과 k를 & 연산 한다면 checkIndex의 자릿수에 해당하는 
					// 위치의 이진수 값이 1이고 나머지는 0으로 나올 것이므로 1이 아니라, != 0으로 해줘야 함.
					// ex) k가 15고 checkIndex가 2라고 가정하면, 1111 과 0100이 & 연산을 수행하고, 값은 0100이 나옴.
					if((k & (1 << checkIndex)) != 0) {
						current = current * 10 + num[checkIndex];
					} else {
						sum += current;
						current = 0;
					}
				}
				sum += current;
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}
```

## 문제 풀이
- 비트마스킹이 아니더라도 풀 수 있었지만, 비트마스킹 연습을 위해 구글링해서 그렇게 풀었는데.. 이해 조차 너무 힘들었슴다..
- 소스에 아주 자세히 주석 붙여놨으니 참고 바랍니다... 설명할 힘이 없네요..
- 그래도 흐름을 설명하자면, 이 문제의 핵심은 각 칸에 해당하는 값을 세로 종이에 포함시킬 지 가로 종이에 포함시킬지 정하는 것입니다. 세로 종이나 가로 종이에 포함시켰다면 해당 종이에 포함되는 수를 각 N 자리수에 맞게 변환시킨 후 더해주고 모두 더한 값이 최대가 될 때를 구해주면 됩니다.
- 일단 모든 경우를 다 검사해야하고, 이 문제가 왜 비트마스킹일지 봤더니 2차원 배열에 저장될 것 같았던 종이에 적힌 수를 1차원 배열에 저장하고 그 길이만큼 이진수를 이용해 0이면 가로 종이로, 1이면 세로 종이로 포함시킨다는 개념을 사용하기 때문이었습니다.
- 모든 값을 탐색해야 하므로, k라는 변수를 잡아 N*M만큼 for문을 돌리고 해당 k와 각 인덱스를 & 연산해 결과에 따라 가로 종이와 세로 종이에 포함시켜 계산해주었습니다.
- 그리고 해당 점수의 합을 다 구했다면 max 값을 갱신해주는 일을 반복하면서 최댓값을 구해주었습니다.