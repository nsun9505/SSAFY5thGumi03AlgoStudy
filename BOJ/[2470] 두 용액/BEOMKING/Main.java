import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, solution[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 용액 수
        solution = new int[N]; // 용액

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solution);

        int start = 0;
        int end = N - 1;
        int diff = Integer.MAX_VALUE;
        int startsol = -1, endsol = -1;

        while (start < end){
            if(solution[start] + solution[end] == 0){
                startsol = solution[start];
                endsol = solution[end];
                break;
            }

            if(Math.abs(solution[start] + solution[end]) < diff){
                startsol = solution[start];
                endsol = solution[end];
                diff = Math.abs(solution[start] + solution[end]);
            }

            if(solution[start] + solution[end] > 0){
                end -= 1;
            }else{
                start += 1;
            }
        }
        System.out.print(startsol + " " + endsol);
    }
}