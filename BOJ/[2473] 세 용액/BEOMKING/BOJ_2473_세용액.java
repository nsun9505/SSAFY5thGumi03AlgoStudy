package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473_세용액 {
    static int N;
    static long solution[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 용액 수
        solution = new long[N]; // 용액

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solution); // NlogN

        long diff = 5000000000L;
        long startsol = -1, midsol = -1, endsol = -1;

        for (int i = 0; i < N; i++) { // 기준이 되는 용액을 먼저 설정 (start와 end 조건을 먼저 줘서 기준 용액을 변경하면서 start와 end값을 조절한다면 다른 결과가 나온다.)
            int start = 0;
            int end = N - 1;
            while (start < end) {
                if (i == start){ // 서로 다른 용액이어야함
                    start++;
                    continue;
                }else if(i == end){
                    end--;
                    continue;
                }

                long sum = solution[start] + solution[i] + solution[end];

                if (Math.abs(sum) < diff) {
                    startsol = solution[start];
                    midsol = solution[i];
                    endsol = solution[end];
                    diff = Math.abs(sum);
                }

                if(sum > 0) end -= 1;
                else start += 1;
            }
        }
        long result[] = {startsol, midsol, endsol};
        Arrays.sort(result);
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
