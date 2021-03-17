# [16236] 아기상어 - JAVA

## 분류
> BFS
>
> 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int map[][];
    static boolean selected[][];
    static int fishnum = 0;
    static int time = 0;
    static Shark shark;
    static int dy[] = new int[]{-1, 0, 1, 0}; // 북 서 남 동
    static int dx[] = new int[]{0, -1, 0, 1}; // 북 서 남 동
    static Queue<Fish> fish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 맵 크기
        map = new int[N][N];
        shark = new Shark();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int fish = Integer.parseInt(st.nextToken());
                map[i][j] = fish;
                if (fish != 0) fishnum++; // 맵 안의 물고기 수
                if (fish == 9) {
                    shark.y = i;
                    shark.x = j;
                    map[i][j] = 0; // 상어 위치 초기화
                }
            }
        }
        while (bfs()) {
        }
        System.out.print(time);
    }
    static boolean bfs(){
        fish = new LinkedList<>(); // bfs를 위한 주변 확인 리스트
        selected = new boolean[N][N]; // 방문 체크
        fish.offer(new Fish(shark.y, shark.x, 0)); // 현재 위치 삽입
        selected[shark.y][shark.x] = true; // 현재 위치 방문 체크
        ArrayList<Fish> list = new ArrayList<>(); // 물고기가 있는 정보를 담은 리스트

        while(!fish.isEmpty() && fishnum > 1) { // 방문할 곳이 없거나 물고기의 수가 상어 밖에 없을 때
            Fish now = fish.poll();
            int y = now.y; int x = now.x;
            int dis = now.dis;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N || selected[ny][nx] || map[ny][nx] > shark.size) continue;
                selected[ny][nx] = true;
                fish.add(new Fish(ny, nx, dis + 1)); // 주변 탐색
                if(map[ny][nx] != 0 && map[ny][nx] < shark.size){
                    list.add(new Fish(ny, nx, dis + 1)); // 물고기 
                }
            }
        }
        if(list.size() == 0){ // 현재 거리에 물고기가 없다면
            return false;
        }
        Collections.sort(list); // 같은 거리의 물고기 정렬
        Fish temp = list.get(0);
        time += temp.dis;
        shark.y = temp.y;
        shark.x = temp.x;
        map[temp.y][temp.x] = 0;
        shark.eat++;
        if(shark.eat == shark.size){
            shark.size += 1;
            shark.eat = 0;
        }
        fishnum--;
        return true;
    }

    static class Shark{
        int size = 2;
        int eat = 0;
        int y; int x;

    }

    static class Fish implements Comparable<Fish>{
        int y; int x;
        int dis;
        public Fish(int y, int x, int dis){
            this.y = y;
            this.x = x;
            this.dis = dis;
        }
        @Override
        public int compareTo(Fish o) {
            if(dis == o.dis){
                if(y == o.y) {
                    return x - o.x;
                }else{
                    return y - o.y;
                }
            }else{
                return dis - o.dis;
            }
        }
    }
}
```

## 문제 풀이
- BFS를 이용해 시뮬레이션 하는 문제입니다.

예외 조건을 잘 확인해야합니다. 개인적으로 어려움을 겪은 조건은

- 같은 거리에 있다면 가장 위, 다 같은 높이라면 가장 왼쪽을 먹어야하는 조건

입니다. 탐색 방향을 북 서 남 동 순으로 하면 위쪽, 그 다음 왼쪽을 확인하므로 자연스럽게 해결 될 것이라 생각했지만 아니었습니다.

그렇기 때문에 현재 상어의 위치에서 주변 모든 정보의 거리를 구하고 정렬하는 방법을 사용해야합니다.