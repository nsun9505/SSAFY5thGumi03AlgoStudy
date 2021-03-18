import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] map;
    static int[][] memoization;
    static int[] tong;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            int ans = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            memoization = new int[N][N];
            tong = new int[M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<N; i++){
                for(int j=0; j+M-1<N; j++){
                    memoization[i][j] = getHoney(i, j);
                }
            }

            ans = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j+M-1<N; j++){
                    int ret1 = memoization[i][j];
                    int ret2 = getOtherHoney(i, j+M);
                    ans = Math.max(ans, ret1 + ret2);
                }
            }

            sb.append("#" + t + " " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getOtherHoney(int row, int col){
        int max = 0;
        for(int i=row; i<N; i++){
            for(int j = (i == row ? col : 0); j+M-1 < N; j++){
                max = Math.max(max, memoization[i][j]);
            }
        }
        return max;
    }

    public static int getHoney(int row, int col){
        int idx = 0;
        for(int k=col; k<col+M; k++)
            tong[idx++] = map[row][k];

        int result = 0;
        for(int i=1; i<(1<<M); i++){
            int tmp = 0;
            int sum = 0;
            for(int j=0; j<M; j++){
                if((i & (1 << j)) > 0){
                    sum += tong[j];
                    tmp += tong[j] * tong[j];
                }
            }
            if(sum <= C)
                result = Math.max(tmp, result);
        }

        return result;
    }
}