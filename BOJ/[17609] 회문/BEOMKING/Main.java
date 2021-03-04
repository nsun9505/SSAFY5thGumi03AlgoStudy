package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_17609_회문 {
    static char str[];
    static int len;
    static StringBuilder sb = new StringBuilder();

    static int ggg(int start, int end, int count){
        while(start <= end){
            if(str[start] == str[end]){
                start += 1;
                end -= 1;
            }else if(str[start] == str[end - 1] && str[start + 1] == str[end]){
                count += 1;
                if(ggg(start + 1, end - 2, count) < 2 || ggg(start + 2, end - 1, count) < 2){
                    return 1;
                }else{
                    return 2;
                }
            }else if(str[start] == str[end - 1]){
                start += 1;
                end -= 2;
                count += 1;
            }else if(str[start + 1] == str[end]){
                start += 2;
                end -= 1;
                count += 1;
            }else{
                count += 2;
            }
            if(count >= 2) return 2;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            str = br.readLine().toCharArray();
            len = str.length;
            int start = 0;
            int end = len - 1;
            int count = 0;
            int result = ggg(start, end, count);
            sb.append(result + "\n");

        }
        System.out.println(sb.toString().trim());
    }
}
