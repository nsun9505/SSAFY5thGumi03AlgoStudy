package 이분탐색;

import java.util.HashMap;
import java.util.HashSet;

class Pro_카카오인턴_보석쇼핑 {
    public int[] solution(String[] gems) {
        int[] answer = {0, 0};

        HashSet<String> hashSet = new HashSet<>();
        for (String str: gems) { // 보석 종류
            hashSet.add(str);
        }

        int start = 0, end = 0, min = Integer.MAX_VALUE;
        String kind;

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < gems.length; i++) { // 시작점에서 모든 보석을 포함하는 특정 i를 찾는 과정
            kind = gems[i];
            if(hashMap.containsKey(kind)){
                hashMap.put(kind, hashMap.get(kind) + 1);
            }else{
                hashMap.put(kind, 1);
            }
            if(hashMap.size() == hashSet.size()){
                end = i;
                break;
            }
        }

        while (start < gems.length){ // 시작점이 배열의 끝이 될 때까지
            if(hashMap.size() == hashSet.size()){ // 현재 범위에 모든 보석이 다 있다면
                if(end - start + 1 < min){ // 현재 길이가 최소 길이보다 짧다면
                    answer[0] = start + 1; // 정답 갱신
                    answer[1] = end + 1;
                    min = end - start + 1;
                }

                kind = gems[start++]; // 시작 값 저장, 후치 인덱스 증가(현재 start를 제거해야하기때문)
                if(hashMap.get(kind) - 1 == 0){ // 첫 번째 보석의 개수가 1개 이하라면
                    hashMap.remove(kind); // 현재 보석 종류 제거
                }else{ // 1개를 제거해도 남아있다면
                    hashMap.put(kind, hashMap.get(kind) - 1); // 값만 감소
                }
            }else{ // 현재 범위에 보석의 종류 다 있지 않다면
                if(end + 1 >= gems.length){ // 범위 바깥이면 종료 (보석의 종류가 모자라서 범위를 늘리는 것, 그렇기 때문에 범위 바깥까지 간다면 start 값이랑 상관없이 더 이상 의미 없음)
                    break;
                }

                kind = gems[++end]; // 새로 합류하는 보석 종류, 새로운 보석을 추가하기 때문에 전치
                if(hashMap.containsKey(kind)){ // 이미 가지고 있는 보석이라면
                    hashMap.put(kind, hashMap.get(kind) + 1); // 값만 증가
                }else{
                    hashMap.put(kind, 1); // 새로 삽입
                }
            }
        }

        return answer;
    }
}
