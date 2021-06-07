# [16918] 봄버맨

## 분류

> 구현

## 코드

```java
package BOJ.BOJ16918;

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

    // 빈칸에 폭탄 설치
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

    // 폭탄 시간초 증가시키기
    public static void increaseBombTime(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == 'O')
                    times[i][j] += 1;
            }
        }
    }

    // 폭발시킬 폭탄 찾기
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

    // 폭탄 터뜨리기
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
```

## 문제풀이

단순한 구현문제입니다.

문제의 조건에 따라 폭탄을 설치하고, 터지는 폭탄을 찾아서 터뜨리면 됩니다.

처음 시작 초를 1초로 잡은 이유는 프로그램이 시작한 후에는 봄버맨은 아무것도 하지 않기 때문에 1초로 잡았습니다.

그리고 문제의 조건에 따라 폭탄을 설치합니다.

현재 시간을 증가시킨 뒤에 N초가 지났는지 체크합니다.

N초가 지났다면 프로그램을 종료하면 됩니다.

N초가 지나지 않았다면, 터트릴 폭탄을 찾습니다.

터질 폭탄을 찾아서 바로 폭발시키지 않고, 리스트에 담아서 리턴합니다.

왜냐하면 인접한 폭탄이 같은 시간에 터지는데, 어떤 폭탄이 먼저 터져서 인접한 폭탄이 터지지 않는 경우가 있을 수 있기 때문에 list에 담았습니다.

list에 담은 후에 list에 담긴 폭탄들을 폭발시키면서 주위(4방향)를 모두 빈칸으로 만들어버리면 됩니다.

그리고 N초가 지났는지도 체크한 다음에 N초가 지났다면 프로그램을 종료하면 됩니다.
