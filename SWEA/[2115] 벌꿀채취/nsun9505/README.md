# [2115] 벌꿀채취

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] map;
    static int[][] memoization;
    static int[] tong;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            int ans = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            memoization = new int[N][N];
            tong = new int[M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            // M범위 미리 계산
            for(int i=0; i<N; i++){
                for(int j=0; j+M-1<N; j++){
                    memoization[i][j] = getHoney(i, j);
                }
            }

            ans = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j+M-1<N; j++){
                    // ret1, ret2 : 서로 다른 통에서 계산한 것을 저장, 대신 겹치지 않음.
                    // 또한, ret1의 통이 ret2의 통보다 앞에 있음!
                    int ret1 = memoization[i][j];
                    int ret2 = getOtherHoney(i, j+M);
                    ans = Math.max(ans, ret1 + ret2);
                }
            }

            sb.append("#" + t + " " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getOtherHoney(int row, int col){
        int max = 0;
        for(int i=row; i<N; i++){
            for(int j = (i == row ? col : 0); j+M-1 < N; j++){
                max = Math.max(max, memoization[i][j]);
            }
        }
        return max;
    }

    // 꿀 채집
    public static int getHoney(int row, int col){
        int idx = 0;
        // 꿀통 범위 만큼 담기
        for(int k=col; k<col+M; k++)
            tong[idx++] = map[row][k];

        int result = 0;
        // 꿀통이 만들 수 있는 부분집합구하기
        // 거기서 C 이하이면서 가장 많이 담을 수 있는 것을 구하기
        for(int i=1; i<(1<<M); i++){
            int tmp = 0;
            int sum = 0;
            for(int j=0; j<M; j++){
                if((i & (1 << j)) > 0){
                    sum += tong[j];
                    tmp += tong[j] * tong[j];
                }
            }
            if(sum <= C)
                result = Math.max(tmp, result);
        }

        return result;
    }
}
```

## 문제 풀이
먼저 N x N 행렬에서 각 행에서 열의 길이가 M만큼인 꿀통을 알아내닙다.

그리고 M만큼 길이에서 담을 수 있는 최대를 계산해서 memoization 배열에 담습니다!
   - 예를 들면, N=3, M=2, C=2인 경우라고 합시다.
      ```
        [1][2][3]
        [2][3][4]
        [3][4][5]
      ```
      - 첫 번째 행에서 길이가 M만큼인 꿀통이 나올 수 있는 경우는 [1][2], [2][3] 입니다.
      - 예로 [1][2] 꿀통에서 나올 수 있는 [1], [2], [1][2]입니다. 
      - 하지만 C가 2이므로 최대 2까지 밖에 담지 못하므로 [1], [2]만 통에 담을 수 있습니다.
      - 그래서 1^2과 2^2을 비교하여 둘 중 큰 것을 memoization[1][1]에 담으면 되겠습니다.
      - memoization[1][1]에서 [1][1]은 벌꿀을 채집하는 시작 위치입니다. 큰 의미는 없습니다.

그러면 벌꿀을 채집합 두 개의 M 조합을 찾는 것은 4중 for문을 통해서 쉽게 구할 수 있습니다.
   - 여기서 벌꿀 채집을 계산할 필요 없이 memoization에 계산되어 있는 값을 사용하면 됩니다.


