import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[] res;
 	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			
			arr = new int[N];	//들어오는 숫자 저장
			res = new int[6];	//뽑은 로또번호 6개 출력용
			
			for(int i=0;i<N;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			combi(0,0);
            System.out.println();
		}
	}
	private static void combi(int cnt, int start) {

		if(cnt==6) {
			for(int j=0;j<res.length;j++) {
				System.out.print(res[j]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=start;i<N;i++) {
			res[cnt]=arr[i];
			combi(cnt+1, i+1);
		}
	}
}
