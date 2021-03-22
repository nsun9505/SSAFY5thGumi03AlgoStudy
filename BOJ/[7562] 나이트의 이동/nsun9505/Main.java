import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1,  1,  2, 2, 1, -1, -2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        Element start = new Element(0 , 0, 0);
        Element end = new Element(0, 0, 0);
        boolean[][] visited = new boolean[300][300];
        Queue<Element> queue = new LinkedList<>();
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            for(int i=0; i<N; i++)
                Arrays.fill(visited[i], false);
            queue.clear();

            StringTokenizer st = new StringTokenizer(br.readLine());
            start.row = Integer.parseInt(st.nextToken());
            start.col = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            end.row = Integer.parseInt(st.nextToken());
            end.col = Integer.parseInt(st.nextToken());

            queue.offer(start);
            visited[start.row][start.col] = true;

            int answer = 0;
            while(!queue.isEmpty()){
                Element cur = queue.poll();

                if(cur.row == end.row && cur.col == end.col){
                    answer = cur.dist;
                    break;
                }

                for(int dir=0; dir<8; dir++){
                    int nx = cur.row + dx[dir];
                    int ny = cur.col + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    if(visited[nx][ny])
                        continue;

                    visited[nx][ny] = true;
                    queue.offer(new Element(nx, ny, cur.dist+1));
                }
            }
            sb.append(answer+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element {
        int row;
        int col;
        int dist;

        public Element(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}