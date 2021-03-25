# [16920] 확장 게임

## 분류

## 코드
```java
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, P;
    static char[][] map;
    // 확장 가능한 성들이 들어가는 queue : 즉, 상하좌우에 '.'이 있는 성들만 queue에 들어감.
    static Queue<Element>[] playerQueue;

    // BFS에 사용할 queue
    static Queue<Element> queue = new LinkedList<>();

    // 각 플레이어의 성 개수
    static int[] countOfArea;

    // 각 플레이어가 한 번에 확장하는 거리
    static int[] distanceOfPlayer;

    // 4방향 탐색
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        // 각 플레이어마다 확장 가능한 성을 담아 놓은 queue를 갖고 있음.
        playerQueue = new Queue[P+1];
        map = new char[N][M];
        countOfArea = new int[P+1];
        distanceOfPlayer = new int[P+1];

        st = new StringTokenizer(br.readLine());
        // 각 플레이어가 한 번에 확장할 수 있는 거리
        for(int i=1; i<=P; i++)
            distanceOfPlayer[i] = Integer.parseInt(st.nextToken());

        // 맵 입력 받기
        for(int i=0; i<N; i++)
            map[i] = br.readLine().toCharArray();

        // 각 플레이어의 큐를 할당
        for(int i=1; i<=P; i++)
            playerQueue[i] = new LinkedList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == '.' || map[i][j] == '#') {
                    continue;
                }

                // '1', '2', ... , '9' 인경우
                int playNumber = map[i][j] - '0';
                countOfArea[playNumber] += 1;

                // 주변에 '.'이 있는 성은 확장 가능한 성
                for(int dir=0; dir<4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;

                    if(map[nx][ny] == '.'){
                        // '.' 상하좌우 중 인접한 칸 중 하나라도 '.' 이면 
                        // 해당 플레이어의 확장 가능한 성만 담는 큐에 넣어주면 됩니다.
                        playerQueue[playNumber].add(new Element(i, j, 0));
                        break;
                    }
                }
            }
        }

        while(true){
            int sum = 0;
            for(int i=1; i<=P; i++)
                sum += BFS(i);
            
            // 1 ~ P까지 다 돌았는데 sum이 0이면 더 이상 확장할 성이 없으므로 종료
            if(sum == 0)
                break;
        }

        for(int i=1; i<=P; i++)
            sb.append(countOfArea[i] + " ");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int playerNumber){
        queue.clear();
        // BFS를 돌릴 queue에 플레이어의 확장 가능한 성을 담은 큐에 있는 것을 모두 넣어준다.
        queue.addAll(playerQueue[playerNumber]);

        // 확장되는 성들이 담긴 큐는 다음에는 확장이 불가능하므로 비워준다.
        playerQueue[playerNumber].clear();
        int count = 0;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            // 4방향 탐색
            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                // Out of Bound 검사
                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 상하좌우 중 인접한 칸이 '.'인 경우
                if(map[nx][ny] == '.'){

                    // 해당 위치를 플레이어의 숫자로 초기화
                    map[nx][ny] = (char)('0' + playerNumber);

                    // 해당 플레이어가 가진 성의 수를 증가시키기.
                    countOfArea[playerNumber]++;

                    // elem.dist + 1이 현재 플레이어의 이동 가능한 거리가 된다면
                    // 해당 위치는 다음 차례에 확장 가능한 위치이므로 확장 가능한 큐에 넣어줍니다.
                    if(elem.dist + 1 == distanceOfPlayer[playerNumber]){
                        playerQueue[playerNumber].add(new Element(nx, ny, 0));
                        count++;
                    } else {
                        // elem.dist + 1이 플레이어의 최대 이동 거리보다 작다면 BFS에서만 활용!
                        queue.offer(new Element(nx ,ny, elem.dist + 1));
                    }
                }
            }
        }
        return count;
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
}
```

## 문제풀이
BFS를 사용해서 문제를 해결했습니다.

플레이어마다 큐를 할당해서, 플레이어가 가진 성에서 상하좌우 인접한 칸에 '.'가 있는 성들만 이 큐에 들어가게 됩니다.
   - 인접한 칸에 '.'가 있는 성은 BFS를 돌렸을 때 플레이어가 이동할 수 있는 최대거리만큼 이동했을 때의 위치에서만 검사하면 되겠습니다.
   - 만약, 2만큼 이동할 수 있다면 이동 거리가 1인 위치들은 2로 감싸져 버리기 때문에 고려할 필요가 없습니다.

그리고 더 이상 확장이 되는지 안 되는지 알기 위해서는 BFS에서 확장을 했을 때마다, 즉, 최대 거리만큼 움직이면서 '.'인 칸에 자신의 성을 확장시킨 횟수를 리턴합니다.
   - 각 플레이어가 확장한 성의 개수를 모두 더해서 0이면 더 이상 확장할 수 없는 경우이므로 종료하면 되겠습니다.