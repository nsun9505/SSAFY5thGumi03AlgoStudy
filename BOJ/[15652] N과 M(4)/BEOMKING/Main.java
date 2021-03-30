import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ttt {
    static int N;
    static int M;
    static int permu[];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        permu = new int[M];
        permutation(1, 0);
        System.out.print(sb.toString().trim());
    }
    static void permutation(int start, int count){
        if(count == M){
            for (int i = 0; i < M; i++) {
                sb.append(permu[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            permu[count] = i;
            permutation(i, count + 1);
        }
    }
}
