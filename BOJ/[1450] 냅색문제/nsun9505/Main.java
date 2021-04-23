import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, C, half;
    static long sub1[], sub2[];
    static ArrayList<Long> group1 = new ArrayList<>();
    static ArrayList<Long> group2 = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        half = N/2;
        sub1 = new long[half];
        sub2 = new long[N-half];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N/2; i++)
            sub1[i] = Integer.parseInt(st.nextToken());
        for(int i=0;i<N-half; i++)
            sub2[i] = Integer.parseInt(st.nextToken());

        calcSubSet(sub1, group1);
        calcSubSet(sub2, group2);
        Collections.sort(group2);

        int answer = 0;
        for(long num : group1){
            answer += (upperBound(group2, C - num));
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void calcSubSet(long[] arr, ArrayList<Long> group){
        for(int i=0; i<(1<<arr.length); i++){
            long sum = 0;
            for(int j=0; j<arr.length; j++){
                if((i & (1 << j)) > 0){
                    sum += arr[j];
                }
            }

            if(sum <= C)
                group.add(sum);
        }
    }

    public static int upperBound(ArrayList<Long> list, long target){
        int left = 0;
        int right = list.size();

        while(left < right){
            int mid = (left + right) / 2;

            if(list.get(mid) > target)
                right = mid;
            else
                left = mid + 1;
        }

        return right;
    }
}