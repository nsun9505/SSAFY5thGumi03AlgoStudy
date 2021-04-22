# [투포인트/해시] 보석 쇼핑

## 분류

> 투포인트
>
> 해시

## 코드

```java
	public static int[] solution(String[] gems) {
		int[] answer = {};
		
		Queue<Integer> queue = new LinkedList<>(); // gems의 보석들을 차례로 담을 queue
		HashSet<String> gemName = new HashSet<>(); // 보석의 종류 개수를 구하기 위해, 보석의 이름을 저장하는 HashSet 만듦
		HashMap<String, Integer> gemCount = new HashMap<>(); // 진열대에 올라와있는 보석을 차례로 담아가며 담은 보석의 개수를 종류별로 저장하기 위한 HashMap 만듦.
		
		int startIndex = 0, start = 0; // startIndex : 보석을 담을 구간의 시작 index		start : gems 배열을 큐에 넣으며 startIndex를 찾기 위한 변수
		int minLength = Integer.MAX_VALUE; // 보석을 담을 구간의 최소 길이
		
		for(String gem : gems) {
			gemName.add(gem);
		}
		
		for(int i = 0; i < gems.length; i++) {
			queue.offer(i);
			
			if (gemCount.containsKey(gems[i])) { // 이미 담은 보석 종류라면
				gemCount.put(gems[i], gemCount.get(gems[i]) + 1); // 개수만 늘려주기
			} else { // 처음 담는 보석 종류라면
				gemCount.put(gems[i], 1); // 개수를 1로 초기화 해주기
			}
			
			while(true) {
				String gem = gems[queue.peek()]; // 제일 처음 넣은 보석 종류의 이름 저장
				
				if(gemCount.get(gem) > 1) { // 처음 넣은 보석이 2개 이상 담겨 있다면
					queue.poll(); // 제일 짧은 구간의 길이를 구하는 것이므로, queue에서 꺼내버리고
					gemCount.put(gem, gemCount.get(gem) - 1); // 개수 줄여줌
					start++; // queue에서 poll했으니 시작 구간은 다음으로 지정
				} else // 제일 처음 넣은 보석이 1개라면, 이 보석을 꺼내면 모든 종류를 다 담을 수 없으므로 break
					break;
				
			}
			
			if(gemCount.size() == gemName.size() && minLength > queue.size()) { // 모든 보석 종류를 다 담았고 && 현재 구간의 길이(큐의 size)가 최소라면
				minLength = queue.size();
				startIndex = start;
			}
		}
		
		answer = new int[] {startIndex + 1, startIndex + minLength};
		
		return answer;
	}
```

## 문제 풀이
- 처음에 문제를 읽고 생각이 든건, HashSet과 HashMap을 사용해야 할 것 같다는 것입니다. 보석의 이름이 문자열로 되어있고, 각 보석을 인덱스로 해야할 것이란 아이디어가 떠올랐기 때문입니다.
- 그 다음 문제에서 모든 종류의 보석을 담되, 연속된 보석을 '최소한의' 구간 길이로 담아야 한다는 조건을 주었습니다. 그렇다면 앞에서부터 순서대로 탐색해나가다가, 뒤에 앞에서 담았던 보석과 똑같은 종류를 담게된다면 맨 앞의 보석은 빼놓으며 구간의 길이를 줄여나갈 수 있다는 뜻이므로 input과 output의 방향이 다른 queue를 사용해야할 것입니다.
- 이 3가지 아이디어만 생각나면, 소스 작성은 크게 어렵지는 않습니다. 먼저 중복 저장을 방지해주는 HashSet을 이용해 현재 진열대 위의 보석의 "종류"의 개수를 저장해둡니다. HashSet은 그저 set의 size가 보석 종류의 개수라는 의미만 가져가게 됩니다. 그리고 queue에 gems의 보석을 하나씩 넣어가며, 담는 보석의 종류와 개수를 HashMap에 저장해줍니다. 보석의 이름이 key가 되고, value값은 그 보석의 개수가 됩니다.
- 이때 보석의 개수는 queue의 peek값, 즉 제일 오래전에 넣은 보석의 개수가 2 이상이면 빼내며 구간의 길이를 줄여주는 역할을 하게 됩니다. 그리고 Map의 size와 Set의 size가 같다면, 모든 종류의 보석이 담겼다는 의미이며, 이때 구간의 최소 길이를 저장해두는 변수와 현재 queue의 size (구간의 길이)를 비교하며 최소 구간일때 구간의 시작점과 구간의 길이를 update 해주면 됩니다.