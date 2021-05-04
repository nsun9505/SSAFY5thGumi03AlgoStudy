import java.io.*;
import java.util.ArrayList;

public class Main {
    static int N;
    static char[] str;
    static ArrayList<String> list = new ArrayList<>();
    static char[] arr = {'1', '2', '3'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        str = new char[N];

        solution('0');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean solution(char prev){
        // 가장 작은 수(1->2->3)부터 문자를 삽입하므로
        // 만든 문자열의 길이가 N인 경우는 가장 작은 숫자를 의미하므로 바로 끝내기 위해서 true 리턴
        if(sb.length() == N)
            return true;

        for(char ch : arr){
            // 이전에 추가한 문자는 추가하지 않기
            if(ch == prev)
                continue;
            
            // 문자를 추가하고
            sb.append(ch);
            // 임의의 길이의 인접한 두 개의 부분 수열이 동일하면 
            if(!check(sb.toString())) {
                // 빼고 다른 문자를 추가하기
                sb.setLength(sb.length()-1);
                continue;
            }

            // 임의의 길이의 인접한 두 개의 부분 수열이 존재하지 않은 경우
            if(solution(ch))
                return true;    // solution이 true이면 더 이상 진행하지 않고 끝내기

            sb.setLength(sb.length()-1);
        }
        // 만들 수 없는 경우 false 리턴
        return false;
    }

    // 임의의 길이를 잡고 인접한 부분 문자열 비교
    public static boolean check(String str){
        int length = str.length();
        int loop = length / 2;
        int start = length - 1;
        int end = length;

        for(int i=1; i<=loop; i++){
            String left = str.substring(start-i, end-i);
            String right = str.substring(start, end);
            if(left.equals(right))
                return false;
            start--;
        }
        return true;
    }
}