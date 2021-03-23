import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 위치 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // BFS에 사용할 큐
        Queue<Integer> queue = new LinkedList<>();

        // 방문 체크 겸 이동 시간 기록에 사용할 배열
        // distance[X] = 0 이라면 아직 한 번도 방문하지 않음.
        // distance[X] != 0 이라면 이미 방문한 경우
        int[] distance = new int[100001];
        queue.offer(N);

        int answer = 0;
        // BFS!!!
        while(!queue.isEmpty()){
            int cur = queue.poll();

            // 현재 위치가 동생 위치라면 움직인 거리를 저장하고 종료
            if(cur == K){
                answer = distance[cur];
                break;
            }

            // X - 1 : -1하므로 배열 인덱스의 범위를 벗어나는지 체크, 아직 방문하지 않은지도 체크
            if(cur - 1 >= 0 && distance[cur-1] == 0){
                // 현재까지 움직인 거리 + 1해서 움직인 거리 갱신
                distance[cur-1] = distance[cur] + 1;
                queue.offer(cur-1);
            }

            // X + 1 : +1하므로 배열 인덱스의 범위를 벗어나는지 체크, 아직 방문하지 않은지도 체크
            if(cur + 1 <= 100000 && distance[cur+1] == 0){
                // 현재까지 움직인 거리 + 1해서 움직인 거리 갱신
                distance[cur+1] = distance[cur] + 1;
                queue.offer(cur+1);
            }

            // X * 2 : *2 하므로 배열 인덱스의 범위를 벗어나는지 체크, 아직 방문하지 않은지도 체크
            if(cur * 2 <= 100000 && distance[cur*2] == 0){
                // 현재까지 움직인 거리 + 1해서 움직인 거리 갱신
                distance[cur*2] = distance[cur] + 1;
                queue.offer(cur*2);
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}