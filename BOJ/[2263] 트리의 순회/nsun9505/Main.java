import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] inorder;
    static int[] postorder;
    static int[] inorderPosition;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        inorder = new int[N];
        postorder = new int[N];
        inorderPosition = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderPosition[inorder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, N);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int inOrderStart, int postOrderStart, int length){
        if(length == 0)
            return;
        if(length == 1){
            sb.append(postorder[postOrderStart] + " ");
            return;
        }

        sb.append(postorder[postOrderStart + length - 1] + " ");
        int rootIdx = inorderPosition[postorder[postOrderStart + length - 1]];
        int leftSubTreeLen = rootIdx - inOrderStart;
        solve(inOrderStart, postOrderStart, leftSubTreeLen);

        int rightSubTreeLen = length - leftSubTreeLen - 1;
        solve(rootIdx+1, postOrderStart + leftSubTreeLen, rightSubTreeLen);
    }
}