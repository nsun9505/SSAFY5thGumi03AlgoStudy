# [2156] 포도주 시식

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int wine[] = new int[n + 3];
        int DP[] = new int[n + 3];
        for (int i = 3; i < n + 3; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 3; i <= n + 2; i++) {
            DP[i] = Math.max(DP[i - 1], Math.max(DP[i - 3] + wine[i - 1] + wine[i], DP[i - 2] + wine[i]));
        }
        System.out.print(DP[n + 2]);
    }
}
```

## 문제 풀이
DP 문제입니다.

포도주를 최대로 먹는 경우의 수는 

- -3번째 와인까지의 최대값 + -1 와인의  양 + 현재 와인의 양 (4 2 3 4)

  ```java
  DP[i - 3] + wine[i - 1] + wine[i]
  ```

- -2번째 와인까지의 최대값 + 현재 와인의 양 ( 3 4 2 4 )

  ```java
  DP[i - 2] + wine[i])
  ```

- -1번째 와인까지의 최대값 ( 2 5 5 1 )

  ```java
  DP[i - 1]
  ```

이 3가지를 고려해야하면 문제를 해결할 수 있습니다.

