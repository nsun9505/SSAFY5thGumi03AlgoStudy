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
