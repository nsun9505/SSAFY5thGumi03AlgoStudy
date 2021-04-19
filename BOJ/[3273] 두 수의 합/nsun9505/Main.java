import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(br.readLine());

        int start = 0;
        int end = N-1;
        Arrays.sort(arr);

        int answer = 0;
        while(start < end){
            int sum = arr[start] + arr[end];
            if(sum == X){
                start++;
                end--;
                answer++;
            }

            else if(sum < X){
                start++;
            }
            else{
               end--;
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
