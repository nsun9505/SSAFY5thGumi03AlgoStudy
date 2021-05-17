# [2075] N번째 큰 수 

## 분류
>


## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_N번째_큰_수 { // 210517
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // N번째 큰 수 찾기
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		}); // 내림차순으로 queue에 삽입하기 위해 comparator를 재정의한 우선수위 큐 선언
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				queue.offer(num);
			}
		}
		
		int count = 0;
		while(count < N-1) { // 찾고자하는 N-1번째까지 poll한 후
			queue.poll();
			count++;
		}
		
		System.out.println(queue.poll()); // N번째 수 poll하며 결과 출력
		
	}
}
```

## 문제 풀이
- 규칙은 있지만 완벽한 오름차순이나 내림차순으로 정렬되어 있지 않는 자료에서, N번째로 큰 수를 찾아야하기 때문에 우선순위큐를 사용했습니다.
- 단, 우선순위큐는 default가 오름차순이므로, comparator를 이용하여 내림차순으로 재정의해줍니다.
- 그리고 받아낸 수를 우선순위 큐에 offer한 후, N-1번째 수까지 poll한 후 마지막에 poll을 한 번 더 해주면 찾고자하는 N번째로 큰 수를 출력할 수 있습니다.