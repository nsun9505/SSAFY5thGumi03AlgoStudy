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