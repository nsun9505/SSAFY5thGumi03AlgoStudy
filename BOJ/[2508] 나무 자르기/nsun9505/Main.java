import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 2000000000;
        int answer = 0;
        while(left <= right){
            int mid = (left + right) / 2;

            long remain = 0;
            for(int i=0; i<N; i++) {
                if(arr[i] - mid > 0)
                    remain += (arr[i] - mid);
            }

            if(remain < M) {
                right = mid - 1;
            }
            else{
                left = mid + 1;
                answer = Math.max(mid, answer);
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}