package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 n개의 트럭이 다리를 건넘 (무게는 서로 같지않을수있음)
트럭의 순서 바꿀수 x 
w대의 트럭만 올라갈수있음
다리길이 w 
단위시간에 하나의 단위길이만큼 이동간으
최대하중보다 트럭무게 작아야함
다리위에 못올라간 트럭은 포함 x 

 */
public class Bj13335 {

	public static void main(String[] args) throws IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine()," "); 


		int n = Integer.parseInt(st.nextToken()); // 트럭의 수  4
		int w = Integer.parseInt(st.nextToken()); // 다리의 길이  2 
		int L = Integer.parseInt(st.nextToken()); // 다리의 최대무게 10

		st = new StringTokenizer(br.readLine(), " "); 
		int[] truck = new int[n]; // 트럭 무게  7 4 5 6
		int[] bridge = new int[w]; // 다리 상태 
		boolean[] visited = new boolean[n]; // 사용한 트럭 
		for (int i = 0; i < n; i++) 
		{ 
			truck[i] = Integer.parseInt(st.nextToken()); 

		} 
		int time = 0; int weight = 0; // 현재 다리의 무게

		while(true) 
		{ 
			time++; // 시간증가 
		
			weight-=bridge[w-1]; //  현재 다리의 무게에서 다리의 마지막칸에 있는 무게 빼기 
			
			
			for (int i = w-1; i > 0; i--) 
			{ // 다리 한칸씩 이동 
				bridge[i] = bridge[i-1]; 

			} 
			int now_truck = -1; //  현재 이동할 차례 트럭 
			
			for (int i = 0; i < n; i++) 
			{ 
				if(!visited[i]) 
				{ 
					now_truck = i; 
					break; 
				} 
			}

			if(now_truck!=-1&& weight+truck[now_truck]<=L) 
			{ 
				//다리에 현재 이동할 차례의 트럭 무게가 최대하중 이하이면 
				//bridge[0]에 현재 이동할 차례의 트럭 넣기 
				
				
				weight+=truck[now_truck]; 
				
				visited[now_truck] = true; 
				
				bridge[0] = truck[now_truck]; 
			}
			else { 
				
				bridge[0] = 0; 
			} 
			if(now_truck==-1 && weight==0) 
				
				break; 
		} 
		System.out.println(time);

	}

}



