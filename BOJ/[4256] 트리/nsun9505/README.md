# [4256] 트리 

## 분류
> 분할정복


## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] preorder;
    static int[] inorder;
    static boolean[] visited;
    static int index = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            preorder = new int[N];
            inorder = new int[N];
            visited = new boolean[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                preorder[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                inorder[i] = Integer.parseInt(st.nextToken());


            index = 0;
            solve(0, N-1);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int start, int end){
        if(index >= N)
            return;

        int node = preorder[index++];
        visited[node] = true;

        if(start >= end){
            sb.append(node + " ");
            return;
        }

        int left = start;
        int mid = end;
        int right = end;
        for(int i=start; i<=end; i++){
            if(inorder[i] == node){
                mid = i;
                break;
            }
        }

        if(left <= mid-1)
            solve(left, mid-1);
        if(mid+1 <= right)
            solve(mid+1, right);
        sb.append(node + " ");
    }
}
```

## 문제 풀이
전위(preorder) 순회와 중위(inorder) 순회를 가지고 트리를 만들 수 있습니다.

왜냐하면 전위는 가장 먼저 각 서브 트리의 루트를 방문합니다.

inorder는 왼쪽 -> 루트 -> 오른쪽 순이므로 preorder에서 값 하나를 읽어서 해당 노드가 inorder 어디에 존재하는지 찾습니다.

preorder[i]가 inorder의 몇 번째에 위치하는지는 mid에 저장합니다.

그러면 inorder에서 mid를 기준으로 왼쪽(0 ~ mid-1)은 preorder[i]의 왼쪽 자식들, 오른쪽(mid + 1 ~ N-1)은 preorder[i]의 오른쪽 자식인 것입니다.
   - inorder는 왼쪽 자식 -> 부모 -> 오른쪽 자식 순으로 방문하기 때문에

그러면 preorder[i](i = 0 ~ N-1)까지 i를 하나씩 증가시키면서 inorder에서 찾고 반으로 나눠서 왼쪽, 오른쪽 탐색하고 preorder[i]번째를 마지막에 출력하면 postorder를 찾을 수 있습니다.