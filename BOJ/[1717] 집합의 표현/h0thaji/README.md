# [1717] 집합의 표현

## 분류
>

## 코드
```java
package BOJ1717_집합의표현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		//makeSet
		for(int i = 0; i < N; i++) {
			arr[i] = i;
		}
		
		// M 만큼 반복
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken()); // 입력키
			int a = Integer.parseInt(st.nextToken()); // a
			int b = Integer.parseInt(st.nextToken()); // b
			if(cal == 0) { // 0이면 합집합
				union(a,b);
			}else { // 1이면 같은집합인지 확인
				sb.append(find(a)==find(b)? "YES":"NO").append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();

	}
    
    //부모 찾기 , 같은 부모면 같은 집합이므로
	private static int find(int a) {
		if(a == arr[a]) return a;
		return arr[a] = find(arr[a]);
		
	}
    
    //집합 합치기
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) arr[bRoot] = aRoot;
		
	}

}

```

## 문제풀이

- 입력받는 두 수가 속해있는 집합을 합치거나 (0), 두 수가 속해있는 집합이 같은지를 확인(1) 하는 문제입니다.
- 그러므로 **union-find**를 통해 집합을 합쳐주고 같은 집합인지 확인해주었습니다.
- arr 배열을 자신으로 초기화를 해줍니다.
- 입력받는 값이 0일 경우
  - union 메서드를 통해 a, b를 합쳐줍니다.
- 입력받는 값이 1일 경우
  - find 메서드를 통해 부모를 찾아 같다면 같은 집합이므로 **YES** 출력
  - 다르다면 **NO** 출력