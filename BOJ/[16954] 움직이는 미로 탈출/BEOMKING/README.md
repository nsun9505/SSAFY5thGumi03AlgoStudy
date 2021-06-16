# [16954] 움직이는 미로 탈출

## 분류

> BFS, DFS

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char Map[][] = new char[8][8];
    static int dy[] = {-1, 0, 1, 0, -1, -1, 1, 1, 0};
    static int dx[] = {-0, 1, 0, -1, -1, 1, 1, -1, 0};
    static int result = 0;
    static boolean start; // 첫 번째 dfs()에서는 벽이 내려오지 않음

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
```

## 문제풀이

DFS, BFS 모두 풀이가 가능하고 DFS로 풀었습니다.

고려해야할 사항으로는

- DFS로 풀 때는 변경된 맵을 전달 값에 넣어줘야합니다.
- 8방향이 아니라 그 자리에 그대로 있는 경우가 있습니다.
- 이동할 곳이 벽인지와 이동해서 벽의 이동 이후 현재 위치가 벽인지 확인해야합니다.

추가적으로 욱제의 위치가 0행에 도착한다면 굳이 0행 7열에 도착하는지 확인할 필요가 없습니다.

0행에 도착한다면 벽은 아래로 이동하므로 더 이상 벽이 욱제의 자리로 오는 일은 없습니다.

result 변수를 기저조건으로 주어서 욱제가 0행에 도착한 적이 있다면 result가 1이 되고 앞으로 모든 dfs()는 return 됩니다.

맨 처음 dfs 함수의 시작에는 벽을 이동해서는 안되기 때문에 start 변수를 사용했습니다.

(start 변수를 사용하지 않는 방법이 있는지?)
