# [1956] 운동

## 분류

## 코드
```java
package BOJ1956_운동;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 10001;
	static int V,E;
	static int A,B,C;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken())+1;
		E = Integer.parseInt(st.nextToken());
		
		arr = new int[V][V];
		
		for(int[] a : arr) {
			Arrays.fill(a, INF);
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr[A][B] = C;
		}
		
		for(int k = 1; k < V; k++) {
			for(int i = 1; i < V; i++) {
				if(k == i) continue;
				for (int j = 1; j < V; j++) {
					if(i == j) continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		int res = INF;
		
		for(int i = 1; i < V; i++) {
			for(int j = 1; j < V; j++) {
				if(i == j) continue;
				if(arr[i][j] != INF && arr[j][i] != INF)
					res = Math.min(res, arr[i][j] + arr[j][i]);
			}
		}
		
		res = (res == INF)? -1: res;
		bw.write(String.valueOf(res));
		bw.flush();
		bw.close();
		br.close();
		
	}

}

```

## 문제풀이

- 도로를 따라 운동을 하기 위한 경로를 찾는데, 운동을 한 후에는 `다시 시작점으로 돌아와야한다`.

- 또한 운동을 매우 귀찮아 하므로, 사이클을 이루는 `도로의 길이의 합이 최소`가 되도록 한다.

- 플로이드-와샬 알고리즘

  - 모든 경로를 거쳐서 최소 경로를 찾는 문제이므로 해당 알고리즘을 이용하였습니다.

- 최소경로를 찾은 뒤, 최소 사이클의 도로 길이를 찾아야하므로 사이클이 발생했는지 알아야합니다.

  -  a -> b , b -> a

  - 즉, 플로이드 와샬 알고리즘을 수행한 배열에서의 초기값 INF가 아닌 다른 정수로 초기화되었으면 사이클이 발생했다는 말입니다.

  - ```
    arr[a][b] = 1 , arr[b][a] = 3
    ```

    