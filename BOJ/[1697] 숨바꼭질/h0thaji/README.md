# [1697] 숨바꼭질

## 분류
> BFS

## 코드
```java
package BOJ1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int MAX_RANGE = 100001;
	static int N,K,res;
	static boolean[] v = new boolean[MAX_RANGE];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치 입력
		K = Integer.parseInt(st.nextToken()); // 동생 위치 입력
		
		if(N!=K) bfs(); // 수빈이와 동생 위치가 다를때 bfs 탐색
		
		sb.append(res); // 결과 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N); // 처음시작은 수빈이 위치이므로 N offer
		v[N] = true; // 방문체크
		
		while(!q.isEmpty()) { // 큐가 빌때까지
			int size = q.size(); // q의 사이즈받아서 
			res++; // 이 반복문에 들어왔다는 건 한번 움직인다는 뜻 res++
			
			while(size-->0) { // q size는 같은 레벨의 크기만큼 있기 때문에 while문을 돌면 해당 레벨까지만 돔
				int current = q.poll();
				
				int pre = current - 1; // -1 칸
				int next = current + 1; // + 1칸
				int jump = current * 2; // 텔레포트
			
				if(pre == K || next == K || jump == K) return; // 이동한 곳이 수빈이 위치이면 bfs 종료
				
				
				if(pre >= 0 && !v[pre]) { // 인덱스 벗어나지 않고 방문하지않았으면
					q.offer(pre); // offer
					v[pre] = true; // 방문체크
				}
				if(next < MAX_RANGE && !v[next]) {
					q.offer(next);
					v[next] = true;
				}
				if(jump < MAX_RANGE && !v[jump]) {
					q.offer(jump);
					v[jump] = true;
				}				
			}			
		}		
	}	
}

```

## 문제풀이

- 수빈이와 동생은 같은 수직선상에 있으며, 수진이는 X의 위치에서 1초후에 X+1,X-1,X*2를 이동할 수 있고 수빈이가 동생의 있는 위치까지 가는 가장 빠른 시간을 출력하는 문제입니다.
- 수빈이의 위치에서 1초마다 +1,-1,*2 를 움직일 수 있으니 이동할 수 있는 세가지의 경우 확인해봐야하므로 한번에 세 가지경우가 퍼져나간다고 생각해 bfs로 접근하였습니다.
- 시작부터 수빈이와 동생의 위치가 같을 수도 있으므로 bfs 전 N,K(수빈이 위치, 동생 위치)를 비교해 다르면 bfs 탐색을 하였습니다.