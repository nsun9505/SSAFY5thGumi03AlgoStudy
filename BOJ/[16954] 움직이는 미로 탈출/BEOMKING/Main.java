import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char Map[][] = new char[8][8];
    static int dy[] = {-1, 0, 1, 0, -1, -1, 1, 1, 0};
    static int dx[] = {-0, 1, 0, -1, -1, 1, 1, -1, 0};
    static int result = 0;
    static boolean start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            Map[i] = br.readLine().toCharArray();
        }
        dfs(7, 0, Map);
        System.out.print(result);
    }

    static void dfs(int y, int x, char translate[][]){
        if(result == 1) return;
        if(y == 0){ // 목적지에 도착했다면 (상단에 도착만 해도됨)
            result = 1;
            return;
        }

        char temp[][] = new char[8][8];
        if(start) { // 시작점이 아니라면 맵 복사
            for (int i = 0; i < 8; i++) {
                temp[0][i] = '.';
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    temp[i + 1][j] = translate[i][j];
                }
            }
        }else{
            temp = translate;
        }
        start = true;
        if(temp[y][x] == '#') return; // 벽이 캐릭터 위치로 왔다면

        for (int i = 0; i < 9; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= 8 || nx < 0 || nx >= 8) continue; // 범위 밖
            if(temp[ny][nx] == '#') continue; // 이동할 곳이 벽이라면
            dfs(ny, nx, temp);
        }
    }
}