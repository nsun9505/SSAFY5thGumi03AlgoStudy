import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static final int N = 8;
    static char[][] map = new char[N][N];
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<Element> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        map[7][0] = '1';
        while(!isEnd()){
            int ret = move();
            if(ret == 0)
                break;
            moveWall();
        }

        if(map[0][7] == '1') sb.append("1");
        else sb.append("0");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    public static int move(){
        list.clear();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == '1'){
                    for(int dir=0; dir<8;dir++){
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];

                        if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                            continue;

                        if(map[nx][ny] == '.')
                            list.add(new Element(nx,ny));
                    }
                }
            }
        }

        for(Element elem : list)
            map[elem.row][elem.col] = '1';

        return list.size();
    }

    public static boolean isEnd(){
        return map[0][7] == '1';
    }

    public static void moveWall(){
        for(int row=N-1; row>=0; row--){
            for(int col = 0; col<N; col++){
                if(map[row][col] != '#')
                    continue;

                map[row][col] = '.';
                int nx = row + 1;
                if(nx >= N) continue;

                map[nx][col] = '#';
            }
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