# [2293] 동전 1

## 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        int[] money = new int [M+1];
        for(int i=1; i<=N; i++)
            coin[i-1] = Integer.parseInt(br.readLine());

        money[0] = 1;
        for(int i=0; i<N; i++){
            // coin[i]로 만들 수 있는 경우의 수로 갱신
            for(int j=1; j<=M; j++){
                if(coin[i] <= j)
                    money[j] += money[j-coin[i]];
            }
        }

        sb.append(money[M]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
문제를 풀기 위해서 일단 모든 경우의 수를 적어봤습니다.

적다보니 구성은 같지만 순서가 다른 경우가 있는 것을 발견했습니다.
   - 예를 들면, {1, 2, 5}가 있을 때 3을 만들기 위해 {1, 2}, {2, 1}, {1, 1, 1}입니다.
   - 여기서 {1, 2}, {2, 1}은 구성은 같지만 순서만 달라서 다른 경우로 카운트 되기 때문에 
   - money[i] = money[i-1] + money[i-2] + money[i-5]와 같이 나타낼 수 없습니다.

그래서 동전 i번째로 만들 수 있는 경우의 수를 각 숫자(1 ~ M)에 대해서 경우의 수를 만들어 봅니다.
   - 1을 사용해서 1 ~ M을 만드는 각 경우의수
   - 1을 사용한 후에 거기에 2를 사용해서 1~M을 만드는 각 경우의 수
   - ...
   - coin[N-1]번째를 사용한 후에 거기에 coin[N]을 사용해서 1 ~ M을 만드는 각 경우의 수
   - 이렇게 알아내면 됩니다.

점화식은 다음과 같습니다.
```java
    if(coin[i] <= j) money[j] += money[j-coin[i]]; // coin[i]가 j보다 작다면 j - coin[i]만큼 더하기
    //else money[j] = money[j];                      // coin[i]가 j보다 크므로 coin[i]로 j를 나타낼 수 없으므로 할 필요 없음.
```

그리고 초기값을 신경써줘야 합니다.

`money[0] = 1`로 했습니다.

왜냐하면, coin[i]가 2고, j가 2이면 j - coin[i] = 0이므로 j번째를 나타낼 수 있는 경우의 수는 

아무 것도 없는 상태에서 coin[i]를 더하면 coin[i]번째가 될 수 있는 것이기 떄문입니다.

그리고 메모리가 4MB밖에 주어지지 않기 때문에 일차원 배열로 잡아서 풀면 메모리는 걱정하지 않아도 됩니다.