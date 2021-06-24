import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int N = 11;
    static int[][] ability = new int[N][N];
    static boolean[] used  = new boolean[N];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int t=0; t<T; t++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    ability[i][j] = Integer.parseInt(st.nextToken());
            }
            answer = 0;
            solve(0, 0);
            sb.append(answer +"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int posIdx, int score){
        if(posIdx >= N){
            answer = Math.max(answer, score);
            return;
        }

        for(int i=0; i<N ; i++){
            if(used[i] || ability[i][posIdx] == 0)
                continue;

            used[i] = true;
            solve(posIdx + 1, score + ability[i][posIdx]);
            used[i] = false;
        }
    }
}