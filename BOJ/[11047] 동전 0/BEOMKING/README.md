# [11047] 동전 0

## 분류
> 그리디

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int Coin[] = new int[N];
        for (int i = 0; i < N; i++) {
            Coin[i] = Integer.parseInt(br.readLine());
        }
        int answer = 0;
        int i = N - 1;
        while(K != 0){
            if(K >= Coin[i]){
                answer += K / Coin[i];
                K %= Coin[i];
            }
            i--;
        }
        System.out.print(answer);
    }
}
```

## 문제 풀이
그리디 문제입니다.

큰 단위의 동전부터 비교합니다.
   - 금액과 같거나 작은 수인 경우에 현재 단위로 나누고 나머지를 다시 저장합니다.
   - 현재 단위로 나눈 몫을 결과에 추가합니다.
   - 나머지를 다시 K에 저장합니다.

남은 금액이 0이 되었을 때 반복은 멈추고 결과를 출력하면 끝납니다.