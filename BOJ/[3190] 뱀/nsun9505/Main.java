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