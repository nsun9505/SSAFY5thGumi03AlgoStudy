import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        double[] tmp = new double[progresses.length];
        int cnt = 1;

        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=0;i<progresses.length;i++) {
            double n = Math.ceil(100-progresses[i])/speeds[i];
            progresses[i] = (int) Math.ceil(n);
        }
        int max = progresses[0];

        for(int i=1;i<progresses.length;i++) {
            if(max>=progresses[i]) {
                cnt++;
            }
            else {
                q.add(cnt);
                cnt=1;
                max=progresses[i];
            }
        }
        q.add(cnt);
        answer = new int[q.size()];
        for(int i=0;i<answer.length;i++) {
            answer[i]=q.poll();
        }
        return answer;
    }
}