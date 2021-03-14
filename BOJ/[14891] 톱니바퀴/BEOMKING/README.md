# [14891] 톱니바퀴

## 분류

> 구현
>
> 시뮬레이션

## 코드

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_14891_톱니바퀴 {
    static int map[][] = new int[4][8]; // 처음 4개의 배열을 만들었으나 이중 배열로 만들면 해결된다.
    static boolean check[] = new boolean[4]; // 바퀴를 돌렸는지 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = Integer.parseInt(str.substring(j, j + 1));
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            checkstatus(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            Arrays.fill(check, false);
        }

        int result = 0;
        int score = 1;
        for (int i = 0; i < 4; i++) {
            if(map[i][0] != 0){
                result += score;
            }
            score *= 2;
        }
        System.out.println(result);
    }
    static void checkstatus(int num, int dir) {
        if(check[num]) return; // 이미 처리한 바퀴면 리턴
        check[num] = true;

        int pre = num - 1;
        int next = num + 1;
        if(pre >= 0){ // 왼쪽에서 돌아갈 것들을 확인하고 변경
            if(map[num][6] != map[pre][2]){
                checkstatus(pre, (dir == 1) ? -1 : 1);
            }
        }
        if(next <= 3){ // 오른쪽
            if(map[num][2] != map[next][6]){
                checkstatus(next, (dir == 1) ? -1 : 1);
            }
        }

        move(num, dir); // 그 이후 본인을 돌린다. 이 부분을 돌려야할 바퀴를 확인하기 전에 처리했었는데 그 부분이 미스였다. 바퀴는 동시에 돌아가므로 미리 돌리면 다른 값이 나온다.
    }

    static void move(int num, int dir){
        if(dir == 1){
            int temp = map[num][7];
            for (int i = 7; i >= 1; i--) {
                map[num][i] = map[num][i - 1];
            }
            map[num][0] = temp;
        }else{
            int temp = map[num][0];
            for (int i = 0; i <= 6; i++) {
                map[num][i] = map[num][i + 1];
            }
            map[num][7] = temp;
        }
    }
}

```

## 문제 풀이

내가 멍청한 건지 문제 설명이 명확하지 않은건지 자석으로 되있기 때문에 바퀴를 돌리고 양 옆을 비교해야한다고 이해했는데 바퀴는 동시에 돌아가므로 돌아갈 것을 미리 돌리고 그 다음 본인것을 돌려야했다.

또한 배열을 처음 4개를 사용했는데 이중 배열을 사용하면 그럴 일은 없다.

배열을 왼쪽이나 오른쪽으로 이동시키기 위해 설마 그냥 일일이 옮겨야하나했는데 그게 맞다.