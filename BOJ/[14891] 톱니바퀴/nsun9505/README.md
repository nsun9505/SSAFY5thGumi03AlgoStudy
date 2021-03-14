# [14891] 톱니바퀴

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr = new int[4][8];
    static int[] shiftArr = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<4; i++){
            String input = br.readLine();
            for(int j=0; j<input.length(); j++){
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            Arrays.fill(shiftArr, 0);
            shiftArr[start] = dir;
            left(start, dir);
            right(start, dir);

            for(int j=0; j<4; j++){
                if(shiftArr[j] == 0)
                    continue;
                shift(j, shiftArr[j]);
            }
        }

        int ans = 0;
        if(arr[0][0] == 1) ans += 1;
        if(arr[1][0] == 1) ans += 2;
        if(arr[2][0] == 1) ans += 4;
        if(arr[3][0] == 1) ans += 8;
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void left(int cur, int dir){
        if(cur - 1 < 0)
            return;

        if(arr[cur-1][2] == arr[cur][6])
            return;

        shiftArr[cur-1] = dir == 1 ? -1 : 1;
        left(cur-1, shiftArr[cur-1]);
    }

    public static void right(int cur, int dir){
        if(cur + 1 >= 4)
            return;

        if(arr[cur][2] == arr[cur+1][6])
            return;

        shiftArr[cur+1] = dir == 1 ? -1 : 1;
        right(cur+1, shiftArr[cur+1]);
    }

    public static void shift(int cur, int dir){
        if(dir == -1){
            int tmp = arr[cur][0];
            for(int i=1; i<=7; i++)
                arr[cur][i-1] = arr[cur][i];
            arr[cur][7] = tmp;
        } else {
            int tmp = arr[cur][7];
            for(int i=7; i>=1; i--)
                arr[cur][i] = arr[cur][i-1];
            arr[cur][0] = tmp;
        }
    }
}
```

## 문제 풀이
각 톱니바퀴를 나타내기 위해 12시방향부터 11시 방향까지 8개의 극을 저장할 배열이 필요하고, 이런 톱니바퀴가 4개가 있으므로 아래와 같이 선언하였습니다.
   - `arr[4][8]` : 4개의 톱니바퀴, 각 톱니바퀴의 돌기부분(8개 : 0~7)

처음 톱니바퀴를 돌리는 부분에서 왼쪽과 오른쪽으로 탐색하는 과정으로 구현하였습니다.
   - 처음 돌리는 톱니바퀴를 X번이라고 할 때 X의 왼쪽들을 검사하고, 오른쪽을 검사하면 됩니다.
   - 왼쪽으로 검사할 때는 왼쪽 톱니바퀴만 검사하고, 오른쪽으로 검사할 때는 오른쪽 톱니바퀴만 검사합니다.

### 왼쪽 방향으로 검사 예시
X의 9시 방향 극과 X-1의 3시 방향 극을 검사하고, 같으면 진행하지 않습니다.
   - 같지 않다면 X-1은 X가 회전하는 방향의 반대 방향으로 회전한다고 배열에 표시합니다.
   - 그리고 X-1의 왼쪽을 검사하기 위해 다시 left()를 호출하면서 극이 같아지거나, 더 이상 체크할 것이 없을 때까지 진행하면 됩니다.

### 오른쪽 방향으로 검사 예시
X의 3시 방향 극과 X+1의 9시 방향 극을 검사하고, 같으면 진행하지 않습니다.
   - 같지 않다면 X+1은 X가 회전하는 방향의 반대 방향으로 회전한다고 배열에 표시합니다.
   - 그리고 X+1의 오른쪽을 검사하기 위해 다시 right()를 호출하면서 극이 같아지거나, 더 이상 체크할 것이 없을 때까지 진행하면 됩니다.

### 실제로 회전시키기
left()와 right() 호출이 모두 끝나면 이제 각 톱니바퀴가 어느 방향으로 회전하는지 보고 회전시키면 됩니다.
   - 0이면 회전시키지 않고, 1은 시계방향, -1은 반시계방향으로 돌려주면 됩니다.