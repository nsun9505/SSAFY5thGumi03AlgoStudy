import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] moneys = new int[N];
        for(int i=0; i<N; i++)
            moneys[i] = Integer.parseInt(br.readLine());

        int answer = 0;
        for(int i=N-1; i>=0; i--){
            if(moneys[i] > K)
                continue;

            answer += (K / moneys[i]);
            K %= moneys[i];
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}