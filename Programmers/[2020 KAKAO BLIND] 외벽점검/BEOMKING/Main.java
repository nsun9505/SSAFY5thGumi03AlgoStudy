import java.io.IOException;
import java.util.Arrays;

class Solution {
    static final int INF = 987654321;
    int N, min;
    int Weak[], Dist[];
    void solve(int cnt, int pos, int visited){
        if(cnt > Dist.length) return;
        if(cnt >= min) return;

        for (int i = 0; i < Weak.length; i++) {
            int nextPos = (pos + i) % Weak.length;
            int diff = Weak[nextPos] - Weak[pos];

            if (nextPos < pos)
                diff += N;

            if (diff > Dist[Dist.length - cnt]) break;
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

        for (int i = 0; i < Weak.length; i++) {
            solve(1, i, 0);
        }

        if(min == INF) return -1;
        return min;
    }
}