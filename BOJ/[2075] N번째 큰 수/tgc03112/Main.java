import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		int N = Integer.parseInt(br.readLine());	
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	//N번째 큰 수로 출력해야 하기 때문에 반대로 넣어줌
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		 for(int i=0; i<N-1; i++) {
	            pq.poll();
	     }
		 System.out.println(pq.poll());
	}
}