import java.util.HashMap;

public class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String, Integer> clothMap = new HashMap<>();
        for(int i=0; i<clothes.length; i++){
            if(clothMap.containsKey(clothes[i][1]))
                clothMap.put(clothes[i][1], clothMap.get(clothes[i][1]) + 1);
            else
                clothMap.put(clothes[i][1], 1);
        }

        if(clothMap.size() == 1)
            return clothMap.get(clothes[0][1]);

        int[][] arr = new int[clothMap.size()][clothMap.size()];
        int idx=0;
        int sum = 0;
        for (String key : clothMap.keySet()) {
            arr[0][idx] = clothMap.get(key);
            sum += arr[0][idx++];
        }

        for(int i=1; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                for(int k=j+1; k<arr.length; k++){
                    arr[i][j] += arr[0][j] * arr[i-1][k];
                }
                sum += arr[i][j];
            }
        }
        return sum;
    }
}