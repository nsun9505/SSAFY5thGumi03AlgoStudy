아래를 복사해서 자신의 폴더에 README.md 파일을 만들어서 내용에 붙여넣기 하고 작성하시면 됩니다.

# [해시] 위장

## 분류
> 자료구조
>
> 해시

## 코드
package 위장;

import java.util.HashMap;
import java.util.Map.Entry;

public class Solution {
	public static void main(String[] args) {
//		int result = solution(new String[][] {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}});
		int result = solution(new String[][] {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}});
//		int result = solution(new String[][] {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_glasses", "eyewear"}, {"green_turban", "headgear"}, {"make_up", "face"}, {"blue_turban", "headgear"}});
		
		System.out.println(result);
	}
	
	static HashMap<String, Integer> map;
	
	public static int solution(String[][] clothes) {
        int answer = 0;
        
        map = new HashMap<>();
        
        for(int i = 0; i < clothes.length; i++) {
        	
        	if (map.containsKey(clothes[i][1])) { // 같은 종류가 있다면
        		map.put(clothes[i][1], map.get(clothes[i][1])+1); // 해당 종류의 아이템 갯수 늘려주기
        	} else { // 처음 저장하는 종류라면
        		map.put(clothes[i][1], 1); // { 종류, 갯수  } 형태로 저장
        	}
        }
        
        int result = 1;
        
        for(Entry<String, Integer> entry : map.entrySet()) {
        	result *= (entry.getValue() + 1);
        }
        
        answer = result-1;
        
        return answer;
    }
}


## 문제 풀이
- 2번의 시간초과를 겪었습니다.. 처음엔 어떻게 해야하나 고민이 됐는데, 일단 힌트는 똑같은 종류(headgear)에 여러 아이템(hat, turban)이 올 수 있다는 것입니다. 그렇다면 종류를 key값으로 갖고, 아이템을 value값으로 갖는 HashMap을 이용하는것이 좋다고 생각했습니다.
- 문제는 value에 여러 아이템을 저장하고 싶었는데, 덮어씌워지길래 HashMap(String, ArrayList<String>)로 잡아서 list에 넣었습니다. 이 상태 그대로 부분집합 재귀를 돌리려고 했는데 너무 복잡해서 ArrayList의 size만 저장해놓는 배열을 따로 만들었습니다.
- 그러나.... 시간초과.... 그러다가 어짜피 부분집합 결과를 출력하는게 아니라 총 갯수만 구하는거니까 굳이 아이템 이름을 저장할 필요가 없으니, map에서 ArrayList<String>을 그냥 Integer형으로 바꿔서 갯수만 저장하고, 이 상태로 부분집합 재귀를 돌렸습니다.
- 시 간 초 과 ,,,, 그래서 살짝 구글링해본 결과 부분집합 재귀를 돌리는게 시간초과 원인이라는 것을 알아차리고, 바로 계산식으로 바꿨습니다.
- 3가지 종류가 있다면, 각 종류는 선택되지 않을 1가지 경우를 함께 포함하여 총 (갯수+1)의 가짓수를 가집니다. 그러면 각 가짓수를 전부 곱한 후, 모두 선택되지 않을 1가지 경우를 빼주면 간단하게.. 구현 가능.. !