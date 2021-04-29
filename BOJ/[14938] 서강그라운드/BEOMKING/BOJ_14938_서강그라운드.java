package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938_서강그라운드 {
    static int n, m, r, map[][], a, b, l, item[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        item = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 100);
        }

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            map[a][b] = l;
            map[b][a] = l;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if(k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            int now = item[i];
            for (int j = 1; j <= n; j++) {
                if(map[i][j] <= m) now += item[j];
            }
            result = Math.max(result, now);
        }

        System.out.println(result);
    }
}
