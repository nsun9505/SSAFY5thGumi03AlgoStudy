# [9663] N-Queen

## 분류
> 백트랙킹

## 코드
```java

import java.io.*;

public class Main {
    static int N;
    static boolean[] colCheck;
    static boolean[] leftUpToRightDown;
    static boolean[] leftDownToRightUp;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        // 열 체크 배열
        colCheck = new boolean[N];

        // 왼쪽 상단에서 오른쪽 하단으로 향하는 대각선 체크 배열
        leftUpToRightDown = new boolean[2*(N-1)+1];

        // 왼쪽 하단에서 오른쪽 상단으로 향하는 대각선 체크 배열
        leftDownToRightUp = new boolean[2*(N-1)+1];

        solution(0);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int row){
        // row가 N이랑 같으면 N개 줄에 퀸을 N개 놓은 것이므로 answer++
        if(row >= N){
            answer++;
            return;
        }

        for(int col=0; col<N; col++){
            // 열, 대각선 검사
            if(colCheck[col] || leftUpToRightDown[row-col+N-1] || leftDownToRightUp[row+col])
                continue;

            // 열과 대각선에 해당 방향으로는 이미 퀸이 존재한다는 표시
            colCheck[col] = true;
            leftUpToRightDown[row-col+N-1] = true;
            leftDownToRightUp[row+col] = true;

            // N개 줄에 N개의 퀸이므로 한 행에 하나의 퀸 밖에 오지 못함.
            solution(row+1);

            // 돌아왔으므로 원상복구
            colCheck[col] = false;
            leftUpToRightDown[row-col+N-1] = false;
            leftDownToRightUp[row+col] = false;
        }
    }
}

```

## 문제풀이
퀸 하나를 놓고 대각선 검사를 하면 되지만, (row, col)의 관계로 대각선을 알아낼 수 있습니다.

왼쪽 하단에서 오른쪽 상단으로 올라가는 대각선을 보면, 각 원소가 row는 감소, col은 증가합니다.
   - 왼쪽 하단 -> 오른쪽 상단 대각선 검사는 row + col가 같은 애들이 같은 대각선에 존재합니다.
      ```
            [0,0][0,1][0,2]
            [1,0][1,1][1,2]
            [2,0][2,1][2,2]
      ```
    - (2, 0) 에서 왼쪽하단에서 오른쪽 상단으로 올라가는 대각선에 있는 위치는 (1,1), (0,2)입니다. 모두 row+col을 하면 똑같이 2인 것을 볼 수 있습니다.
    - (2, 1) 에서도 왼쪽하단 -> 오른쪽상단 대각선에 있는 위치들을 보면 (1,2)가 있습니다. (2,1) 과 (1,2) 모두 row+col을 하면 똑같이 3인 것을 볼 수 있습니다.
    - 그래서 왼쪽 하단에서 오른쪽 상단으로 가는 대각선 검사는 row+col를 인덱스로 해서 배열로 체크하면 됩니다.

왼쪽 상단에서 오른쪽 하단으로 내려가는 대각선은 각 원소가 row 증가, col 증가입니다.
```
    [0,0][0,1][0,2]
    [1,0][1,1][1,2]
    [2,0][2,1][2,2]
```
- (0, 0)에서 왼쪽 상단 -> 오른쪽 하단 대각선에 있는 원소들을 보면 (1, 1), (2, 2) 입니다. 
- 여기서는 조금 다르게 빼봅시다. 그러면 (0, 0) 에서 오른쪽 하단으로 내려가는 대각선에 있는 모든 위치는 0인 것을 볼 수 있습니다.
- (1, 0)에서 오른쪽 하단으로 내려가는 대각선에 있는 모든 위치의 `row - col`은 1인 것을 볼 수 있습니다.
- 그러면 (0, 1)에서 오른쪽 하단으로 내려가는 대각선에 있는 모든 위치에서 `row - col`을 계산해보면 -1인 것을 볼 수 있습니다.
   - col 값이 더 크기때문에!
- 그리고 (0, 2)에서도 `row-col`을 계산해보면 -2가 나오게 됩니다.
- 음수 값을 배열의 인덱스로 사용할 수 없으니 가장 작은 -2를 0으로 만들어주기 위해서 `row-col`에 `N-1`을 더해주면 됩니다.

문제는 DFS로 탐색하면서, 하나의 행에서 퀸을 놓을 때 대각선과 열을 체크해줍니다.

열과 대각선으로 퀸이 존재하지 않는다면, 현재 위치에 퀸을 놓았다는 표시를 열 체크, 대각선 체크 배열에 표시를 하고

다음 행에서 진행하도록 row 값을 +1해서 넘기면 됩니다.

그러다가 row가 N이 되면, 각 행에 퀸을 1개씩 놓은 경우이므로 경우의 수를 하나 증가시키면 됩니다.