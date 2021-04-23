import java.util.Arrays;
import java.util.HashMap;


class Solution {
    public int[] solution(String[] gems) {
        				HashMap<String, Integer> h = new HashMap<String, Integer>();
		int[] answer = new int[2];
		
		answer[0] = 1;
		answer[1] = gems.length;
		
		int idx = 0;
		for(int i=0;i<gems.length;i++) {
			if(!h.containsKey(gems[i])) {
				h.put(gems[i], idx++);
			}
		}
		
		int size = h.size();
		int min = Integer.MAX_VALUE;
		int[] arr = new int[h.size()];
		int cnt = 0;
		int start = 0;	//0
		int end = 0;	//3
				
		while(true) {
			if(cnt >= size) {
				arr[h.get(gems[start])]--;
				if(arr[h.get(gems[start])]==0) {
					cnt--;
				}
				
				if(answer[1]-answer[0] > (end-1)-start) {
					answer[0] = start+1;
					answer[1] = end;
				}
				start++;
				
			}
			else if(end==gems.length) break;
			else {
				arr[h.get(gems[end])]++;
				if(arr[h.get(gems[end])]==1) {
					cnt++;
				}
				end++;
			}
//			System.out.println(start+","+end);
		}
        return answer;
    }
}