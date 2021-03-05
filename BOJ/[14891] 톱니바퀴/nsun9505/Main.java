import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr = new int[4][8];
    static int[] shiftArr = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<4; i++){
            String input = br.readLine();
            for(int j=0; j<input.length(); j++){
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            Arrays.fill(shiftArr, 0);
            shiftArr[start] = dir;
            left(start, dir);
            right(start, dir);

            for(int j=0; j<4; j++){
                if(shiftArr[j] == 0)
                    continue;
                shift(j, shiftArr[j]);
            }
        }

        int ans = 0;
        if(arr[0][0] == 1) ans += 1;
        if(arr[1][0] == 1) ans += 2;
        if(arr[2][0] == 1) ans += 4;
        if(arr[3][0] == 1) ans += 8;
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void left(int cur, int dir){
        if(cur - 1 < 0)
            return;

        if(arr[cur-1][2] == arr[cur][6])
            return;

        shiftArr[cur-1] = dir == 1 ? -1 : 1;
        left(cur-1, shiftArr[cur-1]);
    }

    public static void right(int cur, int dir){
        if(cur + 1 >= 4)
            return;

        if(arr[cur][2] == arr[cur+1][6])
            return;

        shiftArr[cur+1] = dir == 1 ? -1 : 1;
        right(cur+1, shiftArr[cur+1]);
    }

    public static void shift(int cur, int dir){
        if(dir == -1){
            int tmp = arr[cur][0];
            for(int i=1; i<=7; i++)
                arr[cur][i-1] = arr[cur][i];
            arr[cur][7] = tmp;
        } else {
            int tmp = arr[cur][7];
            for(int i=7; i>=1; i--)
                arr[cur][i] = arr[cur][i-1];
            arr[cur][0] = tmp;
        }
    }
}