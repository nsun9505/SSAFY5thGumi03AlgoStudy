# [6593] 상범빌딩

## 분류

그래프 이론

너비 우선 탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_상범빌딩 {
    static int L, R, C, check;
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int df[] = {1, -1};
    static boolean isselected[][][];
    static char Map[][][];
    static Queue<Location> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken()); // 층수
            R = Integer.parseInt(st.nextToken()); // 한 층의 행
            C = Integer.parseInt(st.nextToken()); // 한 층의 열
            if(L == 0 && R == 0 || C == 0) break; // TC 탈출 조건
            Map = new char[L][R][C];
            isselected = new boolean[L][R][C];
            queue = new LinkedList<>();
            check = -1;  // 탈출 가능 체크

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String row = br.readLine();
                    for (int k = 0; k < C; k++) {
                        char c = row.charAt(k);
                        if (c == 'S') { // 시작 지점 저장
                            queue.add(new Location(i, j, k, 0));
                            isselected[i][j][k] = true;
                        }
                        Map[i][j][k] = c;
                    }
                }
                br.readLine(); // 입력 중간에 공백란이 있음
            }

            while (!queue.isEmpty()) {
                Location now = queue.poll();
                if (Map[now.f][now.y][now.x] == 'E') { // 탈출
                    check = now.t;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int y = now.y + dy[i];
                    int x = now.x + dx[i];
                    if (y < 0 || y >= R || x < 0 || x >= C) continue;
                    if (Map[now.f][y][x] == '#' || isselected[now.f][y][x]) continue;
                    isselected[now.f][y][x] = true;
                    queue.add(new Location(now.f, y, x, now.t + 1));
                }
                for (int i = 0; i < 2; i++) {
                    int f = now.f + df[i];
                    if (f >= L || f < 0) continue;
                    if (Map[f][now.y][now.x] == '#' || isselected[f][now.y][now.x]) continue;
                    isselected[f][now.y][now.x] = true;
                    queue.add(new Location(f, now.y, now.x, now.t + 1));
                }
            }
            if (check != -1) sb.append("Escaped in " + check + " minute(s)." + "\n");
            else sb.append("Trapped!" + "\n");
        }

        System.out.println(sb.toString());
    }
    static class Location{
        int f, y, x, t;
        public Location(int f, int y, int x, int t) {
            this.f = f;
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }
}
```

## 문제풀이

BFS 문제입니다.

- 주의할 점
  - 입력이 좀 짜증납니다.
  - 3차원 배열로 해야하고 4방향 외에 위아래 두 방향이 추가됩니다.

시작 지점을 Queue에 저장하고 방문 체크를 해둡니다.

6방향을 모두 확인하면서 막혀있거나, 이미 방문했으면 넘어가고 아니라면 시간 + 1을 해서 Queue에 삽입합니다.

check 변수를 두어 조건을 만족했다면 시간을 넣고 아니면 Trapped! 이 출력되게 합니다.