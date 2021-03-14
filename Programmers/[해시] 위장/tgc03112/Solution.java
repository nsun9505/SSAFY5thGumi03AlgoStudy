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