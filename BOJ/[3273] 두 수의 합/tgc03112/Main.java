import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		int sum = 0;

		int start = 0;
		int end = n-1;
		
		while(start<end) {
			if(arr[start]+arr[end]==x) {
				sum++;
//				end=end-1;	//둘중아무거나 순서 상관없음 ->어차피 다 고려될 예정
				start=start+1;
			}
			else if(arr[start]+arr[end]>x) {
				end=end-1;
			}
			else {
				start=start+1;
			}
		}
		System.out.println(sum);
	}
}