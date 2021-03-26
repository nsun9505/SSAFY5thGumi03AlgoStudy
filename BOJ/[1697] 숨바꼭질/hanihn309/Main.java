package bkj_1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
    	int K = Integer.parseInt(st.nextToken()); // 동생의 위치
    	
    	if (N == K) { // 처음부터 같은 위치에 있다면 바로 0초 반환
    		System.out.println("0");
    		return;
    	} else { // 서로 다른 위치에 있다면
    		Queue<Integer> queue = new LinkedList<>(); // BFS 이용
        	int[] visited = new int[100001]; // 방문 배열 and 이동 수 함께 체크
        	
        	queue.offer(N); // 제일 처음 수빈이 위치 queue에 offer
        	visited[N] = 1; // 출력할 때 편하게 하기 위해 처음 위치부터 1초로 설정
        	
        	while(!queue.isEmpty()) {
        		int current = queue.poll();
        		
        		int next;
        		
        		for(int i = 0; i < 3; i++) { // X-1, X+1, 2*X 위치로 이동할 경우 모두 큐에 넣기 위한 for문
        			if (i == 0) {
        				next = current- 1;
        			} else if (i == 1) {
        				next = current + 1;
        			} else {
        				next = current * 2;
        			}
        			
        			if (next < 0 || next > 100000) { // 이동하려는 위치(next)가 범위 벗어나면
            			continue;
            		}
        			
        			if (visited[next] != 0) { // 이동하려는 위치(next)가 방문한적 있는 곳이면
        				continue;
        			}
            		
            		if (next == K) { // 동생을 찾은 가장 빠른 경우니까 바로 이동 수 출력하고 종료
            			System.out.println(visited[current]);
            			return;
            		}
            		
            		queue.offer(next); // 위의 모든 경우가 아니라면, 다음 위치를 queue에 offer
            		visited[next] = visited[current] + 1; // 이동하려는 곳에 move수 증가
        		}
        	}
    	}
    }
}