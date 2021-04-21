# [투포인트/해시] 보석 쇼핑

## 분류

> 투포인트
>
> 해시

## 코드

```
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {0, 0};

        HashSet<String> hashSet = new HashSet<>();
        for (String str: gems) { // 보석 종류
            hashSet.add(str);
        }

        int start = 0, end = 0, min = Integer.MAX_VALUE;
        String kind;

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(gems[0], 1); // 초기값 삽입

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
```

## 문제 풀이

투 포인터와 해시를 이용한 문제입니다.

보석의 종류 수를 알아야 ```진열된 모든 종류의 보석을 적어도 1개 이상 포함``` 조건을 찾을 수 있기 때문에 HashSet을 사용했습니다.

- 시작점이 배열의 끝 값이 될 때까지 반복 조건을 줍니다.

- 현재 범위에 모든 보석이 다 있고(hashMap.size() == hashSet.size()를 한다면 보석의 유무를 알 수 있음) 범위가 최소 길이보다 짧다면 갱신

  길이가 같을 경우는 그냥 넘어가면 됩니다. start와 end가 증가하면서 확인하고 있기 때문에 길이가 같다면 무조건 예전 답이 시작값이 더 작습니다.

  시작 보석 개수가 1개 이하라면 현재 보석 종류를 제거하고 아니라면 1개를 줄여줍니다.

- 보석이 다 있지 않다면 end를 증가시켜 다음 보석을 이미 가지고 있다면 값만 증가, 없다면 새로 삽입합니다. 





