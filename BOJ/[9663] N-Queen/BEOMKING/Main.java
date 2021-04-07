import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int map[][];
    static boolean check[][];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        check = new boolean[N][N];
        recursion(0);
        System.out.print(answer);
    }

    static void recursion(int n){
        if(n == N){
            answer += 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if(check[j][i]) {
                    flag = true;
                    break;
                }
                if(i - n + j >= 0) {
                    if (check[j][i - n + j]) {
                        flag = true;
                        break;
                    }
                }
                if(i + n - j < N) {
                    if (check[j][i + n - j]) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) continue;
            check[n][i] = true;
            recursion(n + 1);
            check[n][i] = false;
        }
    }
}