import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, nl[], ml[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        nl = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nl[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nl);

        M = Integer.parseInt(br.readLine());
        ml = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            ml[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            if(find(ml[i])){
                sb.append(1 + "\n");
            }else{
                sb.append(0 + "\n");
            }
        }
        System.out.print(sb.toString());
    }
    static boolean find(int x){
        int start = 0, end = N - 1, mid;
        while (start != end){
            mid = (start + end) / 2;
            if(nl[mid] == x || nl[end] == x) return true;
            if(x > nl[mid]){
                start = mid + 1;
                continue;
            }
            if(x < nl[mid]){
                end = mid;
                continue;
            }
        }
        return false;
    }
}