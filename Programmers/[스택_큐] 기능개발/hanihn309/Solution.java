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