import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            solve(0, 1, new int[M]);

            bw.write(sb.toString());
            bw.flush();
            bw.close();
            br.close();
        }
    }

    public static void solve(int arrIndex, int start, int[] arr){
        // M개를 담았다면, 출력
        if(arrIndex == M){
            for(int i=0; i<M; i++)
                sb.append(arr[i] + " ");
            sb.append("\n");
            return;
        }

        // arrIndex 번째에 i 넣어주기
        for(int i=start; i<=N; i++){
            arr[arrIndex] = i;
            solve(arrIndex+1, i, arr);
        }
    }
}