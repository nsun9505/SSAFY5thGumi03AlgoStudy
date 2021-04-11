import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 남은 근무일 수
        int CT[] = new int[N + 1];
        int Value[] = new int[N + 1];
        int DP[] = new int[N + 1];
        
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            CT[n] = Integer.parseInt(st.nextToken());
            Value[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) { // 상담 일자
            int end = i + CT[i] - 1;
            if (end <= N) {
                DP[end] = Math.max(DP[end], DP[i - 1] + Value[i]);
            }
            DP[i] = Math.max(DP[i], DP[i - 1]);
        }
        System.out.print(DP[N]);
    }
}