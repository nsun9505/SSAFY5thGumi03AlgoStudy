# [15652] N과 M(4)

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int permu[];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        permu = new int[M];
        permutation(1, 0);
        System.out.print(sb.toString().trim());
    }
    static void permutation(int start, int count){
        if(count == M){
            for (int i = 0; i < M; i++) {
                sb.append(permu[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            permu[count] = i;
            permutation(i, count + 1);
        }
    }
}

```

## 문제풀이

중복을 허용하는 조합 문제입니다.

시작 값과 개수 값을 인자로 넣어 해결했습니다.

시작 값이 증가하면서 앞에서 이미 만들어진 순열을 만들지 않게 합니다.

개수의 값이 M과 같아진다면 순열을 저장합니다.