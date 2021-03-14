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
