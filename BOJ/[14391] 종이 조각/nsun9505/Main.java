import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static ArrayList<Element> list = new ArrayList<>();
    static int answer = 0;
    static int[][] map;
    static int[][] path;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        path = new int[N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                list.add(new Element(i,j));
                map[i][j] = input.charAt(j) - '0';
                path[i][j] = -1;
            }
        }

        solve(0, 0);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int index, int sum) {
        if (index == list.size()) {
            answer = Math.max(answer, sum);
            return;
        }
        // 가로
        Element elem = list.get(index);
        if (path[elem.row][elem.col] > -1) {
            solve(index + 1, sum);
            return;
        }

        int tmp = 0;
        for (int i = 0; i < M; i++) {
            int nx = elem.row + dx[0] * i;
            int ny = elem.col + dy[0] * i;

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                break;

            if (path[nx][ny] > -1) {
                solve(index + 1, sum + tmp);
                break;
            }

            path[nx][ny] = index;
            tmp = tmp * 10 + map[nx][ny];
            solve(index + 1, sum + tmp);
        }

        for (int i = 0; i < M; i++) {
            int nx = elem.row + dx[0] * i;
            int ny = elem.col + dy[0] * i;

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                break;

            if (path[nx][ny] == index)
                path[nx][ny] = -1;
            else
                break;
        }

        // 세로
        tmp = 0;
        for (int i = 0; i < N; i++) {
            int nx = elem.row + dx[1] * i;
            int ny = elem.col + dy[1] * i;

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                break;

            if (path[nx][ny] > -1) {
                solve(index + 1, sum + tmp);
                break;
            } else {
                path[nx][ny] = index;
                tmp = tmp * 10 + map[nx][ny];
                solve(index + 1, sum + tmp);
            }
        }

        for (int i = 0; i < N; i++) {
            int nx = elem.row + dx[1] * i;
            int ny = elem.col + dy[1] * i;

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                break;

            if (path[nx][ny] == index)
                path[nx][ny] = -1;
            else
                break;
        }
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}