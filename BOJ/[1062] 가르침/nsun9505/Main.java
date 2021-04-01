import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] strings;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        strings = new int[N];

        // 기본적으로 anta, tica가 붙음.
        int letter = 0;
        for(char ch : new char[]{'a', 'n', 't', 'i', 'c'})
            letter |= (1 << (ch - 'a'));

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int idx=0; idx<tmp.length; idx++)
                strings[i] |= (1 << (tmp[idx] - 'a'));
        }

        // 남극 단어는 a, n, t, i, c가 있어야 앞부분과 뒷부분의 글자를 읽을 수 있음.
        solution(5, 0, letter);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int cntOfLetter, int index, int letters){
        if(cntOfLetter > K)
            return;
        if(cntOfLetter == K){
            int cnt = 0;
            for(int i=0; i<N; i++){
                // AND 연산을 해서 strings[i]가 그대로 나온다는 것은
                // letters에 strings[i]에 대한 비트가 모두 켜져있음.
                if((strings[i] & letters) == strings[i])
                    cnt++;
            }
            answer = answer< cnt ? cnt : answer;
            return;
        }

        // 입력으로 주어진 단어에 포함된 글자만 배울 필요는 없음.
        for(int i=index; i<26; i++){
            // 아직 배우지 못한 글자라면
            if((letters & (1 << i)) == 0)
                solution(cntOfLetter+1, i+1, (letters | (1 << i)));
        }
    }
}