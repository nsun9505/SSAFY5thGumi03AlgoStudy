# 다단계 칫솔 판매

## 분류

## 코드
```java
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
```

## 문제풀이

hashmap 을 사용해서 풀었습니다.

판매원과 그 추천인을 저장하는 hash, 판매원의 index 를 저장해주는 hash를 만들어줍니다. 판매원의 index를 알아서 배열에 바로 이익을 누적해주기 위해서 입니다.

seller 배열만큼 반복해주며 이익 - 이익의 1/10을 누적해주고 center, 최상위 부모를 만날 때 까지 이 과정을 반복해줍니다.

근데 반복하는 while문 중간에 price가 누적해도 의미없는 값인 0이하가 되면 break를 해줘야 합니다. 이 break가 없으면 마지막 테케 3개가 시간초과가 납니다.

