import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = null;
		int N = sc.nextInt();	//찾아질 대상 
		int[] find = new int[N];
		for(int i=0;i<N;i++) {
			find[i] = sc.nextInt();
		}
		Arrays.sort(find);
		int M = sc.nextInt();
		
		for(int i=0;i<M;i++) {
			int num = sc.nextInt();
			int start = 0;
			int end = N-1;
			int res = 0;
			
			while(start<=end) {
				int mid = (start+end)/2;
				
				if(find[mid] == num) {
					res=1;
					break;
				}
				if(num<find[mid]) {
					end=mid-1;
				}
				else if(num>find[mid]) {
					start = mid+1;
				}
			}
			System.out.println(res);
		}
	}
}