

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 10001;
	static int V,E;
	static int A,B,C;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken())+1;
		E = Integer.parseInt(st.nextToken());
		
		arr = new int[V][V];
		
		for(int[] a : arr) {
			Arrays.fill(a, INF);
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr[A][B] = C;
		}
		
		for(int k = 1; k < V; k++) {
			for(int i = 1; i < V; i++) {
				if(k == i) continue;
				for (int j = 1; j < V; j++) {
					if(i == j) continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		int res = INF;
		
		for(int i = 1; i < V; i++) {
			for(int j = 1; j < V; j++) {
				if(i == j) continue;
				if(arr[i][j] != INF && arr[j][i] != INF)
					res = Math.min(res, arr[i][j] + arr[j][i]);
			}
		}
		
		res = (res == INF)? -1: res;
		bw.write(String.valueOf(res));
		bw.flush();
		bw.close();
		br.close();
		
	}

}
