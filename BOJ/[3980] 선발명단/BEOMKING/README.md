# [3980] 선발명단

## 분류
>브루트 포스
>
>백트래킹
>
>그래프 이론

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int player[][], result;
    static boolean isselect[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int C = Integer.parseInt(br.readLine()); // TC
        for (int i = 0; i < C; i++) {
            player = new int[11][11];
            isselect = new boolean[11];
            result = 0;
            for (int j = 0; j < 11; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 11; k++) {
                    player[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            sb.append(result + "\n");
        }
        System.out.print(sb.toString());
    }

    private static void dfs(int pn, int sum) {
        if(pn == 11){
            result = Math.max(result, sum);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if(player[pn][i] == 0 || isselect[i]) continue;
            isselect[i] = true;
            dfs(pn + 1, sum + player[pn][i]);
            isselect[i] = false;
        }
    }
}
```

## 문제풀이

완전 탐색 문제입니다.

완전 탐색 문제인지 생각하지 못했었는데 선수들이 가질 수 있는 적합 포지션 개수가 최대 5개이므로

5의 11제곱을 해도 50,000,000을 넘지 않습니다. 이미 선택한 포지션은 방문 처리를 해준다면 8행부터는 탐색해야하는 포지션이 1개씩 감소하므로 실제로는 더 적습니다. ( 아마? )

그렇기 때문에 DFS를 이용해 능력치가 0이거나 이미 선택된 포지션이라면 넘어가면 됩니다.