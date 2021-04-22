# [투포인트/해시] 보석 쇼핑

## 분류

> 투포인트
>
> 해시

## 코드

```java
import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        // 어떤 종류의 보석이 있는지 알기 위해 hashmap 사용
        int length = gems.length; // 입력받는 보석배열의 길이
	    for(int i = 0; i<length; i++){
	        		 map.put(gems[i],0);	           
	        }
        
        int start = 0; // 시작점
	    int end = 0; // 끝점
	    int size = map.size(); // 보석 종류의 수
	    int cnt = 0; //보석종류가 하나씩 있는지 확인 변수
	    int res = Integer.MAX_VALUE; // 가장 짧은 구간 확인 변수
        int[] answer = new int[2]; // 정답배열(시작점, 끝점)
        
        while(true) { // 투포인트

	        if(cnt >= size) { // 보석종류가 최소한 하나 이상일 경우
	        		int value = map.get(gems[start]); // 시작점의 보석 수
	        		if(value == 1) cnt--; // 보석 수가 하나 밖에 없으면 cnt-1
	        		map.replace(gems[start++], value-1); // 해당 보석 뺐으니 -1
                
	        		int l = end - start ; // 구간 길이 구하기
	        		if(res > l) { // 현재까지 가장 짧은 구간보다 더 짧다면
	        			res = l; // 가장 짧은 구간 갱신
	        			answer[0]= start; // 가장 짧은 구간이 된다면 최초이므로(시작 진열대 번호가 가장 작은 번호임) 갱신
	        			answer[1] = end; // 시작 진열대 번호가 가장 작은 번호인 끝점 갱신
	        		}
	        		
	        	}else if(end == length) break; // 끝점이 보석 배열의 인덱스를 벗어난다면 반복문 종료
	        	else { // 보석종류가 최소한 하나 이상이 안되는 경우
	        		int value = map.get(gems[end]); // 끝점의 보석 수
	        		if(value == 0) cnt++; // 보석 수가 0이라면 보석을 포함시키는 것이므로 +1
	        		map.replace(gems[end++], value+1 ); // 해당 보석 포함이니 +1
	        		
	        	}
	        }
        
        return answer; // 정답 출력
    }
}
```

## 문제 풀이

- 입력되는 진열대의 보석들을 적어도 1개이상 포함하는 가장 짧은 구간을 찾아 시작 진열대 번호 와 끝 진열대 번호를 출력하는 문제입니다.
- HashMap
  - 입력되는 진열대의 보석의 종류를 모르고 `중복이 되어 입력`을 받을 수 있기 때문에 이를 확인 하기 위하여 사용`(=Key)`
  - 해당 보석를 `몇 개를 구매했는지 카운트` 해주기 위해 사용`(=value)`
- 투포인트
  - 두개의 포인트의 디폴트는  0
  - int형 변수(cnt)를 사용하여 해당 보석(haspmap의 key값)을 구매했는지(hashmap의 value)를 확인하여 하나라도 구매했다면 cnt+1를 해주었습니다.
    - cnt == gems.length (cnt가 map.size() ,`보석 종류의 수` 와 같다면) 모든 보석을 최소한 하나씩 구매했음을 의미
  - cnt를 이용해 보석 종류의 수와 비교
    - cnt >= size (보석 종류의 수보다 크거나 같다면)
      - 보석의 수를 줄여나가야 하므로 start 포인트를 +1해주어 구간을 줄여줍니다.
      - 또한 gems[start] (해당 start포인트의 보석) 를 뺐을 때 0이 되면 해당 보석이 하나이상 구매한 것이 아니므로 cnt를 -1해줍니다.
      - 해당 보석을 뺐으므로 value 값도 갱신해줍니다.
      - end 포인트 와 start 포인트를 빼서 구간의 길이를 구해 가장 짧은 구간을 구해줍니다.
      - 해당 구간이 가장 짧다면 해당 구간의 start포인트와 end포인트로 결과를 갱신해줍니다.
    - cnt < size (보석 종류의 수보다 작다면)
      - 보석의 수를 늘려야 하므로 end 포인트를 +1해주어 구간을 늘려줍니다.
      - 또한 해당 보석을 구매 했을 때 처음 산 것(value == 0)이라면 cnt를 +1 해줍니다.
  - 문제에서는 진열장의 첨자를 1부터 시작했기 때문에 먼저 더하고 빼준 뒤에 결과를 갱신해줘도 상관없습니다.