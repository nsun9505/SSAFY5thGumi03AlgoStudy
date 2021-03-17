# [1713] 후보 추천하기

## 분류
> 구현
>
> 시뮬레이션

## 코드
package bkj_1713; // 210317

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main { // 백준 1713번 : 후보 추천하기
	
	static class Student  implements Comparable<Student>{
		int stdNum;
		int order;
		int recmmd;
		
		public Student(int stdNum, int order, int recmmd) {
			super();
			this.stdNum = stdNum;
			this.order = order;
			this.recmmd = recmmd;
		}

		@Override
		public int compareTo(Student o) {
			return this.stdNum - o.stdNum;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 사진틀의 갯수
		int num = Integer.parseInt(br.readLine()); // 추천받은 갯수
		ArrayList<Student> frame = new ArrayList<>(); // 사진틀에 학생의 정보를 가지는 클래스를 담음
		
		int order = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < num; i++) {
			int stdNum = Integer.parseInt(st.nextToken());
			boolean wasRecmmd = false;
			
			// 이미 사진틀에 존재한다면 추천수만 늘려주기
			for(Student s : frame) {
				if (s.stdNum == stdNum) {
					s.recmmd++;
					wasRecmmd = true;
					break;
				}
			}
			
			if (!wasRecmmd) { // 사진틀에 없는 학생이라면
				if (frame.size() == N) { // 사진틀에 자리가 없다면
					
					Collections.sort(frame, new Comparator<Student>() {

						@Override
						public int compare(Student o1, Student o2) {
							return (o1.recmmd == o2.recmmd)? o1.order - o2.order : o1.recmmd - o2.recmmd;
						}
					});
					
					frame.remove(0);
					frame.add(new Student(stdNum, order++, 1));
				} else { // 사진틀에 자리가 있다면
					frame.add(new Student(stdNum, order++, 1));
				}
			}
		}
		
		Collections.sort(frame);
		
		for(int i = 0; i < frame.size(); i++) {
			if (i == frame.size() - 1) {
				sb.append(frame.get(i).stdNum + "");
			} else {
				sb.append(frame.get(i).stdNum + " ");
			}
		}
		
		System.out.println(sb.toString());
	}
}


## 문제 풀이
- 추천수가 같으면 제일 오래된 후보부터 제거한다는 조건을 보고 queue를 사용하려고 했지만, 원인을 알 수 없는 오류로,, 포기했씁니다..
- 학생들에게 관리해야 하는 정보가 많아서 (학생 번호, 순서, 추천수) class로 따로 관리합니다.
- class로 관리하기 때문에 정렬이 더욱 중요한데, 문제의 조건에서도 보이듯 사진틀에 후보들이 모두 채워져있으면, "추천수가 적은 순서부터 제거"하고 추천수가 같은 후보가 2명 이상이면 "오래된 순서부터 먼저 제거"한다는 조건이 있기 때문에 추천수와 사진틀에 걸린 순서를 기준으로 정렬해야 합니다.
- 사진틀에 이미 존재하는 사진이라면, 그냥 그 학생의 추천수만 증가해주면 됩니다.
- 제거해야 하는 사진을 골랐다면, 제일 앞에 있는 사진을 제거해주면 됩니다.