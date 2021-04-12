import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int wine[] = new int[n + 3];
        int DP[] = new int[n + 3];
        for (int i = 3; i < n + 3; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 3; i <= n + 2; i++) {
            DP[i] = Math.max(DP[i - 1], Math.max(DP[i - 3] + wine[i - 1] + wine[i], DP[i - 2] + wine[i]));
        }
        System.out.print(DP[n + 2]);
    }
}