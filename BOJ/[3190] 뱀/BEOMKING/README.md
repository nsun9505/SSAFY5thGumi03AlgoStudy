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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int map[][];
    static int dy[] = new int[]{0, 1, 0, -1}; // 동 남 서 북
    static int dx[] = new int[]{1, 0, -1, 0}; // 동 남 서 북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 맵 크기
        map = new int[N][N];
        int K = Integer.parseInt(br.readLine()); // 사과 개수


        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y - 1][x - 1] = 2; // 맵에 사과 표식
        }

        int L = Integer.parseInt(br.readLine());
        Queue<String[]> direction = new LinkedList<>(); // 이동 방향 정보를 담은 리스트
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            direction.add(new String[]{st.nextToken(), st.nextToken()});
        }

        map[0][0] = 1; // 시작점
        int dindex = 0; // 현재 방향
        int y = 0, x = 0;
        int time = 0; // 경과 시간
        Queue<int[]> queue = new LinkedList<>();

        while(true){
            int ny = y + dy[dindex];
            int nx = x + dx[dindex];
            if(ny < 0 || ny >= N || nx < 0 || nx >= N) break; // 벽에 박으면 사망
            if(map[ny][nx] == 0){ // 다음 칸에 아무것도 없으면
                if(!queue.isEmpty()) {
                    int tail[] = queue.poll(); // 꼬리가 있던 자리를 비움
                    map[tail[0]][tail[1]] = 0;
                    map[y][x] = 1; // 기존 머리가 있던 자리를 채움
                    queue.add(new int[]{y, x});
                }else{ // 큐가 비어있다면 기존에 있던 자리만 비움
                    map[y][x] = 0;
                }
                map[ny][nx] = 1; // 이동
                time += 1;
            }else if(map[ny][nx] == 1){ // 다음칸이 자신의 몸이라면
                break;
            }else{ // 사과
                queue.add(new int[]{y, x}); // 몸길이를 증가 시킴
                map[ny][nx] = 1;
                time += 1;
            }
            y = ny; x = nx;

            if(!direction.isEmpty()){
                String dir[] = direction.peek();
                if(time == Integer.parseInt(dir[0])){
                    if(dir[1].equals("D")){
                        if(dindex + 1 == 4){
                            dindex = 0;
                        }else{
                            dindex += 1;
                        }
                    }else{
                        if(dindex - 1 < 0) {
                            dindex = 3;
                        }else{
                            dindex -= 1;
                        }
                    }
                    direction.poll();
                }
            }
        }
        System.out.println(time + 1);
    }
}
```

## 문제 풀이
- 큐를 이용해 시뮬레이션 해서 풀었습니다.
- 다음 칸이 비었다면 꼬리가 있던 자리를 비우고 기존 머리가 있던 자리를 채움으로 뱀이 한 칸씩 이동하는 것을 구현했습니다.

- 벽이나 자신의 몸을 만나면 죽게 됩니다.
- 사과를 만난다면 큐에 머리가 있던 위치 값을 넣습니다.
- 매 이동이 끝날 때마다 방향 큐를 확인해 처리해줬습니다.