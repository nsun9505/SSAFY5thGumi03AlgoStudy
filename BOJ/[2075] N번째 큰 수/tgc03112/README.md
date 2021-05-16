# [2075] N번째 큰 수

## 분류

> 우선순위 큐

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	//N번째 큰 수로 출력해야 하기 때문에 반대로 넣어줌

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}

		 for(int i=0; i<N-1; i++) {
	            pq.poll();
	     }
		 System.out.println(pq.poll());
	}
}
```

## 문제풀이

N번째 큰 수를 출력만 해주면 되는 문제이기 때문에 PQ를 사용했습니다. 자동으로 정렬이 되기 때문에

대신 넣을때 Collections.reverseOrder() 를 써줘서 역순으로 정렬되게 했습니다

N번째 큰 수를 출력해야 하기 때문에 N-1까지 poll해서 버려주고 마지막에 N번쨰를 출력해주면 끝~~!
