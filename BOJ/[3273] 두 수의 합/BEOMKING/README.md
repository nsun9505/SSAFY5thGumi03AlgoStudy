# [3237] 두 수의 합

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

        int T = Integer.parseInt(br.readLine());
        sequence = new int[T];
        st = new StringTokenizer(br.readLine());
        for (int t = 0; t < T; t++) {
            sequence[t] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        Arrays.sort(sequence);
        int count = 0;
        int start = 0;
        int end = T - 1;

        while(start < end){
            int sum = sequence[start] + sequence[end];
            if(sum == x){
                count++;
                start++;
                end--;
            }else if(sum > x){
                end--;
            }else{
                start++;
            }
        }
        System.out.println(count);
    }
}
```

## 문제 풀이
투 포인터 문제입니다.

저번주 이분탐색의 두 용액 문제를 푼 방식과 흡사합니다. 

- 배열을 정렬한 후 배열의 시작점과 끝점을 구합니다.

- 반복문의 조건(```1 ≤ i < j ≤ n 이기 때문에 두 값이 같을 수 없다```)을 주고 배열의 시작값과 끝값의 합을 구합니다.

- 합이 x와 같다면 count를 증가, start 값 증가, end값을 감소시킵니다.

   문제의 조건 ```n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다``` 서로 다른 양수이기 때문에 start와 end값을 유지시키고는 또 그 값이 나올 수 없습니다.

- 합이 x보다 크다면 끝 값을 줄여 합이 나올 범위를 작게 하고, 작다면 시작 값을 늘려 합이 나올 범위를 크게 합니다.

