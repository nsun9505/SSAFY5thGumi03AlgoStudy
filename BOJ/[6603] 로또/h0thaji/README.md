# [6603] 로또

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int K; // 총 선택 할 수
	static int[] S; // 입력한 로또번호 담을 배열
	static boolean[] v; // 방문체크
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		while(true) { // 0이 나올때까지 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()); // 총 선택 할 수 입력
			if(K == 0) break; // K가 0이면 break

			S = new int[K]; // 입력할 로또 번호 배열 생성
			v = new boolean[K]; // 방문체크 배열 생성
			for (int i = 0; i < K; i++) { // 로또 번호 입력
				S[i]=Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0); // 로또 뽑기, 뽑은 횟수, 뽑은 건 안뽑아도 되니
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int cnt, int start) {
		
		if(cnt == 6) { // 6개 뽑았으면
			for (int i = 0; i < K; i++) {
				if(v[i]) { // true면 K개중 뽑힌 수이므로
					sb.append(S[i]).append(" "); // 결과 출력 stringbuilder에 append
				}
			}
			sb.append("\n"); // 줄바꿈
			return;
		}
		
		for (int i = start; i < K; i++) { 
			v[i] = true; // 해당 번호 뽑았으니 true
			dfs(cnt+1,i+1); // 다음 번호 뽑으러, cnt+1, 해당 번호 다음부터 확인하면되니 i+1
			v[i] = false; // 해당 번호 뽑은 경우가 끝났으니 false
		}
	}

}

```

## 문제풀이

- 뽑는 순서를 고려하지 않고 한번 뽑은 원소는 다시 뽑을 수 없는 조합입니다.
- 재귀를 통해 6개의 원소를 뽑게 되면 return을 시킵니다.
  - 반복문을 통해 v(방문 체크 배열) 배열 값이 true인 원소만 추출하여 결과출력값에 append해줍니다. (true면 원소를 뽑았다는 의미이므로)
- S배열에 들어있는 원소를 순서대로 뽑기 때문에 원소 뽑는 반복문을 0부터 시작 할 필요없이 다음 원소부터 뽑으면 되니 재귀를 넘겨줄때 i+1을 넘겨줍니다.
  - 해당 번호를 뽑았으면 v[i] = true 로 바꿔주고 재귀를 통해 해당 원소를 뽑은 경우를 확인 했으면 빠져나온뒤 v[i] = false로 바꾸어 줍니다.

