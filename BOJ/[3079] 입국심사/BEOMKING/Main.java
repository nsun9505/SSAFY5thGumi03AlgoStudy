import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 각 심사대 수
        int M = Integer.parseInt(st.nextToken()); // 친구 수
        int jt[] = new int[N];
        for (int i = 0; i < N; i++) {
            jt[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jt);

        int jtmax = jt[N - 1]; // 심사대 중 최장 시간 (왜 int가 안될까??)
        long max = M * jtmax; // 최장 시간과 친구 수를 곱하면 최대 걸리는 시간
        long result = M * jtmax;
        long min = 0;
        long mid;

        while(min <= max){
            mid = (max + min) / 2;
            long sum = 0; // 최대 친구 수
            for (int i = 0; i < N; i++) {
                sum += mid / jt[i];
            }
            if(sum >= M) {
                result = Math.min(mid, result);
                max = mid - 1;
            }
            if(sum < M) {
                min = mid + 1;
            }
        }
        System.out.print(result);
    }
}