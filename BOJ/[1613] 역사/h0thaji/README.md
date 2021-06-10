# [1613] 역사

## 분류
>

## 코드
```java
package BOJ1613_역사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K, S;
	static boolean[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken())+1;
		K = Integer.parseInt(st.nextToken());
		
		arr = new boolean[N][N];
		
        //그래프 초기화
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = true; // true이면 a가 b보다 순서가 빠르다는 의미
		}
		
		//플로이드와샬
		for(int k = 1; k < N; k++) { // k 중간노드
			for(int i = 1; i < N; i++) { // i 시작노드
				for(int j = 1; j < N; j++) { // j 도착노드
					if(arr[i][k] && arr[k][j]) arr[i][j] = true; // i->k->j로 갈 수 있다는 뜻
				}
			}
		}
		
		S = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(arr[a][b] == arr[b][a]) sb.append(0).append("\n"); // 같다면 false, false 경우 밖에 없음. 즉 모른다
			else sb.append(arr[a][b]? -1:1).append("\n"); // 다르다면 [a][b]가 true이면 a가 b로 갈 수 있다는 의미, 즉 a가 순서가 더 빠르므로 -1, false면 [b][a]가 true이므로 1 출력
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}

```

## 문제풀이

- 입력된 사건들의 순서를 알아야하기 때문에 **모든 정점에 대하여 모든 정점에 도달**할 수 있는 지 알고 있어야하므로 ***플로이드-와샬 알고리즘***을 사용했습니다.

- 입력받은 사건 전후 관계를 알고 있는 두 사건에 대해 그래프를 배열로 만들어주었습니다.

  - 해당 문제의 그래프는 가중치가 없는 **유향 그래프**
  - 먼저 입력받은 사건이 뒤에 입력받는 사건보다 전에 일어난 사건을 의미
  - 그러므로, boolean형 배열을 만들어 먼저 발생한 사건이라면 후에 발생한 사건을 향하게 true를 주었습니다.
    - 갈 수 없다면 먼저 발생한 사건이 아니거나 전후관계를 모른다는 의미

- 플로이드-와샬

  - k = 중간 노드, i = 출발 노드, j = 도착 노드

  - ```java
    if(arr[i][k] && arr[k][j]) arr[i][j] = true;
    ```

    - 출발노드(i) -> 중간노드(k) 가 **true** 이고,
    - 중간노드(k) -> 도착노드(j) 가 **true** 라면
    - i->k->j 가 **true**라는 의미
    - 즉, i가 j 보다 사건 발생이 빠르다는 의미이므로 arr\[i][j]를 true로 바꿔줍니다.

  - 모든 반복문이 끝나게 되면 사건 전후 관계를 파악할 수 있게 됩니다.

- 사건 전후 관계를 알고 싶은 사건 쌍(a,b)을 arr배열를 통해 확인해주면 됩니다.

  - arr\[a][b] == arr\[b][a]
    - 해당 문제에서는 두 사건의 간선 방향이 양방향일 수 가 없습니다. (true , true 경우 X)
    - 즉,  false == false 이고, **사건 전후 관계를 모른다는 의미**이므로 **0을 출력**합니다.
  - else
    - \[a][b]가 true이면 a가 b로 갈 수 있다는 의미이고 **a가 순서가 더 빠르므로** **-1 출력**
    - false면 \[b][a]가 true이므로 **1 출력**

  