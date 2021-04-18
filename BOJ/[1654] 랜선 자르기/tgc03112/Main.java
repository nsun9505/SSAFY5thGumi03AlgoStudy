import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		for(int i=0;i<K;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		long max = arr[K-1];
		long start = 1;
		long mid = 0;
		
		while(start <= max) {
			mid = (start+max)/2;
			long sum = 0;
//			System.out.println("mid : "+mid);
			for(int i=0;i<K;i++) {
				sum+=arr[i]/mid;
			}
			if(sum>=N) {
				start = mid+1;
			}
			else{
				max = mid-1;
			}
		}
		System.out.println(max);
	}
}