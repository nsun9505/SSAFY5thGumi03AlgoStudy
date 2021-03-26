# [1697] 숨바꼭질

## 분류
> BFS

## 코드
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


## 문제풀이
- 수빈이가 이동할 수 있는 위치가 X-1, X+1, 2*X이니까 모든 경우를 고려하기 위해 BFS를 활용했습니다.
- 그리고 예를 들어 N이 (5)라면 처음에 queue에 (4, 6, 10)이 삽입되고, 그 다음 4가 queue에서 poll되면서 다시 (3, 5, 8)이 삽입되는데, 4로 인해 삽입된 5는 다시 똑같은 값을 queue에 넣으므로 중복된 결과를 낳습니다. 그렇기 때문에 방문 체크 배열이 필요한데, 수빈이가 동생을 만나는 "최단 경로"를 구하는 문제이기도 하므로 방문 체크 배열을 이동 시간까지 고려하는 int 배열로 선언했습니다. 만약 해당 위치가 0이 아니라면 이미 방문 했다는 의미이므로 queue에 넣지 않고 다음 경우로 넘어가며, 동생의 위치에 도착했다면 가장 빨리 도착한 경우일테니 바로 그 위치의 값을 반환하고 종료하면 됩니다.
- 처음에 next의 범위 체크하는 부분과 방문 체크하는 부분의 순서를 바꿔써서 어이없게 index 초과 에러가 떴으니 다음부터 주의,,,주의,,,
- 수빈이와 동생의 위치가 같다면 바로 0초를 반환하고 종료시키는 코드를 넣으면 더 간단하고 정확하게 구현할 수 있습니다.