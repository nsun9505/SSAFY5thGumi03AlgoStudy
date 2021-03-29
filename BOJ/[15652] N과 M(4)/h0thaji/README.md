# [15652] N과 M(4)

## 분류
> 백트랙킹

## 코드
```java
package BOJ15652_N과M4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[] res;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[M]; // 뽑을 애들 담을 배열
		dfs(0,1); // 재귀함수
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int cnt,int start) { // 뽑은 개수 , 시작 index
		if(cnt == M) { // M개를 뽑아야하므로 뽑은 개수가 M개이면
			for (int i = 0; i < M; i++) { // 결과 배열 반복문
				sb.append(res[i]).append(" "); // res배열 append
			}
			sb.toString().trim(); // 마지막 공백 없애주고 줄바꿈
			sb.append("\n");
			return; // 백트래킹
			
		}
		
		for (int i = start; i <= N; i++) { // 시작부터 N까지
			
			res[cnt] = i; // 결과배열에 넣어줌
			dfs(cnt+1,i); // start가 1일때는 1부터 2일때는 2부터 니까 i넣어줌 cnt는 하나뽑았으니 +1을 해줌
		}
		
	
}

```

## 문제풀이

- 1부터 N까지의 수를 M개를 중복조합으로 뽑아내는 문제입니다.
- 중복조합은 뽑은 수가 중복이 될 수 있고 뽑은 순서가 상관이 없습니다.
  - 즉 , 뽑았는지 안뽑았는지 확인은 해 줄 필요없이 재귀함수의 매개변수(start)를 이용해 어디서 부터 뽑을지만 지정해주면 됩니다.
- 재귀함수를 사용하여 매개변수 cnt (몇 개뽑았는지) , start(시작지점)를 이용합니다.
  - 해당 재귀함수의 기저조건은 M개를 뽑아야하므로 cnt == M 이되면 멈춥니다.
    - res(결과 배열) 에 담긴 데이터를 출력해야하므로 sb.append를 해줍니다.
  - i = start ~N까지 반복하면서 재귀(cnt+1,i) 를 해줍니다.