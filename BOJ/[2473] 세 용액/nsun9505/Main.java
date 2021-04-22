import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] arr;
    static long[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        long answer = Long.MAX_VALUE;
        ans = new long[3];

        for(int i=0; i<N; i++){
            int left = i + 1;
            int right = N - 1;

            while(left < right){
                long sum = arr[i] + arr[left] + arr[right];
                if(Math.abs(sum) <= answer){
                    answer = Math.abs(sum);
                    ans[0] = arr[i];
                    ans[1] = arr[left];
                    ans[2] = arr[right];
                }

                if(sum > 0)
                    right--;
                else
                    left++;
            }
        }

        sb.append(ans[0] + " " + ans[1] + " " + ans[2]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}