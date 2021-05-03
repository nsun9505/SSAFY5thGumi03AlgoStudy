import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][n];
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<n;i++) {
			Arrays.fill(map[i], 9999);
			map[i][i]=0;
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int dis = Integer.parseInt(st.nextToken());
			
			map[start][end] = dis;
			map[end][start] = dis;
		}

		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				if(k==i) continue;
				for(int j=0;j<n;j++) {
					if(j==k || j==i) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		int max = 0;
		int sum = 0;
		
		for(int i=0;i<n;i++) {
			sum = 0;
			for(int j=0;j<n;j++) {
				if(m>=map[i][j]) {
					sum+=arr[j];
				}
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}