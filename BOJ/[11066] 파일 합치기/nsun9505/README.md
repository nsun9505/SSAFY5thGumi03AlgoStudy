# [11066] 파일 합치기 

## 분류
> DP

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int INF = 1000000000;
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N+1];
            int[] sum = new int[N+1];
            int[][] dp = new int[N+1][N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + arr[i];
            }

            for(int i=1; i<N; i++)
                dp[i][i+1] = arr[i] + arr[i+1];

            for(int r = 2; r<N; r++){
                for(int i=1; i + r <= N; i++){
                    int j = i + r;
                    dp[i][j] = INF;
                    for(int k=i; k<j; k++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1]);
                    }
                }
            }
            sb.append(dp[1][N]+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
DP 테이블을 잘 잡아서 문제를 풀어야 합니다.
   - 어려운 문제!!!

dp[i][j]는 i~j의 파일들을 합쳤을 때의 최소 비용입니다.

그러면 dp[1][3]이면 1 ~ 3까지 파일들을 합쳤을 때의 최소 비용입니다.

예시가 [40, 30, 30, 50]을 합쳤을 때 최소인 값을 구하려면 그룹을 나누고, 나뉜 각 그룹에서 최솟값을 더해서 [40, 30, 30, 50]을 합쳤을 때 최소값이 됩니다.

1 ~ 4까지 그룹을 나눠보면 [40, {30, 30, 50}], [{40, 30}, {30, 50}], [{40, 30, 30}, 50]으로 나누어 집니다.

만약에 [{40, 30}, {30, 50}]이 최솟값을 가진다면 식은 다음과 같습니다.

dp[1][4] = dp[1][2] + dp[3][4]

그리고 우리는 답을 구할 때 합칠 때마다 비용을 계산하기 때문에 i~j까지의 누적합을 구해서 사용하면 되겠습니다.

그러면 점화식은 다음과 같습니다.

dp[i][j] = MIN(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1])