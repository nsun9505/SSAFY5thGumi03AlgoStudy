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