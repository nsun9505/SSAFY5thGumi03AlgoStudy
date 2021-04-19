import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sequence[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        sequence = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            sequence[n] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 1;
        int sum = sequence[start] + sequence[end];
        int answer = Integer.MAX_VALUE;

        while(end < N){
            if(sum >= S){
                answer = Math.min(answer, end - start + 1);
                sum -= sequence[start++];
            }else{
                sum += sequence[++end];
            }
        }
        if(answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);
    }
}