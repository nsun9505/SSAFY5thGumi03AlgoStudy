# [1654] 랜선 자르기

## 분류
> 이분탐색
>
> 파라메트릭 서치

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, lan[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가진 랜선 갯수
        M = Integer.parseInt(st.nextToken()); // 필요 갯수
        lan = new int[N];

        for (int i = 0; i < N; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lan);

        long start = 1;
        long end = lan[N - 1];
        long height = 0;
        long mid;

        while(start <= end){
            mid = (start + end) / 2;
            int nowlan = 0;
            for (int i = 0; i < N; i++) {
                nowlan += lan[i] / mid;
            }

            if(M <= nowlan){
                start = mid + 1;
                height = Math.max(height, mid);
            }else{
                end = mid - 1;
            }
        }
        System.out.print(height);
    }
}
```

## 문제 풀이
파라메트릭 서치 문제입니다.

나무자르기와 거의 비슷한 문제라고 생각합니다.

적어도 M개 이상의 랜선을 만들어야하기 때문에 다음과 같은 과정을 거칩니다.

- for문을 돌면서 mid로 나눈 몫을 합한 값(nowlan)을 구한다.
- 합한 값이 M 이상이라면 mid와 현재까지의 최대 높이를 비교한다.

라면 해결이 됩니다.

주의해야할 점은 두 가지 있었습니다.

- 처음 아무생각 없이 시작값을 0으로 주었는데 이 경우

  5 5
  1
  1
  1
  1
  1

  0으로 나눠지는 에러가 걸리게 됩니다.

- 이 문제도 long형을 사용해야합니다.

  4 4
  2147483647
  2147483647
  2147483647
  2147483647

  이 경우도 처음 mid 10억으로 나눌 경우 nowlan이 8이 나와 M 이상이 됩니다.

  그렇기 때문에 다음 반복에서 (start + end)가 30억을 초과하기 때문에 정확한 값이 출력되지않습니다.



