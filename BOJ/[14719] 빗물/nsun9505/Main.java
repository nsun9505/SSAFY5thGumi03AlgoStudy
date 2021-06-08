import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    public static int[] heights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        heights = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++)
            heights[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        for(int i=0; i<W; i++){
            int left = findLeftMax(i, heights[i]);
            int right = findRightMax(i, heights[i]);

            if(left == -1 || right == -1)
                continue;

            int min = Math.min(heights[left], heights[right]);
            int diff = Math.abs(min - heights[i]);
            answer += diff;
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findLeftMax(int startIndex, int max){
        int findIndex = -1;
        for(int i=startIndex-1; i>=0; i--){
            if(max < heights[i]){
                findIndex = i;
                max = heights[i];
            }
        }
        return findIndex;
    }

    public static int findRightMax(int startIndex, int max){
        int findIndex = -1;
        for(int i=startIndex+1; i<W; i++){
            if(max < heights[i]){
                findIndex = i;
                max = heights[i];
            }
        }
        return findIndex;
    }
}