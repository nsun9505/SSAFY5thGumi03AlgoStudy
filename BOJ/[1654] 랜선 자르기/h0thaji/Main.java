package BOJ1654_랜선자르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int K,N; // 가지고 있는 랜선의 개수, 원하는 랜선의 개수
	static long max = 0;
	static int[] lan;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lan = new int[K];
		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			max = max > lan[i] ? max : lan[i]; // 랜선 중 가장 긴 랜선
		}
		
		bw.write(String.valueOf(search()));
		bw.flush();
		bw.close();
		br.close();
	}
	private static long search() {
		long start = 1; // 나누려면 1부터 시작해야함
		long end = max; // 가장 긴 랜선

		while(start <= end) { // start가 end보다 작아 질 때 까지 반복
			long mid = (start + end) /2; // mid 값, 랜선 자를 길이
			long cnt = cut(mid); // 랜선 자르고 나올 개수
			System.out.println("start : "+start+" / mid : "+mid+" / end : "+end +"/ cnt :"+cnt +" / N : " + N);
			if(cnt >= N) { // 자르고 난 랜선의 개수가 원하는 개수보다 많거나 같다면
				start = mid +1; // 자를 랜선의 크기를 높여 랜선 개수를 줄임

			}else { // 자르고 난 랜선의 개수가 원하는 개수보다 작다면
				end = mid-1; // 자를 랜선의 범위를 줄여 랜선 개수를 늘림
			}
		}
		return end; // 자르고 난 랜선의 개수가 원하는 개수보다 많아도 답임.
		//즉 탐색을 다 끝내고 나면 end의 값이 답이 된다.
	}
	private static long cut(long mid) {
		long cnt = 0;
		for (int l : lan) {
			cnt += l/mid; // 해당 랜선을 나누면 개수가 나옴
		}
		return cnt;
	}


}
