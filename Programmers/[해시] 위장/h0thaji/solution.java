import java.util.;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMapString, Integer map = new HashMap();
        
        for(int i = 0; i  clothes.length;i++){ clothes 길이만큼 반복문
            if(map.get(clothes[i][1]) == null) map.put(clothes[i][1],1);
            clothes[i][1] , 의상 종류의 수가 없다면 1을 추가
            else
                map.put(clothes[i][1], map.get(clothes[i][1])+1);
             있다면 +1을 해줌
        }
        
        for(String k  map.keySet()){ 
            answer = (map.get(k)+1);  의상 종류를 안 고를 경우도 있으니 +1씩 더해주고 옷 선택은 동시에 일어나기에 answer에 곱해줌
        }


        return answer-1;  아무것도 안입는 경우는 없으니 -1
    }
    
}