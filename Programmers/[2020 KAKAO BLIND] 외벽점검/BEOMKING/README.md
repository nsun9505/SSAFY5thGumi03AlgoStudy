# [2020 KAKAO BLIND] 외벽점검

## 분류
> 완전탐색
>
>구현

## 코드
```java
import java.util.Arrays;

class Solution {
    static final int INF = 987654321;
    int N, min;
    int Weak[], Dist[];
    
    void solve(int cnt, int pos, int visited){ // 사용한 친구수, 친구의 시작점, 방문여부(비트)
        if(cnt > Dist.length) return;
        if(cnt >= min) return;

        for (int i = 0; i < Weak.length; i++) { // 나머지 취약지점을 방문할 수 있는지
            int nextPos = (pos + i) % Weak.length; 
            int diff = Weak[nextPos] - Weak[pos]; // 취약지점 간의 거리

            if (nextPos < pos) // ex 10번 출발 1번 도착  13 - 10
                diff += N;

            if (diff > Dist[Dist.length - cnt]) break; //거리가 가장 이동거리가 큰 친구보다 크면
            visited |= 1 << nextPos; 
        }

        if(visited == (1 << Weak.length) - 1){
            min = cnt;
            return;
        }

        for (int i = 0; i < Weak.length; i++) {
            if ((visited & (1 << i)) != 0) continue;
            solve(cnt + 1, i, visited);
        }
    }

    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        N = n;
        Weak = weak;
        Dist = dist;
        min = INF;

        for (int i = 0; i < Weak.length; i++) { // 취약 지점을 변경해가며 완전 탐색
            solve(1, i, 0); 
        }

        if(min == INF) return -1;
        return min;
    }
}
```

## 문제 풀이

완전 탐색 문제입니다.

취약지점에서 친구를 투입하는 것이 가장 효과적이기 때문에 각 취약지점에서 시작하는 모든 경우의 수를 탐색합니다.

현재 친구의 수를 인자로 보내서 현재 친구의 이동거리에 다른 취약지점과 현재 시작지점과의 거리를 계산해서 차이가 작다면 방문체크를 해줍니다.

다른 취약지점과의 거리를 계산할 때 인덱스가 더 작은 취약지점이 올 수 있으므로 그런 경우 외벽의 길이를 더해 원형이 아닌 일자처럼 보이게 해줍니다.

방문 체크는 비트 연산을 이용했습니다. 모든 취약지점에 방문체크가 되어있지않다면 되어 있지 않은 취약지점을 시작점으로, 다음 친구를 인자로 넣어 재귀시킵니다.



