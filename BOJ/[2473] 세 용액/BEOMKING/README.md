# [2473] 세 용액

## 분류
> 투 포인터

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473_세용액 {
    static int N;
    static long solution[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 용액 수
        solution = new long[N]; // 용액 (long으로 설정해야함 int형으로 값을 더하면 결과가 long형이더라도 범위 밖의 이상한 값이 나올수 있음)

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solution); // NlogN

        long diff = 5000000000L;
        long startsol = -1, midsol = -1, endsol = -1;

        for (int i = 0; i < N; i++) { // 기준이 되는 용액을 먼저 설정 (start와 end 조건을 먼저 줘서 기준 용액을 변경하면서 start와 end값을 조절한다면 다른 결과가 나온다.)
            int start = 0;
            int end = N - 1;
            while (start < end) {
                if (i == start){ // 서로 다른 용액이어야함
                    start++;
                    continue;
                }else if(i == end){
                    end--;
                    continue;
                }

                long sum = solution[start] + solution[i] + solution[end];

                if (Math.abs(sum) < diff) {
                    startsol = solution[start];
                    midsol = solution[i];
                    endsol = solution[end];
                    diff = Math.abs(sum);
                }

                if(sum > 0) end -= 1;
                else start += 1;
            }
        }
        long result[] = {startsol, midsol, endsol};
        Arrays.sort(result);
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
```

## 문제 풀이
투 포인터의 변형 문제입니다.

한 개의 기준점을 잡고 투 포인터를 사용하면 해결할 수 있습니다.

주의할 점이 두 가지있었습니다.

- 각 용액의 특성을 int가 아닌 long으로 해야합니다.

  저는 용액의 합을 담을 result만 long형으로 하면 문제가 없을 줄 알았으나 int를 더하면 결과도 int로 바뀝니다.

- for문으로 기준이 되는 용액을 먼저 설정하고 while문을 사용해야합니다.

  start end 값을 조건으로 주고 for문을 기준 용액을 바꾸면서 반복해도 같은 결과를 보여줄거라 생각했으나 모든 경우를 확인하지 못합니다.