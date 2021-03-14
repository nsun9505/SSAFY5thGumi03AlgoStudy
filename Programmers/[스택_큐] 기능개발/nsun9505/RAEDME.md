# [스택/큐] 기능 개발 

## 분류
> 자료구조
>
> 스택/큐

## 코드
```java
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
```

## 문제 풀이
각 progresss[i]별로 언제 끝나는지를 queue에 담아서 해결했습니다.

그러면 첫번째 기능이 완료되는 날짜가 있을 것이고, 두 번쨰 기능이 완료되는 날짜가 있을 것입니다.

두 번쨰 기능이 완료되는 날짜가 첫 번째 기능이 완료되는 날짜보다 작거나 같으면 첫 번쨰 기능이 완료되는 날짜에 배포가 가능합니다.

조금 바꿔 생각해보면 첫 번째 기능이 완료되는 날짜에 배포되는 그룹에 속한다라고 할 수 있습니다.

예를 들어, 각각 완료되는 날짜가 [3, 2, 1, 4]라고 하면, 첫 번째 기능이 3일후에 완료가 되니, 2일, 1일이 지나서 완료되는 기능은 3일째에 모두 배포가 가능하므로 

3일에 배포가 가능한 기능의 수는 3개이므로 days[3] = 3입니다.

4일째에 완료되는 마지막 기능은 앞서 3일쨰에 모든 기능이 배포되었으므로 days[4] = 1로 되고, 답은 {2, 1}이 됩니다.

queue의 peek(front) 값이 현재 날짜보다 작거나 같으면 현재 날짜에 배포가 가능한 것이므로 queue에서 바로 pop시켜주고 현재 날짜에 카운트를 하나 증가시킵니다.

현재 날짜보다 크다면 현재 날짜를 queue의 peek(front) 값으로 바꾸고 다시 queue를 탐색하면 됩니다.