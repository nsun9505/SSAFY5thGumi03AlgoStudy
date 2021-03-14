import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String str = br.readLine();

            // 회문인지 아닌지 체크
            int ans =  check(0, str, 0, str.length()-1);
            ans = ans > 2 ? 2 : ans;
            sb.append(ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int check(int cnt, String str, int start, int end){
        if(cnt >= 2)
            return 0;

        for(;start<end; start++, end--){
            if(str.charAt(start) == str.charAt(end))
                continue;
                // start 위치를 지우거나, end 위치를 지우거나
            else if(str.charAt(start+1) == str.charAt(end)
                || str.charAt(start) == str.charAt(end-1)){
                return Math.min(check(cnt+1, str, start+1, end),
                                check(cnt+1, str, start,end-1)) + 1;
            } else {
                return 2;
            }
        }
        return 0;
    }
}