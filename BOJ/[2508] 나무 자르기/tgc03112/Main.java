import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<arr.length;i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int start = 0;
		int end = arr[N-1];
		
		while(start<=end) {
			int mid = (start+end) / 2;
			
			long tree = 0;
			
			for(int i=0;i<N;i++) {
				if(arr[i]>mid) {	
					tree += arr[i]-mid;	//잘려나간 나무
				}
			}
			if(tree==M) {
				System.out.println(mid);
				System.exit(0);
			}
			else if(tree<M) {
				end = mid-1;
			}
			else{
				start = mid+1;
			}
		}
		System.out.println(end);
	}
}