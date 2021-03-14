package baekjoon;

import java.util.Scanner;

public class Main{

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();	//테케

		for(int t = 0; t<tc;t++) {
			String str = sc.next();
			int start = 0;
			int end = str.length()-1;
			int cnt =0;
			while(start<=end) {
				if(str.charAt(start) == str.charAt(end)) {	//같으면 인덱스 줄이고 계속 진행
					start++;
					end--;
				}
				else {
					int copyS=start;
					int copyE=end;

					copyS++;	//왼쪽에서 진행시켜보기

					while(copyS<=copyE) {
						if(str.charAt(copyS)==str.charAt(copyE)) {
							copyS++;
							copyE--;
						}
						else {	//유사회문 or 문자열 가능성 
							cnt++;
							break;
						}
					}

					copyS=start;
					copyE=end;

					copyE--;	//오른쪽에서 진행시켜보기 
					while(copyS<=copyE){
						if(str.charAt(copyS)==str.charAt(copyE)) {
							copyS++;
							copyE--;
						}
						else {
							cnt++;	//문자열
							break;	
						}
					}
					break;
				}
			}
			//cnt = 0:회문, 1:유사회문 2:문자열
			System.out.println(cnt);
		}
	}
}
