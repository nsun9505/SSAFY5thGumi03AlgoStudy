import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static boolean map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int col = Integer.parseInt(st.nextToken());
            for (int j = 0; j < col; j++) {
                map[j][i] = true;
            }
        }

        for (int i = 0; i < H; i++) {
            boolean before = false;
            int count = 0;
            for (int j = 0; j < W; j++) {
                if(before && map[i][j] == false){
                    count++;
                }else if(before && map[i][j]){
                    result += count;
                    count = 0;
                }else if(before == false && map[i][j]){
                    before = true;
                }
            }
        }

        System.out.println(result);
    }
}