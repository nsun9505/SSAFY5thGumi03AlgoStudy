import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] map;
    static ArrayList<Element> list = new ArrayList<>();
    static int[] dx= {-1, 0, 1, 0};
    static int[] dy= {0, 1, 0, -1};
    static ArrayList<Element>[] wormhole = new ArrayList[5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<5; i++)
            wormhole[i] = new ArrayList<>();

        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            list.clear();
            for(int i=0; i<5; i++)
                wormhole[i].clear();
            StringTokenizer st = null;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 0){
                        list.add(new Element(i, j));
                    } else if(map[i][j] > 5){
                        wormhole[map[i][j]-6].add(new Element(i, j));
                    }
                }
            }

            int answer = 0;
            for(Element start : list){
                for(int dir=0; dir<4; dir++){
                    answer = Math.max(solution(start, start.row, start.col, dir, 0), answer);
                }
            }
            sb.append("#" + t + " " + answer + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(Element start, int row, int col, int dir, int sum) {
        int nx = row + dx[dir];
        int ny = col + dy[dir];

        if(nx < 0 || ny < 0 || nx >= N || ny >= N)
            return sum * 2 + 1;
        else if((nx == start.row && ny == start.col) || map[nx][ny] == -1){
            return sum;
        }
        else if(map[nx][ny] == 0){
            return solution(start, nx, ny, dir, sum);
        }
        else if(map[nx][ny] > 5){
            for(Element elem : wormhole[map[nx][ny]-6]){
                if(elem.row == nx && elem.col == ny)
                    continue;
                nx = elem.row;
                ny = elem.col;
                break;
            }

            return solution(start, nx, ny, dir, sum);
        }

        if(dir == 0){
            if(map[nx][ny] == 1 || map[nx][ny] == 4 || map[nx][ny] == 5)
                dir = (dir+2) % 4;
            else if(map[nx][ny] == 2)
                dir = 1;
            else if(map[nx][ny] == 3)
                dir = 3;
        }
        else if(dir == 1){
            if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5)
                dir = (dir+2) % 4;
            else if(map[nx][ny] == 3)
                dir = 2;
            else if(map[nx][ny] == 4)
                dir = 0;
        }
        else if(dir == 2){
            if(map[nx][ny] == 2 || map[nx][ny] == 3 || map[nx][ny] == 5)
                dir = (dir + 2) % 4;
            else if(map[nx][ny] == 1)
                dir = 1;
            else if(map[nx][ny] == 4)
                dir = 3;
        }
        else if(dir == 3){
            if(map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)
                dir = (dir + 2) % 4;
            else if(map[nx][ny] == 2)
                dir = 2;
            else if(map[nx][ny] == 1)
                dir = 0;
        }
        return solution(start, nx, ny, dir, sum+1);
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