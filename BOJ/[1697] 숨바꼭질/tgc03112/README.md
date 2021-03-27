# [1697] 숨바꼭질

## 분류
> BFS

## 코드
```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N,K;
	static int[] v;
	static int res;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	//출발
		K = sc.nextInt();	//도착
		v = new int[100000+1];
		res = 0;
		
		v[N]=0;
		bfs();
		
		System.out.println(v[K]);
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == K) break;
			
			if(now-1 >=0 && v[now-1]==0) {
				q.add(now-1);
				v[now-1] = v[now]+1;
			}
			if(now+1 <=100000 && v[now+1]==0) {
				q.add(now+1);
				v[now+1] = v[now]+1;
			}
			if(now*2 <=100000 && v[now*2]==0) {
				q.add(now*2);
				v[now*2] = v[now]+1;
			}
		}
	}
}
```

## 문제풀이
bfs를 활용하는 문제입니다.

bfs가 Q를 이용하여 차례대로 방문한다는 점을 생각한다면 그렇게 풀기어려운 문제는 아닙니다. (하지만 전 생각이 안떠올라서 좀 걸렸습니다..)

방문한 숫자에서 -1, +1, *2한 값을 모두 Q에 넣어주고 방문을 시도합니다.

방문한다면 return해주고 아니라면 현재까지 방문한 전의 정점에서 +1만큼 증가시켜줍니다.

원하는 도착지를 만나면 종료하고 그 때 배열에 있는 값을 출력해주면됩니다.