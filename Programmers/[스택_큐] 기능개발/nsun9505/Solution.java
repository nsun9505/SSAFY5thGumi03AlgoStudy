import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> queue = new LinkedList<>();
        int[] days = new int[100];

        // 언제 끝나는지 저장
        for(int i=0; i<progresses.length; i++) {
            int remain = 100 - progresses[i];
            int day = remain / speeds[i];
            if(remain % speeds[i] != 0)
                day++;
            queue.offer(day);
        }

        int curDay = queue.peek();
        int size = 1;
        while(!queue.isEmpty()){
            // 끝나는 날짜가 언제인지
            int completeDay = queue.peek();
            // 앞에 있는 기능이 완료된 날짜보다 작거나 같으면 배포 가능
            if(completeDay <= curDay){
                queue.poll();
                days[curDay]++;
            } 
            // 앞에 있는 기능이 완료된 날짜보다 더 걸린다면
            // 현재 날짜를 바꾼다.
            else {
                size++;
                curDay = completeDay;
            }
        }

        answer = new int[size];
        int idx = 0;
        for(int i=1; i<100; i++){
            if(days[i] > 0)
                answer[idx++] = days[i];
        }

        return answer;
    }
}
