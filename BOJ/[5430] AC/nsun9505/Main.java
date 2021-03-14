import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t<T; t++){
            String cmd = br.readLine();

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine().replaceAll("\\[", "").replaceAll("]", ""), ",");
            String[] list = new String[N];

            for(int i=0; i<N; i++)
                list[i] = st.nextToken();

            boolean isError = false;
            boolean isReverse = false;
            int start = 0;
            int end = list.length-1;

            for(int i=0; i<cmd.length(); i++){
                if(cmd.charAt(i) == 'R')
                    isReverse = isReverse == false ? true : false;
                else{
                    if(start > end){
                        isError = true;
                        break;
                    }

                    if(isReverse) end--;
                    else start++;
                }
            }

            StringBuilder ret = new StringBuilder("error");
            if(!isError){
                ret.setLength(0);
                ret.append("[");
                if(isReverse) {
                    for (int i = end; i >= start; i--)
                        ret.append(list[i] + (i == start ? "" : ","));
                } else {
                    for (int i = start; i <= end; i++)
                        ret.append(list[i] + (i == end ? "" : ","));
                }
                ret.append("]");
            }
            sb.append(ret.toString() + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}