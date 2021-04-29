import java.util.Arrays;

public class Solution {
    static int INF = 100000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for(int i=0; i<fares.length; i++){
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];

            if(f < dist[c][d]){
                dist[c][d] = f;
                dist[d][c] = f;
            }
        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1;j<=n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        answer = dist[s][a] + dist[s][b];
        for(int i=1; i<=n; i++){
            int total = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(answer, total);
        }

        return answer;
    }
}