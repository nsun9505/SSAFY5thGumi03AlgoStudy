# [2470] 두 용액

## 분류
> 이분탐색
>
> 투포인트

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, solution[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 용액 수
        solution = new int[N]; // 용액

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solution);

        int start = 0;
        int end = N - 1;
        int diff = Integer.MAX_VALUE;
        int startsol = -1, endsol = -1;

        while (start < end){
            if(solution[start] + solution[end] == 0){
                startsol = solution[start];
                endsol = solution[end];
                break;
            }

            if(Math.abs(solution[start] + solution[end]) < diff){
                startsol = solution[start];
                endsol = solution[end];
                diff = Math.abs(solution[start] + solution[end]);
            }

            if(solution[start] + solution[end] > 0){
                end -= 1;
            }else{
                start += 1;
            }
        }
        System.out.print(startsol + " " + endsol);
    }
}
```

## 문제풀이
아마 투포인터로 해결한 것 같습니다.

배열을 정렬해주고 초기 값은 배열의 시작값(start, 0)과 끝 값(end, N - 1)로 주었습니다.

- 같은 용액의 특성을 합하지 않으므로 반복문의 조건은 start < end 입니다. <=을 한다면 같은 용액까지 계산하게 됩니다.

- 용액의 start와 end의 합이 0이라면 현재 값을 저장하고 종료합니다.

- 현재 저장되어있는 최소 차이보다 현재 차이가 더 작다면 갱신시킵니다.

- 두 용액의 합이 0보다 크다면 end의 값이 상대적으로 더 커서 그런 것이므로 -1, 작다면 start가 상대적으로 더 작아서 +1을 해줍니다.

  N의 범위가 100000이기 때문에 시간복잡도는 문제되지않습니다.