import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[W];
		st=new StringTokenizer(br.readLine());
		
		for(int i=0;i<W;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int res=0;
		for(int i=0;i<W;i++) {
			//나 포함 왼쪽으로 가면서 나보다 높은 기둥 구하기
			int left=i;
			for(int l=i;l>=0;l--) {
				if(arr[l]>arr[left]) {
					left=l;
				}
			}
			//나 포함 오른쪽으로 가면서 나보다 높은 기둥 
			int right=i;
			for(int r=i;r<W;r++) {
				if(arr[r]>arr[right]) {
					right=r;
				}
			}
			//왼쪽 오른쪽 중에 작은 기둥 - 내높이 => 현재 위치에서의 빗물 받은 넓이
			int min=Math.min(arr[left], arr[right]);
			int high=min-arr[i];
			if(high>0) {
				res+=high;
			}
		}
		System.out.println(res);
	}
}