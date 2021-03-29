# [15652] N과 M(4)

## 분류
> 백트랙킹

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            solve(0, 1, new int[M]);

            bw.write(sb.toString());
            bw.flush();
            bw.close();
            br.close();
        }
    }

    public static void solve(int arrIndex, int start, int[] arr){
        // M개를 담았다면, 출력
        if(arrIndex == M){
            for(int i=0; i<M; i++)
                sb.append(arr[i] + " ");
            sb.append("\n");
            return;
        }

        // arrIndex 번째에 i 넣어주기
        for(int i=start; i<=N; i++){
            arr[arrIndex] = i;
            solve(arrIndex+1, i, arr);
        }
    }
}
```

## 문제풀이
arrIndex를 카운트로 해서 M개가 되는 시점에서 출력하고 돌아오도록 구현했습니다.

문제에서 비내림차순(=오름차순)으로 출력하기 위해서 1 ~ N까지 증가하도록 for문을 만들었습니다.

그리고 같은 수를 고를 수 있기 때문에, 이전에 골랐던 숫자도 고를 수 있도록 start를 이전 호출에서 담았던 숫자를 현재 solve의 for문 시작이 되도록해서 해결했습니다.
   - 이렇게하면 오름차순(같거나 증가하는)으로 숫자를 출력할 수 있습니다.