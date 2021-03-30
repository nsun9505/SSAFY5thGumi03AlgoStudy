import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] arr;
    static final int LOTTO_MAX_SIZE = 6;
    static int[] lotto = new int[LOTTO_MAX_SIZE];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            // K가 0이라면 종료
            if(K == 0)
                break;

            arr = new int[K];
            for (int i = 0; i < K; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            // 6개 로또 번호 뽑기
            solution(0, 0);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int index, int start){
        // 6개의 로또 번호를 뽑았다면
        if(index == LOTTO_MAX_SIZE){
            // 출력
            for(int lottoNumber : lotto)
                sb.append(lottoNumber + " ");
            sb.append("\n");
            return;
        }

        // start : arr에서 시작할 번호
        // index : 6개 중에서 몇 번째인지
        // index+1 : 하나를 뽑았으므로 다음에 뽑을 위치
        // i+1 : arr의 i번째는 사용했으므로 다음 위치부터 사용하도록 함.
        for(int i=start; i<arr.length; i++){
            lotto[index] = arr[i];
            solution(index+1, i+1);
        }
    }
}
