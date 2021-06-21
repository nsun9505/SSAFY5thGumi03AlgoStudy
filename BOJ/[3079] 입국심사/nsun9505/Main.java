import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N;
        long M;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        long maxSec = 0;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            maxSec = Math.max(arr[i], maxSec);
        }

        long left = 0;
        long right = maxSec * M;
        long sec = Long.MAX_VALUE;
        while(left <= right){
            long mid = (left + right) / 2;

            long cnt = 0;
            for(int i=0; i<N; i++)
                cnt += (mid / arr[i]);
            if(M <= cnt) {
                right = mid - 1;
                if(mid < sec)
                    sec = mid;
            }
            else
                left = mid + 1;
        }
        sb.append(sec);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}