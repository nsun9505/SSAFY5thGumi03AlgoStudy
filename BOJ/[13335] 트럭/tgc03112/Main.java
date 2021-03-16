import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {	//트럭

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> bridge= new LinkedList<>();	//다리위
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	//트럭의 수 
		int W = Integer.parseInt(st.nextToken());	//다리의 길이 
		int L = Integer.parseInt(st.nextToken());	//최대 하중
		
		int[] wait = new int[n];	//트럭 대기 
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			wait[i] = Integer.parseInt(st.nextToken());
		}
		
		int time = 0;	//시간	
		int weight = 0;	//하중
		
		for(int i=0;i<n;i++) {
			while(true) {
				if(bridge.isEmpty()) {
					time++;
					bridge.add(wait[i]);
					weight +=wait[i];
					break;
				}
				else if(bridge.size()==W) {
					weight-=bridge.peek();	//맨앞트럭 무게 빼주기
					bridge.poll();
				}
				else {
					if(weight + wait[i]<=L) {
						time++;
						bridge.add(wait[i]);
						weight +=wait[i];
						break;
					}
					else {
						time++;
						bridge.add(0);	//맨 앞 트럭을 앞으로 밀어줌
					}
				}
			}
		}
		time+=W;
		System.out.println(time);
	}
}