# [1253] 좋다

## 분류

자료 구조

정렬

이분 탐색

두 포인터

해시를 사용한 집합과 맵

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {
    static int N, result;
    static long number[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        number = new long[N];
        st = new StringTokenizer(br.readLine());
        result = 0;

        for (int i = 0; i < N; i++) {
            number[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(number);

        for (int i = 0; i < N; i++) {
            first(i);
        }
        System.out.print(result);
    }
    static void first(int now){
        for (int i = 0; i < N; i++) {
            if(i == now) continue;
            if(!second(now, i)) return;
        }
    }
    static boolean second(int now, int j){
        for (int i = j + 1; i < N; i++) {
            if(now == i) continue;
            long temp = number[j] + number[i];
            if(temp == number[now]){
                result++;
                return false;
            }
            if(temp > number[now]) return true;
        }
        return true;
    }
}

```

## 문제풀이

해시를 이용하거나 투포인터로 풀면 빠른 시간복잡도로 풀 수 있으나 저는 백트래킹을 이용한 노가다를 한 것 같습니다.

정렬을 시키고 완전 탐색을 진행하는데 2번째 값을 정하기 위해 반복문을 돌리는데 1번째 값과 2번째 값의 합이 찾는 값보다 크다면 2번째 값의 나머지 반복문은 돌릴 필요가 없습니다. (어짜피 다음 수부터는 무조건 더 크기 때문에)

값을 찾았다면 1번째 값을 찾는 반복문도 종료 시켰습니다. 