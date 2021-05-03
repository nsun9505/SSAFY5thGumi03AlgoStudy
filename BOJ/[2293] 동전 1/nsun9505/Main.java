import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        int[] money = new int [M+1];
        for(int i=1; i<=N; i++)
            coin[i-1] = Integer.parseInt(br.readLine());

        money[0] = 1;
        for(int i=0; i<N; i++){
            for(int j=1; j<=M; j++){
                if(coin[i] <= j)
                    money[j] += money[j-coin[i]];
            }
        }

        sb.append(money[M]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}