package bkj_13335; // 210316

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 백준 13335번 : 트럭

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 트럭의 갯수
		int w = Integer.parseInt(st.nextToken()); // 다리 위에 동시에 최대로 올라갈 수 있는 트럭의 갯수
		int L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중
		
		Queue<Integer> queue = new LinkedList<>(); // 트럭을 다리에 올릴 때의 순서를 저장하는 queue
		st = new StringTokenizer(br.readLine());
		
		int[] truck = new int[n];
		for(int i = 0; i < n; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		
		int time = 0, weight = 0; // weight : 다리 위에 올린 트럭 무게의 함
		
		for(int i = 0; i < n; i++) {
			while(true) {
				if (queue.isEmpty()) { // 다리위에 아무 트럭도 없다면
					queue.offer(truck[i]); // 다리 위에 올리고
					time++; // 전진
					weight += truck[i];
					break;
				} else if (queue.size() == w) {
					weight -= queue.poll(); // 제일 앞의 트럭은 다리를 다 건넜기 때문에, 무게 빼주기
				} else { // 다리위에 트럭이 있는데,
					if (weight + truck[i] > L) { // 현재 트럭을 올렸을 때 다리의 최대 하중보다 커지면
						queue.offer(0); // 무게에는 영향을 주지 않지만, 최대 하중보다 커질 때 바로 이어서 트럭이 오지 않도록 그룹별로 나누는 역할을 함
						time++; // 다리 위에 올리지 않고 전진만
					} else { // 최대 하중보다 작다면
						queue.offer(truck[i]); // 다리 위에 올리고
						time++; // 전진
						weight += truck[i];
						break;
					}
				}
			}
		}
		
		System.out.println(time + w); // 마지막 트럭이 건너는 시간은 따로 더해주기
	}

}
