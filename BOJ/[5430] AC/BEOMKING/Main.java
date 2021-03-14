package 구현;

import java.io.*;
import java.util.*;

public class 백준_5430_AC {
    static char function[];
    static Deque<String> array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb; // string bulider 사용으로 출력 시간 감소
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            sb = new StringBuilder();
            String func =br.readLine();
            function = func.toCharArray(); // 함수 배열
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            if(n == 0){ // 빈 배열
                if(func.contains("D")) {
                    System.out.println("error");
                }else{
                    System.out.println("[]");
                }
                continue;
            }
            str = str.substring(1, str.length() - 1); // 파싱
            array = new LinkedList<>();
            st = new StringTokenizer(str, ",");
            for (int i = 0; i < n; i++) {
                array.add(st.nextToken()); // 숫자로 변환 필요 x 메모리 감소
            }

            int R = 0; // 뒤집힌 횟수
            boolean error = false; // 에러 확인
            for (char f: function) {
                if(f == 'R'){
                    R += 1;
                }else{
                    if(array.size() == 0) {
                        System.out.println("error");
                        error = true;
                        break;
                    }
                    if(R % 2 == 0){ // 정방향
                        array.pollFirst();
                    }else{ // 역방향
                        array.pollLast();
                    }
                }
            }

            if(!error) {
                sb.append("[");
                int len = array.size();
                if(R % 2 == 0) {
                    for (int i = 0; i < len; i++) {
                        if (i == len - 1) {
                            sb.append(array.pollFirst());
                        } else {
                            sb.append(array.pollFirst());
                            sb.append(",");
                        }
                    }
                }else{
                    for (int i = len; i > 0; i--) {
                        if (i == 1) {
                            sb.append(array.pollLast());
                        } else {
                            sb.append(array.pollLast());
                            sb.append(",");
                        }
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}
