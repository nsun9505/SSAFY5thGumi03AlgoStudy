package BOJ2805_나무자르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N,M, max = 0; // 나무의 수 , 필요한 나무 길이, N개의 나무 중 가장 긴 나무 길이
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			max=Math.max(max, tree[i]); // 나무 중 가장 큰 놈
		}

		bw.write(String.valueOf(search()));
		bw.flush();
		bw.close();
		br.close();
	}
	private static int search() { // 이분탐색
		int start = 0;
		int end = max;
		int res = 0;
		while(start <= end) { // start의 값이 end값보다 작거나 같을 때 까지 반복
			int mid = (start + end) / 2; // mid 값
			long sum = 0; // 자른 나무길이 더해줄 변수
			
			for (int i = 0; i < N; i++) { // 자른 나무길이 더하기
				if(mid < tree[i]) { // mid보다 큰 놈만 자를 수 있음
					sum += tree[i] - mid; // 잘라서 총 길이 구해줌
				}
			}
			
			if(sum == M) return mid; // 원하는 길이만큼만 가져갈 것이니 같다면 mid가 답
			else if(sum > M) { // 자른 총 길이보다 원하는 길이보다 크다면
				start = mid+1; // start는 mid+1
				res = Math.max(res, mid); // 결과값은 원하는 길이만큼 가져가면 좋겠지만 원하는 길이가 정확히 떨어지지 않을 수 있으니
				// 그 중에서 mid값이 가장 큰 값

			}
			else end = mid-1; // 자른 길이가 원하는 길이보다 작다면
			
		}
		return res;
	}

}
