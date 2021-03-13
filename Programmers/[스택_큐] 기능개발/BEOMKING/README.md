# [스택/큐] 기능 개발

## 분류

> 자료구조
>
> 스택/큐

## 코드

```
import java.util.*;

class Main {
    static public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        int value = 0; // 처리한 작업의 수
        ArrayList<Integer> answer = new ArrayList<>(); // 정답

        while(value != progresses.length) { // 처리한 작업의 수가 총 작업의 수와 같아질 때까지 반복
            int day = 1; // 작업 소요일 수
            while (progresses[value] + day * speeds[value] < 100) { // 가장 앞의 기능의 진도율이 100 이상이 될 때까지
                day += 1;
            }
            
            for (int i = value; i < progresses.length; i++) { // 모든 작업에 소요일 * 작업량
                progresses[i] += day * speeds[i];
            }

            int count = 0;
            for (int i = value; i < progresses.length; i++) { // 현재 처리해야할 작업부터 진행
                if(progresses[i] < 100){ // 진도율이 100 미만이 나오면 중지
                    break;
                }
                count += 1;
            }
            answer.add(count);
            value += count;
        }
        return answer;
    }
}

```

## 문제 풀이

처리해야 할 작업의 진도율을 100으로 만들고 다음 작업들을 체크해서 진도율 100 이상인 것들의 개수를 세서 정답 배열에 저장해주면 되는 문제

정답 배열의 개수가 얼마가 될지 모르니 리스트 사용

value 변수로 처리한 작업의 수를 저장, 다음 처리해야할 작업의 순서를 알게 해줍니다.

배열에서 현재 처리해야하는 작업의 진도율을 100으로 만들 작업 소요일 수(day)를 구하고

모든 작업에 소요일 * 각 작업 속도를 곱해줍니다.

진도율이 100 미만인 작업이 나올때까지 개수를 늘리다 작업이 나온다면 정답 배열에 저장하고 처리한 작업만큼 value를 늘려줍니다.