import java.util.*;

class Main {
    public int solution(String[][] clothes) {
        int answer = 1;
        Set<String> set = new HashSet<>(); // 중복 요소 제거를 위한 set 자료구조

        for (int i = 0; i < clothes.length; i++) {
            set.add(clothes[i][1]); // 옷의 종류 중복 제거
        }

        int count[] = new int[set.size()]; // 각 옷의 개수 저장

        ArrayList list = new ArrayList(); // 인덱스 값 비교를 위한 메모리 낭비
        list.addAll(set);

        for (int i = 0; i < clothes.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if(clothes[i][1].equals(list.get(j))){
                    count[j] += 1;
                    break;
                }
            }
        }

        for (int z = 0; z < count.length; z++) { // 서로 다른 부분집합을 고르는 모든 경우의 수 공식
            answer *= (count[z] + 1); // 각 부분집합 원소 개수를 곱해준다 (+ 1은 공집합을 포함하기 때문)
        }

        return answer - 1; // 옷을 안 입는 경우는 없기 때문에 - 1
    }
}