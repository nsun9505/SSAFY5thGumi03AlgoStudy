import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        int sum = arr[0];
        int answer = Integer.MAX_VALUE;

        while(end < N){
            if(sum >= S){
                int len = end - start + 1;
                answer = Math.min(answer, len);
                sum -= arr[start++];
            }

            else{
                if(++end < N)
                    sum += arr[end];
            }
        }

        int result = answer;
        if(result == Integer.MAX_VALUE)
            result = 0;
        sb.append(result);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}