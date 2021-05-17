# [14391] 종이 조각

## 분류
> 완전탐색

## 코드
```java
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

        Element elem = list.get(index);
        if (path[elem.row][elem.col] > -1) {
            solve(index + 1, sum);
            return;
        }

        // 가로
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
```

## 문제 풀이
완전탐색으로 풀 수 있는 문제입니다.

N, M의 최대 범위가 4이기 때문에 map 안에서 가로, 세로를 다 만들어서 더해주면 됩니다.

임의의 위치 (row, col)에서 가로 길이 1 ~ M까지 하나씩 증가시켜 보면서 가로 길이를 만들어보고

다음 위치들에서도 가로, 세로를 만들어보면서 합이 최대가 되는 것을  고르면 됩니다.

이미 사용되었다는 의미로 path를 사용해서 -1인 경우에만 해당 위치를 시작으로 가로, 세로를 만들어서 각 위치에서 가로로 했을 경우, 세로로 했을 경우 그리고 각 가로, 세로 길이에 따라 가질 수 있는 수를 sum에 더해서 최댓값을 찾습니다.

### 더 빠른 방법
어차피 (row, col)은 가로 또는 세로인 경우 밖에 없습니다.

어떤 위치(row, col)이 가로에 속하는 경우는 true, 세로에 속하는 경우는 false로 해서 (0, 0) 부터 찾아보면서 오른쪽으로 true가 이어져 있는지(가로), 아래로 false가 이어져 있는지(세로) 확인하면서 최댓값을 찾으면 됩니다.