# [15652] N과 M(4)

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] res;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//4
		M = Integer.parseInt(st.nextToken());	//2

		res = new int[M];
		
		combi(0,1);
	}
	private static void combi(int cnt, int start) {

		if(cnt == M) {	//뽑는 개수, 기저조건
			for(int i=0;i<res.length;i++) {
				System.out.print(res[i]+" ");
			}
			System.out.println();
			return;
		}
			
		for(int i=start;i<=N;i++) {
			res[cnt] = i;
			combi(cnt+1,i);
		}
	}
}
```

## 문제풀이
res배열을 M 개수만큼 지정해줘 배열에 담고 바로 출력하는 형식으로 작성 했습니다.

배열의 맨 마지막 부분? 부터 차례대로 올려주고 for문 한바퀴 다 돌면 그 다음 자리수 하나 증가시킨 뒤 또 for문을 돌게 해서 출력했습니다.

처음에 1 1 , 4 4 같이 중복된 숫자가 나오지 않아서 조금 헤맸습니다.