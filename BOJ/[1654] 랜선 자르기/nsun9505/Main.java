import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        long N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        for(int i=0; i<K; i++)
            arr[i] = Integer.parseInt(br.readLine());

        long left = 1;
        long right = Integer.MAX_VALUE;
        long answer = 0;
        while(left <= right){
            long mid = (left + right) / 2;

            long cnt = 0;
            for(int i=0; i<K; i++)
                cnt += arr[i]/mid;

            if(cnt >= N){
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        sb.append(answer);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
