import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] res;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//4
		M = Integer.parseInt(st.nextToken());	//2

		res = new int[M];
		
		combi(0,1);
	}
	private static void combi(int cnt, int start) {

		if(cnt == M) {	//뽑는 개수, 기저조건
			for(int i=0;i<res.length;i++) {
				System.out.print(res[i]+" ");
			}
			System.out.println();
			return;
		}
			
		for(int i=start;i<=N;i++) {
			res[cnt] = i;
			combi(cnt+1,i);
		}
	}
}