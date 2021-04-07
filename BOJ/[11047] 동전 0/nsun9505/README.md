# [11047] 동전 0

## 분류
> 그리디

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
        int K = Integer.parseInt(st.nextToken());

        int[] moneys = new int[N];
        for(int i=0; i<N; i++)
            moneys[i] = Integer.parseInt(br.readLine());

        int answer = 0;
        for(int i=N-1; i>=0; i--){
            if(moneys[i] > K)
                continue;

            answer += (K / moneys[i]);
            K %= moneys[i];
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

```

## 문제 풀이
그리디 유형 대표 문제!!

가장 큰 금액부터 K를 나눠서 필요한 동전의 개수를 알아냅니다.
   - 대신, K와 같거나 작은 수인 경우에만 K를 나누고 나머지를 다시 K에 저장합니다.
   - K / moneys[i]는 동전의 수
   - K % moneys[i]는 남은 수

가장 큰 수부터 내려오면서 동전의 개수를 카운트할 수 있는 이유는 어떤 임의의 i번째 수에서 i보다 큰 수들은 모두 i를 최대 공약수로 가지고 있기 때문입니다.

문제에서 입력으로 주어지는 A(i+1)번째 수는 A(i)의 두 배이므로 
A(i + 2), A(i + 3), ... , A(N)은 모두 A(i)를 약수로 가지게 됩니다.
   - 거꾸로 생각해보면 A(i)에서 i가 1보다 큰 수라고 할 때
   - A(i) 이전에 있는 값들은 모두 A(i)의 약수가 될 것입니다.
   - 왜냐하면 A(i) 이전에 값들은 자기 다음의 값은 자기 자신의 배수이기 때문이죠.
   - 예를 들어, 10의 5배수의 2배수는 100이고, 100은 10을 약수로 갖습니다.
   - 이러한 성질을 만족하면 가장 큰 것부터 나눠서 몫을 구하는 것이 최고!

예를 들어, 10, 20, 40, 80, 160 이라고 했을 때, 300이 들어왔다고 해보겠습니다.
   - 이 경우에는 10, 20, 40, 80, 160의 최대 공약수는 10이므로 입력으로 들어온 수를 10으로 나누면 가장 큰 몫을 가지게 될 것이고
   - 20으로 나누면 2번째로 큰 몫, 40으로 나누면 3번째로 큰 몫, ... , 이렇게 160으로 나눠서 몫을 보면 가장 큰 값으로 나눈 몫의 값이 가장 작은걸 볼 수 있습니다.
   - 160 이전에 있는 수들은 모두 160의 약수이고, 160보다 작은 수로 K를 나누면 160으로 나눈 값 보다 클 수밖에 없습니다.
   - 그리고 160으로 나눈 값이 가장 작기 때문에 큰 수부터 나누는 것이 최소 동전의 개수를 만족시킬 수 있습니다.