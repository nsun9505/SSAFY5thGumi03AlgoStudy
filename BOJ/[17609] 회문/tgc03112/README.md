# [17609] 회문

## 분류
> 구현

## 코드
```java
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
```

## 문제 풀이
문자열의 인덱스를 start와 end 로 나누어 양쪽 끝에서부터 진행하는 방식으로 풀이 했습니다.

중간에서 한번 달라지면 회문은 무조건 아니고 유사회문 또는 문자열일 가능성이 있는 경우입니다.
   - 1. start 인덱스를 오른쪽으로 한번 움직여서 진행시키는 경우 
   - 2. end 인덱스를 왼쪽으로 한 번 움직여서 진행시키는 경우 

두 가지 모두 해본 뒤 둘 중 하나의 경우에서만 문자가 다른 부분이 있으면 문자 하나를 삭제해서 회문을 만들 수 있는 유사회문이고 두 가지 경우 다 문자 하나씩 삭제해야 되는 경우 문자하나로 삭제해서 회문이 만들어지지 않는 그냥 문자열로 판단했습니다. 이 부분은 cnt 값을 증가시켜 판단했습니다.