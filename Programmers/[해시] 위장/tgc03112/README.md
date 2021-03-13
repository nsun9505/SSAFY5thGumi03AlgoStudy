# [해시] 위장

## 분류
> 자료구조
>
> 해시

## 코드
```java
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> h = new HashMap();
        
        for(int i=0;i<clothes.length;i++) {
        	if(h.containsKey(clothes[i][1])) {	//이미 있는 key라면
        		h.put(clothes[i][1],h.get(clothes[i][1]) + 1);	//1개 더해서 덮어써줌
        	}
        	else {
        		h.put(clothes[i][1], 1); //없는 key면 추가
        	}
        }

        for(String k : h.keySet()) {
        	answer *= (h.get(k)+1);
        }
        return answer-1;	//아무것도 안입는 공집합 빼줌
    }
}
```

## 문제풀이
처음에는 부분집합 문제로 모두 구했더니 시간초과가 나왔습니다. 찾아보니 모든 조합을 구해주기 보다 그냥 경우의 수만 구하면 되기 때문에 옷을 입는경우/안입는 경우를 모두 곱해주고 마지막에 옷을 안 입는 경우 -1을 빼주면 되는 문제였습니다.

해시맵에 대해서 공부하고 사용해보는? 문제였던거 같습니다.

중간에 if문 대신 getOrDefault(Object key, V DefaultValue)를 사용해도 되는거 같습니다.(찾는 키가 존재하면 찾는 키의 값을 반환해주고 없다면 기본값을 반환하는 메서드라고합니다.. 직관적으로 보이지않아서 그냥 if-else문 사용했습니다!!)