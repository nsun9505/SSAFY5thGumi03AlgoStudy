import java.util.HashMap;

public class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashMap<String, Integer> map = new HashMap<>();
        int index = 0;
        int[] arr = new int[gems.length];
        for(int i=0; i<gems.length; i++){
            if(!map.containsKey(gems[i]))
                map.put(gems[i], index++);
            arr[i] = map.get(gems[i]);
        }

        int start = 0;
        int end = 0;
        int numOfType = 1;
        int[] counts = new int[map.size()];
        counts[arr[0]] = 1;
        int N = arr.length;
        int total = map.size();
        int dist = Integer.MAX_VALUE;

        while(end < N){
            if(numOfType == total){
                int d = (end+1) - (start+1) - 1;
                if(d < dist) {
                    dist = d;
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                }
                counts[arr[start]]--;
                if(counts[arr[start]] == 0)
                    numOfType--;
                start++;
            }

            else{
                if(++end < N){
                    counts[arr[end]]++;
                    if(counts[arr[end]] == 1)
                        numOfType++;
                }
            }
        }

        return answer;
    }
}
