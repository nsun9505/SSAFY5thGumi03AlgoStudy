import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 최대 하중

        Queue<Integer> N = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            N.add(Integer.parseInt(st.nextToken()));
        }

        int time = 0; // 경과 시간
        int nowweight = 0; // 현재 다리 위의 트럭 무게
        ArrayList<int[]> onLoad = new ArrayList<>(); // 다리에 있는 트럭 무게와 진행거리
        while(!N.isEmpty() || !onLoad.isEmpty()){ // 대기 중인 트럭과 다리 위의 트럭 모두 없어야 종료
            if(!onLoad.isEmpty()) {
                for (int i = 0; i < onLoad.size(); i++) { // 다리 위의 모든 트럭 진행 거리 증가
                    onLoad.get(i)[1] += 1;
                }

                if (onLoad.get(0)[1] > w) { // 다리를 지난 트럭이 있다면
                    nowweight -= onLoad.get(0)[0]; // 무게 제거
                    onLoad.remove(0);
                }
            }
            time += 1;

            if(!N.isEmpty() && nowweight + N.peek() <= L){ // 다리 위 무게에 대기중인 첫 트럭의 무게를 합해도 하중 무게를 넘지 않는다면
                int weight = N.poll();
                nowweight += weight;
                onLoad.add(new int[]{weight, 1});
            }
        }
        System.out.println(time);
    }
}