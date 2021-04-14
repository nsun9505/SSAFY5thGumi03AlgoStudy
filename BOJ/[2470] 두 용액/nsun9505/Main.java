import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int ans[] = new int[2];
    static int base = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        ans[0] = arr[0];
        ans[1] = arr[N-1];
        base = ans[0] + ans[1];

        for(int i=0; i<N; i++){
            int left = i+1;
            int right = N-1;

            while(left <= right){
                int mid = (left + right) / 2;

                int sum = arr[i] + arr[mid];
                if(Math.abs(sum) < Math.abs(base)){
                    ans[0] = arr[i];
                    ans[1] = arr[mid];
                    base = sum;
                }

                if(sum >= 0)
                    right = mid - 1;
                else
                    left = mid+1;
            }
        }

        Arrays.sort(ans);
        sb.append(ans[0] + " " + ans[1]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}