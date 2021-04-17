import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, lan[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가진 랜선 갯수
        M = Integer.parseInt(st.nextToken()); // 필요 갯수
        lan = new int[N];

        for (int i = 0; i < N; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lan);

        long start = 1;
        long end = lan[N - 1];
        long height = 0;
        long mid;

        while(start <= end){
            mid = (start + end) / 2;
            int nowlan = 0;
            for (int i = 0; i < N; i++) {
                nowlan += lan[i] / mid;
            }

            if(M <= nowlan){
                start = mid + 1;
                height = Math.max(height, mid);
            }else{
                end = mid - 1;
            }
        }
        System.out.print(height);
    }
}