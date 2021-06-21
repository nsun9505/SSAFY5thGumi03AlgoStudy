import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static double res;
	static int[] parent;
	static Edge[] edgeList;
	static ArrayList<Edge> spaceList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//우주신들의 개수(N<=1000)
		M = Integer.parseInt(st.nextToken());	//이미 연결된 신들과의 통로의 개수(M<=1000)
		parent = new int[N];
		edgeList = new Edge[N];
		res = 0;
		
		//union-find 를 위한 parent 배열
		for(int i=0;i<N;i++) {
			parent[i] = i;
		}
		
		//우주신들의 좌표 받기 (1,1)(3,1)(2,3)(4,3)
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(x, y, 0);
		}
		
		//이미 연결된 통로들 union연산으로 합쳐줌 (1,4)
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a-1, b-1);
		}
		
		//초기화
		for(int i=0;i<edgeList.length-1;i++) {
			Edge edge1 = edgeList[i];	//하나 꺼내서
			for(int j=i+1;j<edgeList.length;j++) {
				Edge edge2 = edgeList[j];
				double weight = Math.sqrt(Math.pow(edge1.x - edge2.x,2) + Math.pow(edge1.y - edge2.y,2));
				spaceList.add(new Edge(i, j, weight));
			}
		}
		//크루스칼을 위한 오름차순 정렬
		Collections.sort(spaceList);
		
		//최소비용 탐색
		for(int i=0;i<spaceList.size();i++) {
			Edge tmp = spaceList.get(i);
			if(!(find(tmp.x)==find(tmp.y))) {
				res += tmp.weight;
				union(tmp.x, tmp.y);
			}
		}
		System.out.println(String.format("%.2f", res));
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		//루트 노드를 부모노드로 만들어줌
		return parent[a] = find(parent[a]);
	}
	
	private static class Edge implements Comparable<Edge>{	//간선정보
		int x,y;	//좌표
		double weight;
		

		public Edge(int x, int y, double weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.weight > o.weight) return 1;
			else return -1;
		}
	}
}
