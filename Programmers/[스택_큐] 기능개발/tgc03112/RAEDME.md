# [스택/큐] 기능 개발 

## 분류
> 자료구조
>
> 스택/큐

## 코드
```java
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
```

## 문제 풀이
배포까지 얼마나 걸리는지 각각 계산한 후 다시 배열 맨 앞에서 부터 검사 해줬습니다.

먼저 n = Math.ceil(100-progresses[i])/speeds[i]; 를 통해 배포까지 얼마나 걸리는지 계산 해줬습니다.

예제에서 진도 30일떄 속도 30이면 3일간 작업 후 배포가 가능합니다. 계산하면 2.33333일이 나오는데 math.ceil을 사용해서 무조건 올림해줬습니다.

배포까지 걸리는 시간을 계산한 뒤 새로운 배열을 만들지 않고 바로 progresses[]에 저장해주었습니다.

progresses[0]을 max로 잡고 progresses[1]부터 끝까지 검사해줬습니다.
   - 만약 현재 max 값이 진행중인 배열보다 크다면 cnt++을 한 뒤 넘어갑니다. 앞의 작업이 끝나지 않아 배포가 불가능하기 때문에 작업을 누적해놓습니다.
   - (max , n , n) , (max , n , n , n) 이런식으로 그룹을 만든다고 생각하면 됩니다.
   - 배열이 진행되다가 새로운 max 값, 즉 배포가 가능한 시점이 오면 그때 queue에 배포가능한 작업들 (cnt)을 넣고 다시 cnt = 1로 세팅해주어 계속해서 작업들을 누적하게 합니다.

마지막에  q.add(cnt) 를 해서 마지막 배포가능한 작업의 수를 넣어줍니다.

queue에 있는 작업들을 answer배열에 옮기고 리턴해줍니다.