# [2263] 트리의 순회

## 분류

> 분할정복

## 코드

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] inorder;
    static int[] postorder;
    static int[] inorderPosition;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        inorder = new int[N];
        postorder = new int[N];
        inorderPosition = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderPosition[inorder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, N);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int inOrderStart, int postOrderStart, int length){
        if(length == 0)
            return;
        if(length == 1){
            sb.append(postorder[postOrderStart] + " ");
            return;
        }

        sb.append(postorder[postOrderStart + length - 1] + " ");
        int rootIdx = inorderPosition[postorder[postOrderStart + length - 1]];
        int leftSubTreeLen = rootIdx - inOrderStart;
        solve(inOrderStart, postOrderStart, leftSubTreeLen);

        int rightSubTreeLen = length - leftSubTreeLen - 1;
        solve(rootIdx+1, postOrderStart + leftSubTreeLen, rightSubTreeLen);
    }
}
```

## 문제풀이

inorder에 분할정복을 사용해서 문제를 해결했습니다.

inorder는 중간에 임의의 서브 트리의 루트를 기준으로 왼쪽 자식과 오른쪽 자식으로 나뉘게 됩니다.

postorder는 마지막에 있는 값이 트리의 루트라는 특징이 있습니다.

그러면 처음에 postorder의 마지막 값을 루트로 해서 inorder에서 어디에 위치하는지 찾습니다.

- 찾는 것은 따로 inorder에서의 순서를 저장한 배열을 사용했습니다.

inorder의 몇 번째에 위치하는지 찾았다면 그 위치를 기준으로 왼쪽은 왼쪽 자식, 오른쪽은 오른쪽 자식입니다.

이제 왼쪽 자식 트리의 크기를 구합니다.

왼쪽 자식 트리의 크기를 구하면 inorder의 시작 부분부터 해당 트리의 크기까지로 왼쪽을 탐색하면 됩니다.

오른쪽 트리를 탐색하는 방법은 postorder의 시작 인덱스만 구하면 됩니다.

오른쪽 서브트리의 크기는 현재 길이에서 왼쪽 서브트리의 크기를 빼주면 오른쪽 서브트리의 크기를 알 수 있습니다.

오른쪽 서브트리에서 inorder의 시작 인덱스는 rootIdx 다음이고, postorder의 시작인덱스는 왼쪽 서브트리의 크기를 postorder의 시작 인덱스를 더하면 됩니다.

왜냐하면 postorder의 결과값을 보면 왼쪽 서브트리의 루트 이후에는 반드시 오른쪽 서브트리의 값들만 있는 것을 볼 수 있기 때문에 postorder의 시작 인덱스에다가 왼쪽 서브트리의 크기 값을 더하면 오른쪽 자식의 postorder 시작 인덱스를 알 수 있습니다.

그리고 length가 0인 경우는 출력하지 않고, 1인 경우는 바로 출력하고 리턴하면 됩니다.
