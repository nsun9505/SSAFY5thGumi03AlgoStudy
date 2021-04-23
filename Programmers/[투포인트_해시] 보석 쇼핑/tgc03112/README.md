# [2020 카카오 인턴십] 보석 쇼핑

## 분류
> 투포인트
>
> 해시

## 코드
```java
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
//		System.out.println(h.toString());	//출력확인
		
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
//				System.out.println("2 :"+Arrays.toString(arr)+" "+cnt);
				start++;
				
			}
			else if(end==gems.length) break;
			else {
				arr[h.get(gems[end])]++;
				if(arr[h.get(gems[end])]==1) {
					cnt++;
				}
				end++;
//				System.out.println("1 :"+Arrays.toString(arr)+" "+cnt);
			}
//			System.out.println(start+","+end);
		}
        return answer;
    }
}
```

## 문제 풀이
투포인터를 사용하엿 문제를 풀었습니다.

슬라이딩 윈도우? 처럼 앞 뒤로 밀어주며 개수를 비교했습니다.

HashMap을 이용하여 보석의 종류가 몇개인지 구해줍니다. 그리고 최대 구간을 처음에 1~보석배열의 길이로 지정합니다.
제일 긴 길이를 설정하고 그보다 짧은 길이가 나오면 갱신해주는 방식으로 했습니다. 

if 만약 뽑은 종류가 해시의 사이즈와 같거나 크다면 조건을 만족하는 경우입니다. 이때 arr배열에서 시작 위치에 있는 배열을 -- 했을때 
0이 된다면 해당 배열의 보석은 담지 못하는것이므로 보석 종류 cnt도 -- 해줍니다. 이때 이미 조건 >=size를 만족해서 들어왔기 때문에 길이를 계산해주고 짧다면 갱신해줍니다.

else if 끝점 end 가 보석배열만큼의 길이가 되면 종료합니다.

else 아직 보석의 종류를 다 못세었기 때문에 끝점을 늘려주며 보석 종류만큼 arr배열이 모두 1이상이 될때까지 반복해줍니다. 만약 ++을 했을 때 1이 된다면 처음 새롭게 담긴 보석의 종류이기 때문에 cnt의 개수를 ++해서 증가시켜 줍니다.
