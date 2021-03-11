아래를 복사해서 자신의 폴더에 README.md 파일을 만들어서 내용에 붙여넣기 하고 작성하시면 됩니다.

# [스택/큐] 기능 개발 

## 분류
> 자료구조
>
> 스택/큐

## 코드
package 기능개발; // 210311
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) {
		int[] result = solution(new int[] {93,30,55}, new int[] {1,30,5});
		
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		
		List<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();

		for(int i = 0; i < progresses.length; i++) {
			int rest = 100 - progresses[i];
			int day = (rest % speeds[i]) == 0? rest / speeds[i] : (rest / speeds[i]) + 1;

			if (queue.isEmpty() || queue.peek() >= day) { // stack이 비어있거나 top값보다 내가 작거나 같으면
				queue.offer(day);
			} else {
				int count = 0;

				while(!queue.isEmpty()) {
					queue.poll();
					count++;
				}

				list.add(count);
				
				queue.offer(day);
			}
		}
		
		if (!queue.isEmpty()) {
			int count = 0;
			while(!queue.isEmpty()) {
				queue.poll();
				count++;
			}
			list.add(count);
		}
		
		answer = list.stream().mapToInt(i->i).toArray();

		return answer;
	}
}

## 문제 풀이
- 빨리 끝나야하는 기능들보다 뒤의 기능들의 개발이 더 빨리 끝나도 기다려야 한다는 사실에서 큐를 사용해야함을 알 수 있음.
- 제일 먼저 남은 날들을 계산해서, 큐에 넣거나 빼는 작업을 해야 하는데 조건은 간단.
   1. 앞에서부터 순서대로 확인하면서 큐가 비어있으면 offer
   2. 큐에 제일 먼저 넣은 값과, 현재 값을 비교하면서 현재 값이 더 작거나 같다면 배포까지 기다려야하므로 다시 offer
   3. 큐에 제일 먼저 넣은 값보다 큰 값을 만나면 큐에 남은 것들을 전부 poll
- 크게 어렵지 않은 문제라 주의해야할 점만 조심하면 금방 구현 가능함.
   1. 남은 날들을 계산할때, 나누어 떨어지지 않으면 +1 해줘야 함.
   2. 큐에 offer할지 poll할지 나누는 조건에서, 현재 값이 큐에 제일 먼저 넣은 값보다 작거나 "같다"는 것.
   3. 큐에 남은 것을 전부 poll하고 다시 현재 값을 poll해줄 것.