# [13549] 숨바꼭질 3

## 분류
> 0-1 BFS

## 코드
``` java 
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

```

## 문제풀이
0-1 BFS로 풀었습니다.

0-1 BFS는 가중치가 0인 경우는 큐 앞에 넣어서 우선 처리하고, 가중치가 1늘어나는 것은 뒤에 넣어서 처리하는 방식입니다.

가중치가 0, 1 밖에 없을 때 사용하는 방법입니다.

문제에서는 순간이동할 때 0초가 걸린다고 했으니 현재 위치에서 0초가 증가해도 현재 위치의 시간초와 다른 것이 없으므로 큐 앞에 넣어서 뒤에 있는 것들 보다 먼저 처리합니다.

그리고 위치 X를 기준으로 앞 뒤로 움직일 떄는 1초가 걸리므로 X까지 걸린 시간에 +1을 해줘서 큐 뒤에 넣어줍니다.

동생위치에 도착한다면 동생이 있는 위치까지 걸린 시간을 출력하고 프로그램을 끝내면 됩니다.

큐에서는 자동으로 움직인 거리순이 아니라, 어떤 위치 M까지 가기 위해 걸린 시간으로 정렬되어 있으므로 우리는 최단 시간을 찾는 문제에 적용해서 풀 수 있습니다.