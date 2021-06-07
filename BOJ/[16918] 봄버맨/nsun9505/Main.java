import java.io.*;
import java.util.*;

public class Main {
    static int R, C, N;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        times = new int[R][C];
        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++){
                if(map[i][j] == 'O')
                    times[i][j] = 1;
            }
        }

        for(int sec = 1;sec < N;){
            increaseBombTime();
            installBomb();
            sec++;
            if(sec >= N)
                break;

            increaseBombTime();
            sec++;
            List<Element> bombs = searchBurstBomb();
            burstBomb(bombs);
            if(sec >= N)
                break;
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++)
                sb.append(map[i][j]);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void installBomb(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == '.'){
                    map[i][j] = 'O';
                    times[i][j] = 1;
                }
            }
        }
    }

    public static void increaseBombTime(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == 'O')
                    times[i][j] += 1;
            }
        }
    }

    public static List<Element> searchBurstBomb(){
        ArrayList<Element> list = new ArrayList<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == '.')
                    continue;

                if(times[i][j] < 3)
                    continue;

                list.add(new Element(i, j));
            }
        }
        return list;
    }

    public static void burstBomb(List<Element> bombs){
        for(Element bomb : bombs){
            map[bomb.row][bomb.col] = '.';
            times[bomb.row][bomb.col] = 0;
            for(int dir=0 ;dir<4; dir++){
                int nx = bomb.row + dx[dir];
                int ny = bomb.col + dy[dir];

                if(isOutOfBounds(nx, ny))
                    continue;

                map[nx][ny] = '.';
                times[nx][ny] = 0;
            }
        }
    }

    public static boolean isOutOfBounds(int row, int col){
        if(row < 0 || col < 0 || row >= R || col >= C)
            return true;
        return false;
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
