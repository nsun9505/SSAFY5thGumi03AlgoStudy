# [13549] 숨바꼭질 3

## 분류
>- BFS

## 코드
``` java 
public class Main {
	static int N,K;
	static Queue<Integer> q;
	static int[] hide;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//수빈
		K = Integer.parseInt(st.nextToken());	//동생
		
		hide = new int[100000+1];
		visited = new boolean[100000+1];
		
		bfs();
		
		System.out.println(hide[K]);
	}
	
	private static void bfs() {
		q = new LinkedList<Integer>();
		q.add(N);
		visited[N] = true;
		
		while(!q.isEmpty()) {
			int subin = q.poll();

			if(subin==K) {
				return;
			}

			if(subin*2<=100000 && !visited[subin*2]) {
				q.add(subin*2);
				visited[subin*2]=true;
				hide[subin*2] = hide[subin];
			}
			if(subin-1>=0 && !visited[subin-1]) {
				q.add(subin-1);
				visited[subin-1]=true;
				hide[subin-1] = hide[subin]+1;
			}
			if(subin+1<=100000 && !visited[subin+1]) {
				q.add(subin+1);
				visited[subin+1]=true;
				hide[subin+1] = hide[subin]+1;
			}
		}
	}
}
```

## 문제풀이

숨바꼭질 문제와 비슷하게 BFS로 풀었습니다.

다른점은 

1. 수빈이 순간이동하는 x2부터 계산해줘야됩니다. 만약 수빈이가 1이고 동생이 2일떄 비용을 1쓰면서 +1로 가는 방법이 있고 비용을 0 쓰면서 x2로 가는 방법 두가지가 있기 떄문에 최소비용을 쓰는 x2부터 계산합니다.

2. 수빈이 출발하는 방문체크를 뺴먹으면 안됩니다. 처음에 방문체크 배열을 만들지않고 그냥 hide배열에서 0이면 방문하지 않았다 로 조건을 검사헀더니 수빈이 출발하는 곳도 방문체크가 되지않아 제대로된 값이 나오지 않았습니다.

그것 외에는 숨바꼭질을 풀어봤거나 아이디어만 떠오르면 괜찮은 문제였습니다.