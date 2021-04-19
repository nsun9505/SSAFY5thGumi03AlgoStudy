# [1806] 부분합

## 분류
> 투포인트

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sequence[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        sequence = new int[N + 1]; // new int[N] 반복 조건 2
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            sequence[n] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = sequence[0];
        int answer = Integer.MAX_VALUE;

        while(end < N){ // while(start < N) 반복 조건 2
            if(sum >= S){
                answer = Math.min(answer, end - start + 1);
                sum -= sequence[start++];
            }else{
                //if(end == N - 1){ 반복 조건 2
                //    break;
                //}
                sum += sequence[++end];
            }
        }
        if(answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);
    }
}
```

## 문제 풀이
투 포인터 문제입니다.

연속합을 계속 갱신하면서 연속 개수도 함께 갱신하면 됩니다.

- 두 개의 포인터를 시작 값 0으로 초기화 시킵니다. 시작 값 하나의 값을 가진 sum도 생성합니다.
- 반복문의 조건을 줍니다. end가 N보다 크면 범위를 벗어나므로 종료시킵니다. 
- 찾는 값보다 현재 합이 큰 경우 현재 길이를 비교해 갱신하고 현재 합에서 start의 값을 빼고 start를 1 증가시킵니다.
- 반대의 경우 증가시킨 end의 값을 합해줍니다.

