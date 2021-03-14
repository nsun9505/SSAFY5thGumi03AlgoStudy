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
