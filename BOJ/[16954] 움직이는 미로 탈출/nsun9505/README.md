# [16954] 움직이는 미로 탈출

## 분류

> BFS

## 코드

```java
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
```

## 문제풀이

욱제가 갈 수 있는 위치를 '1'로 표시했습니다.

그래서 map[0][7]의 값이 '1'이라면 탈출이 가능한 경우고, 어떻게 해서도 map[0][7]에 '1'이 들어가지 않는다면 답을 0으로 찍으면 됩니다.

욱제가 움직인 위치에는 '1'을 넣어줍니다.

- 대각선 및 상하좌우 인접한 칸 중 '.'이라면 '1'을 마킹합니다.

그리고 벽이 이동합니다.

- 벽은 아래에 있는 행부터 순서대로 옮겨줍니다.
- 위에 있는 것부터 옮기면 바로 아래에 있는 벽을 없앨 수도 있고, 아래에 있는 벽이 움직일 때 움직인 벽을 없앨 수도 있습니다.

벽이 아래로 움직일 때는 '1'로 표시된 위치도 '#'으로 덮어버리므로 '벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없다'라는 조건을 만족합니다.

여튼, 욱제가 이동하고, 벽은 내려오고를 반복하다가 욱제가 더이상 이동할 수 없다면 답을 0으로 찍고, 어찌어찌 이동(BFS)해서 오른쪽 맨 위에 도달하면 1을 답으로 찍으면 됩니다.
