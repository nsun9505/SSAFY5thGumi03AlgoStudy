import java.io.*;

public class Main {
    static int N;
    static boolean[] colCheck;
    static boolean[] leftUpToRightDown;
    static boolean[] leftDownToRightUp;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        colCheck = new boolean[N];
        leftUpToRightDown = new boolean[2*(N-1)+1];
        leftDownToRightUp = new boolean[2*(N-1)+1];

        solution(0);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int row){
        if(row >= N){
            answer++;
            return;
        }

        for(int col=0; col<N; col++){
            if(colCheck[col] || leftUpToRightDown[row-col+N-1] || leftDownToRightUp[row+col])
                continue;

            colCheck[col] = true;
            leftUpToRightDown[row-col+N-1] = true;
            leftDownToRightUp[row+col] = true;
            solution(row+1);
            colCheck[col] = false;
            leftUpToRightDown[row-col+N-1] = false;
            leftDownToRightUp[row+col] = false;
        }
    }
}