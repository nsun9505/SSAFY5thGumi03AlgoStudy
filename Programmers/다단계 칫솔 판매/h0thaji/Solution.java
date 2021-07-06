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