import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, sequence[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sequence = new int[n + 1];
        makeparent();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int calculate = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(calculate == 0){
                union(a, b);
            }else{
                if(update(a) == update(b)){
                    sb.append("YES" + "\n");
                }else{
                    sb.append("NO" + "\n");
                }
            }
        }

        System.out.print(sb.toString());
    }
    static void makeparent(){
        for (int i = 1; i <= n; i++) {
            sequence[i] = i;
        }
    }
    static void union(int a, int b){
        int A = update(a);
        int B = update(b);
        sequence[B] = A;
    }
    static int update(int idx) {
        if (sequence[idx] == idx) {
            return idx;
        }
        return sequence[idx] = update(sequence[idx]);
    }
}