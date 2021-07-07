class Solution {
    private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
	int[] answer = new int[enroll.length];
		
		HashMap<String, String> hashmap = new HashMap<>();	//자식, 부모 저장하는 hash
		HashMap<String, Integer> idxhashmap = new HashMap<>();	//idx저장 -> 배열에 바로 price 누적해주기 위해
		
		for(int i=0;i<enroll.length;i++) {
			hashmap.put(enroll[i], referral[i]);
			idxhashmap.put(enroll[i], i);
		}
		
		for(int i=0;i<seller.length;i++) {
			String str = seller[i];
			int price = amount[i] * 100;
			
			while(!str.equals("-")) {	//center에 도달할 때 까지 계속 반복
				int resprice = price - (price / 10);
				
				answer[idxhashmap.get(str)] += resprice;
				
				//부모 이름으로 바꾸고 가격도 1/10으로 바꿔준 뒤 계속 반복
				str = hashmap.get(str);
				price /= 10;
                
                if(price<1){	//price 1 미만되면 계산해줄 필요 x -> 마지막 테케 3개 시간초과 남
                    break;
                }
			}
		}
        return answer;
	}
}