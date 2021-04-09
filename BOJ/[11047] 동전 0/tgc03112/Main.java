import java.util.Scanner;

public class Main {	//동전0

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();	//가치
		int sum =0;
		int[] arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		
		for(int i=N-1;i>=0;i--) {
			sum+=K/arr[i];
			K%=arr[i];
		}
		System.out.println(sum);
	}
}