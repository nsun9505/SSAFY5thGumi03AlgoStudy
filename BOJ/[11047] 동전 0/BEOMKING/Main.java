import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int Coin[] = new int[N];
        for (int i = 0; i < N; i++) {
            Coin[i] = Integer.parseInt(br.readLine());
        }
        int answer = 0;
        int i = N - 1;
        while(K != 0){
            if(K >= Coin[i]){
                answer += K / Coin[i];
                K %= Coin[i];
            }
            i--;
        }
        System.out.print(answer);
    }
}