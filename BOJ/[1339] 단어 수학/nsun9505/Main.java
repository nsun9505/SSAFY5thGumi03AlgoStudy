import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] inputs = new String[N];
        Element[] positions = new Element[26];
        for(int i=0; i<26; i++)
            positions[i] = new Element(i, 0);
        for(int i=0; i<N; i++) {
            // 뒤집기
            inputs[i] = br.readLine();

            int mul = 1;
            for(int idx=inputs[i].length()-1; idx>=0; idx--, mul *= 10){
                int alphabet = inputs[i].charAt(idx) - 'A';
                positions[alphabet].sum += mul;
            }
        }

        // 자릿수 합이 가장 큰 것을 알기 위해 정렬
        Arrays.sort(positions);

        int num = 9;
        int sum = 0;
        for(int i=0; i<26; i++){
            if(positions[i].sum == 0)
                break;
            sum += positions[i].sum * num--;
        }
        sb.append(sum);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element implements Comparable<Element>{
        int alphabet;
        int sum;

        public Element(int alphabet, int sum) {
            this.alphabet = alphabet;
            this.sum = sum;
        }

        @Override
        public int compareTo(Element o) {
            return o.sum - this.sum;
        }
    }
}