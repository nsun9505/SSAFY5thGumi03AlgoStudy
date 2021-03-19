# [5650] 핀볼 게임

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] map;
    static ArrayList<Element> list = new ArrayList<>();
    static int[] dx= {-1, 0, 1, 0};
    static int[] dy= {0, 1, 0, -1};
    static ArrayList<Element>[] wormhole = new ArrayList[5]; // 웜홀 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<5; i++)
            wormhole[i] = new ArrayList<>();

        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            list.clear();
            for(int i=0; i<5; i++)
                wormhole[i].clear();
            StringTokenizer st = null;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());

                    // 배열을 다 돌아보면서 0인 칸을 발견해서 하는 방식보다는
                    // 0인 위치를 따로 list에 저장해서 돌리는게 더 효율적이라고 생각합니다.
                    if(map[i][j] == 0){
                        list.add(new Element(i, j));
                    } 
                    // 웜홀 저장!
                    // 6이상이므로 -6을 해서 인덱스를 0부터 카운트 되도록 맞췄습니다.
                    else if(map[i][j] > 5){
                        wormhole[map[i][j]-6].add(new Element(i, j));
                    }
                }
            }

            // map에서 0인 모든 위치에 핀볼을 시작해보기
            // 상,하,좌,우 모든 방향 다~
            int answer = 0;
            for(Element start : list){
                for(int dir=0; dir<4; dir++){
                    answer = Math.max(solution(start, start.row, start.col, dir, 0), answer);
                }
            }
            sb.append("#" + t + " " + answer + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // start : 시작한 위치
    // row, col : 현재 위치
    // dir : 현재 방향
    // sum : 부딪힌 횟수
    private static int solution(Element start, int row, int col, int dir, int sum) {

        // 현재 위치에서 dir 방향으로 이동
        int nx = row + dx[dir];
        int ny = col + dy[dir];

        // 범위를 벗어나면 벽에 도달했던 경로를 똑같이 타고가므로
        // 지금까지 부딪힌 횟수 * 2를 하고 벽에 부딪힌거 +1을 해서 바로 리턴!
        if(nx < 0 || ny < 0 || nx >= N || ny >= N)
            return sum * 2 + 1;
        // 만약 벽(map을 벗어나는 것)도 안 부딪히고 잘 돌아서 원래 자리로 왔거나
        // 블록홀에 빠진 경우 지금 까지 부딪힌 횟수 리턴!
        else if((nx == start.row && ny == start.col) || map[nx][ny] == -1){
            return sum;
        }
        // 다음 칸에 아무 것도 없으면 이동
        else if(map[nx][ny] == 0){
            return solution(start, nx, ny, dir, sum);
        }
        // 다음 칸에 웜홀이 있다면
        else if(map[nx][ny] > 5){
            // 해당 웜홀과 쌍을 이루는 것을 찾아서 그곳으로 이동!
            // 방향도 유지!
            for(Element elem : wormhole[map[nx][ny]-6]){
                if(elem.row == nx && elem.col == ny)
                    continue;
                nx = elem.row;
                ny = elem.col;
                break;
            }

            return solution(start, nx, ny, dir, sum);
        }


        // 여기서부터는 다음 칸이 장애물인 경우! 1, 2, 3, 4, 5
        // 현재 방향이 위로 가는 방향이라면
        if(dir == 0){
            // 1, 4, 5의 경우 현재 방향의 반대 방향으로 이동하게 됨.
            // 반대방향으로 흘러갈 때는 지나왔던 경로를 그대로 가므로 현재까지 부딪힌 횟수 * 2 + 1(방금 부딪힌거)
            if(map[nx][ny] == 1 || map[nx][ny] == 4 || map[nx][ny] == 5)
                return sum * 2 + 1;
            else if(map[nx][ny] == 2)
                dir = 1;
            else if(map[nx][ny] == 3)
                dir = 3;
        }
        // 현재 방향이 왼쪽으로 가는 방향이라면
        else if(dir == 1){
            // 방향이 반대 방향으로 바뀌는 경우
            if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5)
                return sum * 2 + 1;
            // 방향이 아래로 바뀌는 경우
            else if(map[nx][ny] == 3)
                dir = 2;
            // 방향이 위로 바뀌는 경우
            else if(map[nx][ny] == 4)
                dir = 0;
        }
        // 아래부터는 위와 로직은 똑같고, 방향에 따라 반대 방향으로 가거나 장애물에 따라 방향이 바뀌게 됩니다.
        else if(dir == 2){
            if(map[nx][ny] == 2 || map[nx][ny] == 3 || map[nx][ny] == 5)
                return sum * 2 + 1;
            else if(map[nx][ny] == 1)
                dir = 1;
            else if(map[nx][ny] == 4)
                dir = 3;
        }
        else if(dir == 3){
            if(map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)
                return sum * 2 + 1;
            else if(map[nx][ny] == 2)
                dir = 2;
            else if(map[nx][ny] == 1)
                dir = 0;
        }
        return solution(start, nx, ny, dir, sum+1);
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
전형적인 시뮬레이션 문제 같습니다.

잘못하면 시간초과가 날 수도 있을 텐데 아마 시간 초과가 나면 왔던 길을 다시 돌아가서가 아닐까 싶습니다.

왔던 길을 다시 돌아가는 경우는 방향이 현재 방향의 반대 방향으로 바뀔 때가 아닐까 싶습니다.

방향이 현재 방향의 반대 방향으로 바뀌는 경우 지나왔던 경로를 그대로 가기에 부딪혔던 횟수 * 2에 방금 부딪힌거 +1을 리턴하면 될거 같습니다.

방향을 바꾸는 것은 현재 방향을 기준으로 장애물에 따라 달라집니다.

예를 들어 현재 방향이 위로 가는 방향이라면, 문제에서 보시다시피 장애물 1, 4, 5를 만났을 때는 부딪혀서 방향이 반대로 바뀌게 됩니다.

장애물 2를 만난 경우는 오른쪽으로 방향이 변경됩니다.

장애물 3을 만나면 왼쪽으로 방향이 변경됩니다.

이처럼 현재 방향에 따라 어떤 장애물을 만나냐가 방향을 정하므로 이것만 주의하시면 문제를 쉽게 풀 수 있을 것입니다.