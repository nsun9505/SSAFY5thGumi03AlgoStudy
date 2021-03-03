# [5430] AC - JAVA

## 분류
> 구현

## 코드
package bkj_5430; // 210302

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main { // 백준 5430번 : AC

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String p = br.readLine(); // 수행할 함수  ex) RDD
			
			int n = Integer.parseInt(br.readLine()); // 배열의 갯수
			String input = br.readLine(); // 배열의 수
			
			int count = 0;
			for(int i = 0; i < p.length(); i++) {
				if (p.charAt(i) == 'D') {
					count++;
				}
			}
			
			if (count > n) { // 수행할 함수에서의 삭제 명령 갯수가 현재 배열의 숫자보다 많으면 error
				sb.append("error\n");
				continue;
			}
			
			String[] formattedInput = input.replace("[", "").replace("]", "").split(",");
			List<String> list = new LinkedList<>(); // 삭제를 쉽게하기 위해 list 사용
			list.addAll(Arrays.asList(formattedInput)); // 대괄호와 콤마를 삭제한 배열을 list에 옮김
			
			boolean isReverse = false; // true : 역방향, false : 정방향
			
			for(int i = 0; i < p.length(); i++) { // 함수를 하나씩 확인하면서 방향 전환 및 삭제 처리
				if (p.charAt(i) == 'R') { // 뒤집기면 isReverse 변수 바꿔주기
					isReverse = !isReverse;
				} else if (p.charAt(i) == 'D') { // 삭제 명령이라면 방향 확인 후 처리
					if (isReverse) { // 역방향이면
						list.remove(list.size()-1); // 맨 뒤부터 삭제
					} else { // 정방향이면
						list.remove(0); // 앞 부터 삭제
					}
				}
			}
			
			if (isReverse) { // 역방향이면 뒤집어줌
				Collections.reverse(list);
			}
			
			StringBuilder tmp = new StringBuilder();
			tmp.append("[");
			for(String s : list) {
				tmp.append(s + ",");
			}
			tmp.append("]");
			
			sb.append(tmp.toString().replace(",]", "]") + "\n");
		}
		System.out.println(sb.toString());
	}
}


## 문제 풀이

처음엔 배열에 숫자를 넣어서 하려고 했는데 삭제가 까다로워서 포기했습니다... 그래서 삭제는 안하고 인덱스만 계속 변화시켜 주면서 삭제한 값을 -1로 바꿔주도록 했는데 배열 범위 초과 오류가 계속 나길래 그냥 배열은 포기하고 list로 새로 짰습니다.
아마 문제의 포인트는 뒤집기를 실제로 매번 해주는게 아니라, 인덱스만 앞 뒤로 바꿔가면서 삭제하는 일을 해주는 것 같습니다. 그리고 replace 메소드는 평소에 사용을 잘 안했는데 결과 출력할때 쓰니까 아주 편하더라구요! Collections.reverse() 메소드도.. 알고리즘은 얼마나 자바 메소드를 잘 알고있냐가 너무너무너무 중요한 것 같아요