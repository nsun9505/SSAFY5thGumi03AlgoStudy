# [1697] 숨바꼭질

## 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(N, 0));
        Location location = null;
        int nx = 0;
        int answer = 0;
        boolean visited[] = new boolean[100001];

        while (!queue.isEmpty()){
            location = queue.poll();
            if(location.x > 100000 || location.x < 0) continue; // 수빈이의 위치가 범위 밖으로 갈 경우
            if(visited[location.x]) continue; // 이미 방문한 경우
            visited[location.x] = true; // 방문 처리
            if(location.x == M){ // 도착
                answer = location.second;
                break;
            }

            for (int i = 0; i < 3; i++) {
                if(i == 0){ // 텔포
                    nx = location.x * 2;
                }else if(i == 1){
                    nx = location.x + 1; // 일보
                }else{
                    nx = location.x - 1; // 뒤로 일보
                }
                queue.offer(new Location(nx, location.second + 1));
            }
        }
        System.out.print(answer);
    }
    static class Location{
        int x;
        int second;
        public Location(int x, int second) {
            this.x = x;
            this.second = second;
        }

    }
}
```

## 문제풀이
BFS를 이용한 문제입니다.

제가 간과한 두 가지 점이 있었습니다.

- 먼저 특정 지점에 도착을 한다면 그 경우가 특정 지점으로 가는 가장 빠른 경우이기 때문에 방문 처리를 해야하고, 우선 순위 큐를 사용해서는 안됩니다.

- 또 수빈이는 앞 텔은 가능하지만 뒷 텔은 못하는 반푼이라는 점입니다.

  예로 100 0 이라면 수빈이는 뒤로 100 걸음을 가야합니다.

이 주의 사항을 지킨다면 쉽게 구현 가능합니다.

