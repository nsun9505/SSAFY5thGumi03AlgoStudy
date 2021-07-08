package DFSBFS;

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
/*
public class 백준_13549_숨바꼭질3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());

        int subin = Integer.parseInt(st.nextToken());
        int dongsang = Integer.parseInt(st.nextToken());
        int visited[] = new int[100001];
        Arrays.fill(visited, 100001);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(subin);
        visited[subin] = 0;
        int answer = 0;

        while(!queue.isEmpty()){
            int now = queue.poll();
            if(now == dongsang){
                answer = visited[now];
            }

            if(now - 1 >= 0 && visited[now - 1] > visited[now] + 1){
                visited[now - 1] = visited[now] + 1;
                queue.offer(now - 1);
            }
            if(now + 1 <= 100000 && visited[now + 1] > visited[now] + 1){
                visited[now + 1] = visited[now] + 1;
                queue.offer(now + 1);
            }
            if(now * 2 <= 100000 && visited[now * 2] > visited[now]){
                visited[now * 2] = visited[now];
                queue.offer(now * 2);
            }
        }
        System.out.println(answer);
    }
}
 */
