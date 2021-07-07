public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());	//마을
		int E = Integer.parseInt(st.nextToken());	//도로
		
		
		int map[][] = new int[V+1][V+1];
		int res = Integer.MAX_VALUE;
		
		for(int i=0;i<=V;i++) {
			Arrays.fill(map[i], 10001);
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a][b]=c;
		}
		
		//플로이드와샬
		for(int k=0;k<=V;k++) {
			for(int i=0;i<=V;i++) {
				for(int j=0;j<=V;j++) {
					if(k==j || i==j || k==i) continue;
					map[i][j]=Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		for(int i=1;i<=V;i++) {
			for(int j=1;j<V;j++) {
				if(map[i][j]<10001) {	
					res = Math.min(res, map[i][j]+map[j][i]);
				}
			}
		}
		
		if(res > 10000) {
			System.out.println(-1);
		}
		else {
			System.out.println(res);
		}
	}
}