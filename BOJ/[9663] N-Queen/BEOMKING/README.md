# [9663] N-Queen

## 분류

> 백트랙킹

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int map[][];
    static boolean check[][];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        check = new boolean[N][N];
        recursion(0);
        System.out.print(answer);
    }

    static void recursion(int n){
        if(n == N){
            answer += 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if(check[j][i]) {
                    flag = true;
                    break;
                }
                if(i - n + j >= 0) {
                    if (check[j][i - n + j]) {
                        flag = true;
                        break;
                    }
                }
                if(i + n - j < N) {
                    if (check[j][i + n - j]) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) continue;
            check[n][i] = true;
            recursion(n + 1);
            check[n][i] = false;
        }
    }
}
```

## 문제풀이

- DFS 문제입니다. 체스판의 각 줄에 퀸이 하나씩 들어갈 수 있는 경우의 수를 찾으면 됩니다.
- 체스판의 맨 윗줄부터 하나씩 놓는 경우의 수를 확인합니다.
- 현재 위치의 11시 12시 1시 방향을 모두 확인해 퀸이 존재하지 않다면 현재 위치는 가능하므로 방문 체크하고 다음 줄로 넘어갑니다.
- 퀸의 개수가 N이 된다면 조건을 만족하므로 정답을 1 증가 시킵니다.
- 재귀함수가 이미 완성이 되었든 조건에 맞지 않아 리턴된다면 현재 값의 방문체크를 해제합니다.

