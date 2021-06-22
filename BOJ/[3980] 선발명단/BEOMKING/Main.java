import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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