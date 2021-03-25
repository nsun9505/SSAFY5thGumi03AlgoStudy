import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {	//트리의 부모 찾기 
	static int n;
	static int[] p;
	static boolean[] v;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		n = Integer.parseInt(br.readLine());
		p = new int[n+1];
		v = new boolean[n+1];
		list = new ArrayList[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=1;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			list[s].add(e);
			list[e].add(s);
		}
		v[1]=true;
		dfs(1);
		
		for(int i=2;i<p.length;i++) {
			System.out.println(p[i]);
		}
	}
	private static void dfs(int k) {
		for(int i :list[k]) {
			if(!v[i]) {
				p[i]=k;
				v[i]=true;
				dfs(i);
			}
		}
	}
}
