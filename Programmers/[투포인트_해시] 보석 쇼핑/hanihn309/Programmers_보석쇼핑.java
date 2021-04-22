import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_보석쇼핑 {

	public static void main(String[] args) {
//		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}; // [3, 7]
//		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA", "RUBY"}; // [6, 9]
//		String[] gems = {"AA", "AB", "AC", "AA", "AC"}; // [1, 3]
//		String[] gems = {"XYZ", "XYZ", "XYZ"}; // [1, 1]
		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}; // [1, 5]

		int[] result = solution(gems);
		
		System.out.println("[" + result[0] + ", " + result[1] + "]");
	}

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
}