import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, map[][], a, b, c, Max = 100000, result = 100000, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V + 1][V + 1];

        for (int i = 0; i <= V; i++) {
            Arrays.fill(map[i], Max);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i == j) continue;
                for (int k = 1; k <= V; k++) {
                    if(j == k || i == k) continue;
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                if(map[i][j] >= Max || map[j][i] >= Max) continue;
                temp = map[i][j] + map[j][i];
                result = Math.min(result, temp);
            }
        }
        if(result == 100000) result = -1;
        System.out.println(result);
    }
}