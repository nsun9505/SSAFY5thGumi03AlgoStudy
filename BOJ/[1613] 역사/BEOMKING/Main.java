import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k, s, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사건의 개수
        k = Integer.parseInt(st.nextToken()); // 사건의 전후 관계의 개수
        map = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            map[before][after] = -1;
            map[after][before] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= n; l++) {
                    if(map[j][l] != 0) continue; // 이미 대소 관계를 안다면
                    if(map[j][i] == 0) continue; // 중간 수와의 관계를 알 수 없다면
                    if(map[j][i] == map[i][l]) { // 시작 값과 중간 수와의 관계가 중간 수와 끝 값 사이의 관계와 같다면
                        map[j][l] = map[j][i];
                    }
                }
            }
        }

        s = Integer.parseInt(br.readLine()); // 알고 싶은 사건 수
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            sb.append(map[before][after] + "\n");
        }
        System.out.println(sb.toString());
    }
}