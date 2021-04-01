import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] strings;
    static int answer = 0;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        strings = new int[N];
        HashSet<Integer> letterSet = new HashSet<>();

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            int num = 0;
            for(int idx=0; idx<tmp.length; idx++){
                num |= (1 << (tmp[idx] - 'a'));
                if(letterSet.contains((1 << (tmp[idx] - 'a'))))
                    continue;
                letterSet.add((1 << (tmp[idx] - 'a')));
            }
            strings[i] = num;
        }
        list.addAll(letterSet);

        solution(0, 0, 0);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int cntOfLetter, int index, int letters){
        if(cntOfLetter > K)
            return;
        if(cntOfLetter == K || index >= list.size()){
            int cnt = 0;
            for(int i=0; i<N; i++){
                // 지금까지 배운 글자로 입력 받은 단어를 읽을 수 있는지 검사
                if((strings[i] & letters) == strings[i])
                    cnt++;
            }
            answer = answer< cnt ? cnt : answer;
            return;
        }

        // 단어 배우기
        for(int i=index; i<list.size(); i++){
            solution(cntOfLetter+1, i+1, (letters | list.get(i)));
        }
    }
}