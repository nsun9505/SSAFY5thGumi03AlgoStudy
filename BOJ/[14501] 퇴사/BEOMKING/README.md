# [14501] 퇴사

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 남은 근무일 수
        int CT[] = new int[N + 1];
        int Value[] = new int[N + 1];
        int DP[] = new int[N + 1];
        
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            CT[n] = Integer.parseInt(st.nextToken());
            Value[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) { // 상담 일자
            int end = i + CT[i] - 1; // 상담이 끝나는 날짜
            if (end <= N) { // 끝나는 날이 근무날과 겹친다면
                DP[end] = Math.max(DP[end], DP[i - 1] + Value[i]); // 끝나는 날짜의 금액과 시작전날의 금액 + 시작날의 금액
            }
            DP[i] = Math.max(DP[i], DP[i - 1]); // 일을 하지 않았을경우
        }
        System.out.print(DP[N]);
    }
}
```

## 문제 풀이
DP 문제입니다.

   - 만약 7번째 날에 1일이 소요되는 상담이 있다면 그 상담은 7번째 날 종료됩니다.
   - 그렇기 때문에 상담이 끝나는 날짜를 현재 날짜 + 소요 시간 - 1을 해주었습니다. -1을 하지 않는다면 배열 범위를 벗어날 수 있습니다. (7 + 1 = 8)
   - 끝나는 날이 근무날 안에 있다면 상담이 끝나는 날에 현재 저장되 있는 값과 상담 전날까지 금액 + 상담 금액을 비교했습니다.
   - 일을 하지 않은 날은 전날의 금액과 저장되있는 금액을 비교해줬습니다.