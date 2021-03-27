package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1697_숨바꼭질 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(N, 0));
        Location location = null;
        int nx = 0;
        int answer = 0;
        boolean visited[] = new boolean[100001];

        while (!queue.isEmpty()){
            location = queue.poll();
            if(location.x > 100000 || location.x < 0) continue; // 수빈이의 위치가 범위 밖으로 갈 경우
            if(visited[location.x]) continue; // 이미 방문한 경우
            visited[location.x] = true; // 방문 처리
            if(location.x == M){ // 도착
                answer = location.second;
                break;
            }

            for (int i = 0; i < 3; i++) {
                if(i == 0){ // 텔포
                    nx = location.x * 2;
                }else if(i == 1){
                    nx = location.x + 1; // 일보
                }else{
                    nx = location.x - 1; // 뒤로 일보
                }
                queue.offer(new Location(nx, location.second + 1));
            }
        }
        System.out.print(answer);
    }
    static class Location{
        int x;
        int second;
        public Location(int x, int second) {
            this.x = x;
            this.second = second;
        }

    }
}
