# [13549] 숨바꼭질 3

## 분류
>- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
>- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
>- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
>- [다익스트라](https://www.acmicpc.net/problem/tag/22)
>- [0-1 너비 우선 탐색](https://www.acmicpc.net/problem/tag/176)

## 코드
``` java 
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 백준_13549_숨바꼭질3 {
    static int N, K, dis[];
    static PriorityQueue<Subin> pq;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        pq = new PriorityQueue<>();
        dis = new int[100001];
        Arrays.fill(dis,100001);
        pq.add(new Subin(N, 0));
        dis[N] = 0;

        if(N > K) System.out.println(N - K);
        else {
            while (!pq.isEmpty()) {
                Subin now = pq.poll();
                if (now.location == K) {
                    System.out.print(now.time);
                    break;
                }
                isavailable(now.location + 1, now.time + 1);
                isavailable(now.location - 1, now.time + 1);
                isavailable(now.location * 2, now.time);
            }
        }
    }
    static void isavailable(int location, int time){
        if(location > 0 && location <= 100000){
            if(dis[location] > time) {
                dis[location] = time;
                pq.add(new Subin(location, time));
            }
        }
    }
    static class Subin implements Comparable<Subin>{
        int location;
        int time;
        public Subin(int location, int time) {
            this.location = location;
            this.time = time;
        }
        @Override
        public int compareTo(Subin o) {
            return time - o.time;
        }
    }
}
```

## 문제풀이

가중치가 0인 간선이 있기 때문에 다익스트라로 풀어야합니다.

각 거리별 시간 배열을 생성해서 시간이 적은 경우에만 큐에 추가해줘야 메모리 초과가 안납니다.