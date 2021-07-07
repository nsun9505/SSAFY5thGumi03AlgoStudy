package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {
    static int N, result;
    static long number[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        number = new long[N];
        st = new StringTokenizer(br.readLine());
        result = 0;

        for (int i = 0; i < N; i++) {
            number[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(number);

        for (int i = 0; i < N; i++) {
            first(i);
        }
        System.out.print(result);
    }
    static void first(int now){
        for (int i = 0; i < N; i++) {
            if(i == now) continue;
            if(!second(now, i)) return;
        }
    }
    static boolean second(int now, int j){
        for (int i = j + 1; i < N; i++) {
            if(now == i) continue;
            long temp = number[j] + number[i];
            if(temp == number[now]){
                result++;
                return false;
            }
            if(temp > number[now]) return true;
        }
        return true;
    }
}
