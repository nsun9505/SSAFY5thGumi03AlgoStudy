# [최단경로 알고리즘] 합승 택시 요금 

## 분류
> 최단경로 알고리즘
>
> 다익스트라
>
> 플로이드-와샬

## 코드
```java
import java.util.Arrays;

public class Solution {
    static int INF = 100000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] dist = new int[n+1][n+1];

        // INF로 초기화
        for(int i=1; i<=n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // dist 세팅
        for(int i=0; i<fares.length; i++){
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];

            if(f < dist[c][d]){
                dist[c][d] = f;
                dist[d][c] = f;
            }
        }

        // 플로이드 와샬로 최단경로 구하기
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1;j<=n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 최소 요금 구하기
        answer = dist[s][a] + dist[s][b];
        for(int i=1; i<=n; i++){
            int total = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(answer, total);
        }

        return answer;
    }
}
```

## 문제 풀이
N이 최대 200까지이므로 플로이드 와샬로 깔끔하게 풀 수 있습니다!

입력은 fares로 dist를 세팅합니다.

이 문제에서 답이 될 수 있는 경우는 어떤 지점 i까지 합승하고 각자 집으로 갈 수 있는 경우입니다.
   - i는 s도 될 수 있고, a, b도 될 수 있습니다.

그러면 s -> ... -> i에 대한 최소 경로를 구해야 하므로 플로이드 또는 다익스트라로 구하면 됩니다.

그리고 s에서 i까지 합승을 했을 때 i에서 a로 가는 요금, b로 가는 요금을 더하면 i까지 합승하고 i에서는 따로 가는 것에 대한 합승 요금이 나오게 됩니다.
   - i -> ... -> a, i -> ... -> b 이렇게 i 에서 b로 가는 최단거리도 필요하므로 저는 플로이드 와샬을 사용했습니다.

플로이드 와샬로 최단 거리를 다 구한 뒤에

dist[s][i] + dist[i][a] + dist[i][b]가 최소인 것을 찾으면 됩니다.