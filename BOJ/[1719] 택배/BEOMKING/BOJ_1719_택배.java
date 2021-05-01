package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1719_택배 {
    static final int INF = 10000;
    static int n, m, a, b, c, map[][], result[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        result = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
            map[b][a] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if(k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if(i == j) continue;
                    if(map[i][j] > (map[i][k] + map[k][j])){
                        map[i][j] = map[i][k] + map[k][j];
                        result[i][j] = k;
                        int t = j;
                        while(result[i][t] != 0){
                            result[i][j] = result[i][t];
                            t = result[i][t];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j) System.out.print('-' + " ");
                else if(result[i][j] == 0) System.out.print(j + " ");
                else System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
