# [1920] 수찾기

## 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, nl[], ml[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        nl = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nl[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nl);

        M = Integer.parseInt(br.readLine());
        ml = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            ml[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            if(find(ml[i])){
                sb.append(1 + "\n");
            }else{
                sb.append(0 + "\n");
            }
        }
        System.out.print(sb.toString());
    }
    static boolean find(int x){
        int start = 0, end = N - 1, mid;
        while (start != end){
            mid = (start + end) / 2;
            if(nl[mid] == x || nl[end] == x) return true;
            if(x > nl[mid]){
                start = mid + 1;
                continue;
            }
            if(x < nl[mid]){
                end = mid;
                continue;
            }
        }
        return false;
    }
}
```

## 문제 풀이
이분탐색 문제입니다.

```
입력으로 주어진 N 개의 숫자에서 M개의 데이터를 찾기 위해서는 O(N^2)

N이 100,000까지 주어지므로 시간 복잡도를 만족하지 못합니다.

이분탐색을 사용한다면 O(NlogN)에 가능합니다.
																					- 남썬의 시간복잡도 설명 中 발췌-
```

start, end 두 개의 포인터를 주었고 mid라는 중간 값을 구해주고 start와 end의 값이 같아질 때(start != end)까지 반복합니다.

찾는 값이 mid 보다 작다면 start = mid + 1을 해줍니다.

찾는 값이 mid 보다 작다면 end = mid 을 해줍니다.

이 풀이의 경우 start가 end와 같게 되면 종료가 되는데 

찾는 값이 1이고 end가 1이고 start가 0이라면  start가 1이 되어 end 값을 확인하지 못하고 종료됩니다.

그래서 찾는 값을 mid값뿐만 아니라 end 값과 비교해주었습니다. 그러면 값이 같아지기 전에 end값을 확인하고 종료할 수 있습니다.

그런데 애당초 반복 조건을 start <= end 로 준다면 값이 같아도 확인을 해주기 때문에 end 값을 비교할 필요도 없습니다.

