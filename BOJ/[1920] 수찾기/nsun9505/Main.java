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

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<M; i++){
            int findNumber = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(arr, findNumber)+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = (left + right) / 2;

            if(arr[mid] == target)
                return 1;

            if(arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return 0;
    }
}
