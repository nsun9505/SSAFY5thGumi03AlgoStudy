# [7562] 나이트의 이동

## 분류
> BFS

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1,  1,  2, 2, 1, -1, -2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Element start = new Element(0 , 0, 0);
        Element end = new Element(0, 0, 0);
        boolean[][] visited = new boolean[300][300];
        Queue<Element> queue = new LinkedList<>();
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            // 방문 표시 배열 초기화
            for(int i=0; i<N; i++)
                Arrays.fill(visited[i], false);
            // 큐 초기화
            queue.clear();
            // 시작 지점 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            start.row = Integer.parseInt(st.nextToken());
            start.col = Integer.parseInt(st.nextToken());
            // 끝나는 지점 입력 받기
            st = new StringTokenizer(br.readLine());
            end.row = Integer.parseInt(st.nextToken());
            end.col = Integer.parseInt(st.nextToken());
            // 시작 위치 큐에 넣고 방문 표시
            queue.offer(start);
            visited[start.row][start.col] = true;
            int answer = 0;
            // BFS!!!
            while(!queue.isEmpty()){
                Element cur = queue.poll();
                // 현재 지점이 끝나는 지점이라면 답으로 출력하기 위해
                // 끝나는 지점까지 걸린 이동 횟수를 저장하고 종료
                if(cur.row == end.row && cur.col == end.col){
                    answer = cur.dist;
                    break;
                }
                // 총 8 방향에 대해서 BFS를 돌립니다.
                for(int dir=0; dir<8; dir++){
                    int nx = cur.row + dx[dir];
                    int ny = cur.col + dy[dir];
                    // 체스판을 벗어나는 경우
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;
                    // 이미 방문한 경우
                    if(visited[nx][ny])
                        continue;
                    // 다음 위치가 체스판 안이고, 아직 방문하지 않았다면 방문
                    visited[nx][ny] = true;
                    queue.offer(new Element(nx, ny, cur.dist+1));
                }
            }
            sb.append(answer+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static class Element {
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
BFS를 사용하면 쉽게 풀 수 있습니다.

나이트가 최소 몇 번만에 이동할 수 있는지 알아보는 문제이므로 BFS를 사용하여 최소 거리를 구하면 되겠습니다.

이전까지는 4방향 상하좌우만 탐색했다면, 이 문제는 8방향에 대해 BFS를 돌리면 문제가 풀립니다.

그리고 처음 위치와 시작 위치가 같을 수 있으므로 다음 방문 위치가 끝나는 지점인지 체크하지 않고

현재 위치가 끝나는 위치인지 체크하도록 해서 문제를 풀었습니다.