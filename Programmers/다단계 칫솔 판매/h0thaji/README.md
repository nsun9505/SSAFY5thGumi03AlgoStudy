# 다단계 칫솔 판매

## 분류

## 코드
```java
import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int eSize = enroll.length;
        int sSize = seller.length;
        int[] answer = new int[eSize];
        
        HashMap<String,String> map = new HashMap<>(eSize); 
        HashMap<String,Integer> index = new HashMap<>(eSize);
        
        for(int i = 0; i < eSize; i++) map.put(enroll[i], referral[i]);
        for(int i = 0; i < eSize; i++) index.put(enroll[i],i);
              
        for(int i = 0; i < sSize; i++){
            String s = seller[i];
            int profit = amount[i] * 100;
            
            int next = profit/10;
            int idx = index.get(s);
            answer[idx] += profit - next;
            
            s = map.get(s);
            
            while(!s.equals("-")){
                profit = next;
                next = profit/10;
                
                idx = index.get(s);
                answer[idx] += profit - next;
                
                if(next < 1) break;
                s = map.get(s);
            }                        
        }
        return answer;
    }
}
```

## 문제풀이

- HashMap
  - 판매원과 추천인 매칭을 위해 key,value값을 <String, String> 선언했습니다 => HashMap map
  - 또한 판매원의 index을 알기위해 <String,Integer>로 선언했습니다 => HashMap index
- 판매량을 아는 판매원 만큼 반복
  - 해당 판매원의 추천인들을 타고 올라가면서 이익의 10%씩을 더해주었습니다.
  - map을 통해 추천인을 찾고 index을 이용해 해당 추천인의 index을 찾아 값을 저장해주었습니다.
  - 추천인이 "-" 라면 민호이므로 추천인 찾는 반복문을 종료 해주었습니다
    - 이익의 10%가 0 이라면 더이상 반복해줄 필요가 없으므로 시간을 단축시켰습니다
    - if(next < 1) break; => 안하니까 시간초과가 남 ㅠ