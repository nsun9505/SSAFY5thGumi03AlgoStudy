import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] preorder;
    static int[] inorder;
    static boolean[] visited;
    static int index = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            preorder = new int[N];
            inorder = new int[N];
            visited = new boolean[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                preorder[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                inorder[i] = Integer.parseInt(st.nextToken());


            index = 0;
            solve(0, N-1);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int start, int end){
        if(index >= N)
            return;

        int node = preorder[index++];
        visited[node] = true;

        if(start >= end){
            sb.append(node + " ");
            return;
        }

        int left = start;
        int mid = end;
        int right = end;
        for(int i=start; i<=end; i++){
            if(inorder[i] == node){
                mid = i;
                break;
            }
        }

        if(left <= mid-1)
            solve(left, mid-1);
        if(mid+1 <= right)
            solve(mid+1, right);
        sb.append(node + " ");
    }
}