import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//심사대 개수
		long M = Long.parseLong(st.nextToken());	//줄 선사람
		
		long[] arr = new long[N];
		long max = 0;
		
		for(int i=0;i<N;i++) {
			arr[i]=Long.parseLong(br.readLine());	//심사대 시간
			max = Math.max(arr[i], max);	//심사대 중에 가장 긴 시간 * 줄선사람 = 최대범위
		}
		
		long start = 1;
		long end = max*M;
		long res = end;
		long mid = 0;
		
		while(start<=end) {
			mid = (start+end)/2;
			
			long cnt = 0;
			
			for(int i=0;i<N;i++) {	//mid 초에 몇 명의 인원이 통과할 수 있는지 계산
				cnt += mid/arr[i];
			}
			
			if(cnt >= M) {	//친구를 다 심사할 수 있는 시간이라면
				res = Math.min(mid, res);	//둘 중작은것
				end = mid - 1;	//낮춰서 계산해보기
			}
			else {
				start = mid + 1;	//높여서 계산해보기
			}
		}
		System.out.println(res);
	}
}
