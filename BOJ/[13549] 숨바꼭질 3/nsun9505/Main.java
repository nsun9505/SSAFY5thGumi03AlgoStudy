package BOJ.BOJ13549;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, K;
    static int[] visited = new int[200001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(visited, -1);

        Deque<Integer> queue = new LinkedList<>();
        visited[N] = 0;
        queue.offerLast(N);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(cur == K){
                sb.append(visited[cur]);
                break;
            }

            int incOne = cur + 1;
            int decOne = cur - 1;
            int mul = cur * 2;

            if(!isOutOfBound(mul) && !isVisited(mul)){
                queue.offerFirst(mul);
                visited[mul] = visited[cur];
            }

            if(!isOutOfBound(incOne) && !isVisited(incOne)){
                queue.offerLast(incOne);
                visited[incOne] = visited[cur] + 1;
            }

            if(!isOutOfBound(decOne) && !isVisited(decOne)){
                queue.offerLast(decOne);
                visited[decOne] = visited[cur] + 1;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isVisited(int X){
        if(visited[X] >= 0)
            return true;
        return false;
    }

    public static boolean isOutOfBound(int X){
        if(X < 0 || X > 200000)
            return true;
        return false;
    }
}
