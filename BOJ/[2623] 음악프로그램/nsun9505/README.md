# [2623] 음악프로그램

## 분류
> 위상정렬

## 코드
``` java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int indegree[];
    static List<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        graph = new LinkedList[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new LinkedList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for(int j=1; j<K; j++){
                int cur = Integer.parseInt(st.nextToken());
                indegree[cur]++;
                graph[prev].add(cur);
                prev = cur;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(indegree[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(cur+"\n");

            for(int next : graph[cur]){
                indegree[next]--;
                if(indegree[next] == 0)
                    queue.offer(next);
            }
        }

        boolean isOk = true;
        for(int i=1; i<=N; i++){
            if(indegree[i] > 0){
                isOk = false;
                break;
            }
        }

        if(!isOk){
            sb.setLength(0);
            sb.append(0);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
순서를 알기 위해서 위상정렬을 이용해서 문제를 풀었습니다.

가수들의 순서들을 알기 위해서는 먼저 자신 뒤에 먼저 나와야할 가수가 없으면 바로 무대에 설 수 있습니다.

즉, indegree가 0인 것을 먼저 출력하면 됩니다.

indegree가 0인 것들을 먼저 queue에 담고 빼면서 자기 다음에 무대에 올라갈 가수의 indegree에서 -1을 해줍니다.

만약 -1한 indegree가 0이라면 현재 가수 이전에 무대에 올라야 할 애들이 다 올라갔으므로 올라갈 수 있기 때문에 큐에 넣어줍니다.

0이 아니라면 그냥 넘어가면 됩니다.

이렇게 큐를 돌면서 indegree가 0인 애들을 큐에 넣고, 큐에서 뺀 가수 다음에 무대에 오를 가수의 indegree를 1씩 빼주면 됩니다.

만약 큐가 비어서 while문을 종료했는데, indegree가 0보다 큰 가수가 있다면 해당 가수는 무대에 오르지 못했으므로 0을 출력하고,

모두 0이라면 순서를 출력하면 됩니다.