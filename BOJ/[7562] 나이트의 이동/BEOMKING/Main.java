package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_7562_나이트의이동 {
    static int dy[] = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    static int dx[] = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int answer = 0;
            int I = Integer.parseInt(br.readLine());
            boolean visited[][] = new boolean[I][I];
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            Knight knight = new Knight(y, x, 0);
            st = new StringTokenizer(br.readLine());
            int ty = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());

            Queue<Knight> queue = new LinkedList<>();
            queue.offer(knight); // 시작점 추가
            visited[y][x] = true; // 방문 체크

            while(!queue.isEmpty()){
                Knight now = queue.poll();
                if(now.y == ty && now.x == tx) { // 현재 위치가 목표 위치와 같다면
                    answer = now.move;
                    break;
                }

                for (int i = 0; i < 8; i++) {
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];
                    if(ny < 0 || ny >= I || nx < 0 || nx >= I || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    queue.offer(new Knight(ny, nx, now.move + 1)); // 바뀐 위치, 움직인 거리
                }
            }
            sb.append(answer + "\n");
        }
        System.out.print(sb.toString().trim());
    }

    static class Knight{
        int y;
        int x;
        int move;
        public Knight(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
}
