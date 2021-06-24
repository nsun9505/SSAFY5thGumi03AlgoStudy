# [3980] 선발명단

## 분류
> 완전탐색

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int N = 11;
    static int[][] ability = new int[N][N];
    static boolean[] used  = new boolean[N];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int t=0; t<T; t++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    ability[i][j] = Integer.parseInt(st.nextToken());
            }
            answer = 0;
            solve(0, 0);
            sb.append(answer +"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int posIdx, int score){
        if(posIdx >= N){
            answer = Math.max(answer, score);
            return;
        }

        for(int i=0; i<N ; i++){
            if(used[i] || ability[i][posIdx] == 0)
                continue;

            used[i] = true;
            solve(posIdx + 1, score + ability[i][posIdx]);
            used[i] = false;
        }
    }
}
```

## 문제풀이
완전탐색으로 풀었습니다.

먼저 아직 다른 포지션에 할당되지 않았고, 현재 뽑으려고 하는 포지션에 대해서 능력치가 0이 아닌 선수를 찾습니다.

찾았다면, 해당 선수를 현재 포지션에 할당했다는 것을 표시하고, 해당 선수의 능력치를 더합니다.

그리고 다음 포지션에 맞는 사람을 찾아가면 됩니다.