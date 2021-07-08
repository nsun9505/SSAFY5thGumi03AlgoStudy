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