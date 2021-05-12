package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661_좋은수열 {
    static int N;
    static String result = "";
    static boolean end = false;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        process("");
        System.out.println(result);
    }
    public static void process(String str){
        if(end) return;

        if(str.length() == N) {
            result = str;
            end = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if(isavailable(str + i)){
                process(str + i);
            }
        }
    }

    public static boolean isavailable(String str){
        for (int i = 1; i <= str.length() / 2; i++) {
            int last = str.length() - i;
            if(str.substring(last, last + i).equals(str.substring(last - i, last))){
                return false;
            }
        }
        return true;
    }
}