package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_봄버맨 {
    static int R, C, N, time[][];
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 행
        C = Integer.parseInt(st.nextToken()); // 열
        N = Integer.parseInt(st.nextToken()); // 초

        map = new char[R][C];
        time = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'O'){
                    time[i][j] = 1; // 폭탄에 타이머
                }else{
                    time[i][j] = -1; // 폭탄 없음 표시
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if(i % 2 == 0){
                install(); // 설치
            }else{ // 폭파
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        if (map[j][k] == 'O') {
                            if(++time[j][k] == 3) {
                                bomb(j, k);
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void install() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 'O') {
                    map[i][j] = 'O'; // 폭탄 설치
                    time[i][j] = 0; // 폭탄 타이머 세팅
                }else{
                    time[i][j]++; // 기존 폭탄 타이머 증가
                }
            }
        }
    }
    private static void bomb(int y, int x) {
        map[y][x] = '.';
        time[y][x] = -1;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
            if(map[ny][nx] == 'O'){
                if(time[ny][nx] == 2){
                    bomb(ny, nx);
                }else{
                    map[ny][nx] = '.';
                    time[ny][nx] = -1;
                }
            }
        }
    }
}
