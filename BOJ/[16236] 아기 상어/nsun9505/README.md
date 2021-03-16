# [16236] 아기상어 - JAVA

## 분류
> BFS
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Element> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st = null;

        // 처음 상어 위치 저장할 변수
        int sharkRow = 0;
        int sharkCol = 0;

        // 입력 받기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sharkRow = i;
                    sharkCol = j;
                }
            }
        }
        // 상어 클래스 정의
        // row, col, size, 먹은 횟수
        Shark shark = new Shark(sharkRow, sharkCol, 2, 0);

        // 돌아다닌 시간
        int answer = 0;

        // BFS의 리턴값이 0이면 더 이상 먹을 물고기가 없다는 것
        // 0 보다 크다면 먹을 물고기가 있다는 것.
        while(true) {
            int sec = BFS(shark);
            if(sec == 0)
                break;
            answer += sec;
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(Shark shark){
        // 큐와 방문 배열 초기화
        queue.clear();
        for(int i=0; i<N; i++)
            Arrays.fill(visited[i], false);

        // 큐에 처음 상어 위치 넣기 & 방문 표시
        queue.offer(new Element(shark.row, shark.col, 0));
        visited[shark.row][shark.col] = true;

        // 먹이가 있는 위치 초기화
        // minDist : 상어로부터 먹이까지의 거리
        int minDist = Integer.MAX_VALUE;

        // 먹이의 위치 row, col
        int minDistRow = N;
        int minDistCol = N;

        // BFS를 사용해서 먹이 탐색
        while(!queue.isEmpty()){
            Element cur = queue.poll();

            // cur.dist : 상어가 움직인 거리가 먹이가 있는 위치보다 크다면 더 이상 탐색할 필요 없음.
            // minDist가 처음에는 Integer.MAX_Value이다가, 상어가 먹을 수 있는 먹이를 만나면 minDist가 상어가 움직인 거리로 갱신된다.
            // 그래서 갱신된 minDist보다 큰 거리에 존재하는 것들은 더 이상 볼 필요가 없어서 BFS를 종료한다.
            // cur.dist가 minDist보다 큰 경우에 queue에 담겨 있는 모든 원소는 minDist보다 크므로 바로 종료해도 무관!
            // 왜냐하면 BFS를 돌렸을 때 
            if(cur.dist > minDist)
                break;

            // 4방향 탐색
            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                // 이동할 위치가 이미 방문했거나
                // 이동할 위치의 물고기의 크기가 아기 상어의 크기보다 큰 경우 지나가지 못함.
                if(visited[nx][ny] || map[nx][ny] > shark.size)
                    continue;

                // map[nx][ny] == 0 : 이동할 위치가 0(빈칸)이면 바로 지나갈 수 있음.
                // map[nx][ny] == shark.size : 이동할 위치의 물고기 크기가 아기 상어의 크기와 같으면 먹지는 못하지만 지나갈 수 있음.
                if(map[nx][ny] == 0 || map[nx][ny] == shark.size){
                    queue.offer(new Element(nx, ny, cur.dist+1));
                    visited[nx][ny] = true;
                }

                // 아기 상어의 크기보다 작은 물고기 발견!
                else if(map[nx][ny] < shark.size){
                    // minDist보다 작다면 해당 위치로 바꾼다.
                    if(cur.dist + 1 < minDist){
                        minDist = cur.dist + 1;
                        minDistRow = nx;
                        minDistCol = ny;
                    } 
                    // minDist와 같은 경우 
                    else if(cur.dist + 1 == minDist){
                        // 가장 위쪽에 있는 것을 선택한다. 그래서 row를 비교
                        if(nx < minDistRow){
                            minDistRow = nx;
                            minDistCol = ny;
                        } 
                        // row도 같다면 가장 왼쪽에 있는 것을 선택한다.
                        // 그래서 col을 비교
                        else if(nx == minDistRow){
                            if(ny < minDistCol){
                                minDistCol = ny;
                            }
                        }
                    }
                }
            }
        }

        // minDist가 변경되지 않았다는 것은 먹을 물고기가 없다는 것이므로 이제 엄마 상어에게로 간다~
        if(minDist == Integer.MAX_VALUE)
            return 0;

        // 위 if문을 통과했으면 먹을 수 있는 물고기가 존재!
        // 먹은 횟수를 증가
        shark.cntOfEat++;

        // 먹은 횟수와 아기 상어의 크기가 같아지면 
        // 아기 상어 크기를 하나 증가시킨다! (레벨업!!!)
        // 그리고 먹은 횟수를 0으로 초기화 해줘야 함!
        if(shark.cntOfEat == shark.size){
            shark.size++;
            shark.cntOfEat = 0;
        }
        // 아기 상어가 먹은 물고기 위치로 이동했으므로
        // 원래 있던 자리를 비우고, 먹은 물고기 자리도 비우고!
        // 아기 상어의 위치를 먹은 물고기의 위치로 변경시킨다.
        map[shark.row][shark.col] = 0;
        map[minDistRow][minDistCol] = 0;
        shark.row = minDistRow;
        shark.col = minDistCol;

        // 먹은 물고기까지의 거리를 리턴!
        return minDist;
    }

    static class Element{
        int row;
        int col;
        int dist;

        public Element(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static class Shark{
        int row;
        int col;
        int size;
        int cntOfEat;

        public Shark(int row, int col, int size, int cntOfEat) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.cntOfEat = cntOfEat;
        }
    }
}
```

## 문제 풀이
BFS를 사용해서 먹을 수 있는 물고기를 찾는 형식으로 문제를 해결했습니다!

물고기의 크기가 아기 상어의 크기보다 크다면 지나가지 못하므로 queue에 넣지 않습니다.

이동하려는 칸이 0(빈칸)이거나 아기 상어와 크기가 같으면 먹지는 못하지만 지나만 갈 수 있습니다.
   - 그러므로 queue에 넣어줍니다.

만약 아기상어의 크기보다 작은 물고기를 만났다면 minDist(먹을 수 있는 물고기까지의 거리)보다 작은지 검사합니다.
   - 처음에는 minDist를 Integer.MAX_VALUE로 초기값을 줘서 무조건 참이 됩니다.

minDist보다 작다면 먹을 수 있는 위치를 갱신합니다.

minDist와 같은 거리에 있는 물고기들을 만나면, row를 비교하여 더 작은 것을 선택하고

row도 같다면 col이 더 작은 것을 선택하여 먹을 수 있는 물고기의 위치를 문제에 맞게 알아냅니다.

BFS가 끝나고 먹을 수 있는 물고기가 없으면 0을 리턴하여 시뮬레이션을 종료하도록 합니다.

먹을 수 있는 물고기가 있다면, 아기 상어의 위치를 먹을 수 있는 물고기의 위치로 바꿔줍니다.
   - 그리고 원래 물고기가 있던 곳과 상어가 있던 곳을 0으로 비워줍니다.

물고기를 먹은 횟수가 상어의 크기와 같다면 상어의 크기를 하나 늘리고 먹은 횟수를 0으로 초기화합니다.

그리고 해당 물고기를 먹기 위해 움직인 거리를 리턴하면 됩니다.
   - 먹기 위해 움직인 거리나 이동한 시간은 동일한 의미입니다.