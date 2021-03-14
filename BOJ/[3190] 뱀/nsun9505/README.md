# [3190] 뱀

## 분류
> 구현
> 
> 자료구조
>
> 시뮬레이션
>
> 덱
>
> 큐

## 코드
```java
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] isUsed = new boolean[N][N];
        LinkedList<Element> snake = new LinkedList<>();
        int curDir = 1;
        int numOfApple = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int i=0; i<numOfApple; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        Queue<Direction> directionQueue = new LinkedList<>();
        int L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            directionQueue.offer(new Direction(sec, dir));
        }

        snake.offer(new Element(0, 0));
        isUsed[0][0] = true;

        int sec = 1;
        while(true){
            Element head = snake.peekFirst();
            int nx = head.row + dx[curDir];
            int ny = head.col + dy[curDir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                break;

            // 사과가 있는 경우
            if(map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } 
            // 사과가 없는 경우
            else {
                // 몸의 일부분이 있는 경우
                if(isUsed[nx][ny])
                    break;

                // 꼬리 부분 없애기
                Element tail = snake.pollLast();
                isUsed[tail.row][tail.col] = false;
            }

            // 머리 부분 추가하기
            isUsed[nx][ny] = true;
            snake.offerFirst(new Element(nx, ny));

            // X초 이후에 방향 바꾸기
            if(!directionQueue.isEmpty() && sec == directionQueue.peek().sec) {
                curDir = changeDirection(curDir, directionQueue.peek().dir);
                directionQueue.poll();
            }
            sec++;
        }
        sb.append(sec);
        bw.write(sb.toString());
        bw.flush();
    }

    public static int changeDirection(int curDir, char changeDir){
        if(changeDir == 'L')
            return curDir == 0 ? 3 : curDir - 1;
        return (curDir + 1) % 4;
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Direction{
        int sec;
        char dir;

        public Direction(int sec, char dir) {
            this.sec = sec;
            this.dir = dir;
        }
    }
}
```

## 문제 풀이
덱이란 자료구조가 가지는 특성을 사용하면 쉽게 풀 수 있습니다.

뱀이 이동하기 위해서는 머리 부분에 다음 위치를 추가하고, 꼬리부분을 지우면 이동하는 것처럼 보이게 됩니다.

그러면 사과가 있으면 꼬리 부분을 없애지 않고 머리부분만 추가합니다.

사과가 없다면, 꼬리 부분을 지우고 다음 위치를 머리에 추가합니다.

문제의 종료 조건으로 벽이나 자기 자신의 몸과 부딪히면 게임이 끝나게 됩니다.

벽을 만나는 경우는 인덱스 범위를 넘어서는 경우로 체크하고

자신의 몸과 부딪히는 경우는 배열을 통해서 자신의 몸이 있는 위치를 체크하는 배열을 둡니다.
   - 이동했을 때 이미 자신의 몸이 있다면, 바로 종료하면 되겠습니다.
   
자신의 몸도 없고, 벽에 부딪히는 것이 아니라면 머리 부분에 추가한 부분을 체크 배열표시하는 방식으로

시뮬레이션을 돌리면 됩니다.