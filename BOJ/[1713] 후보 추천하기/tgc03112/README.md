# [1713] 후보 추천하기

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());	//사진틀의 수
		int C = Integer.parseInt(br.readLine()); 	//추천 횟수
		int[] res = new int [N];
		LinkedList<Data> list = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int t = 0; t < C ; t++) {
			int num = Integer.parseInt(st.nextToken());
			boolean flag = false;
			int idx = 0;
			int min = Integer.MAX_VALUE;
			int minum = 0;
			if(list.size()==0) {	//처음 시작 
				list.add(new Data(num,1));
			}
			else {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).num==num) {	//list에 같은 학생 있는 경우
						flag=true;				//flag값 변경
						idx = i;				//idx 기억
					}
				}
				if(flag) {	//같은 추천학생이 있는 경우 
					list.get(idx).chu+=1;
				}
				else{
					if(list.size()==N) {	//사진틀이 다 찬 경우 
						for(int i=0;i<list.size();i++) {
							min = Math.min(min, list.get(i).chu);
						}
						
						for(int i=0;i<list.size();i++) {
							if(min == list.get(i).chu){
								list.remove(i);
								break;
							}
						}
					}
					list.add(new Data(num,1));
				}
			}
			/*//디버깅안돼서확인용
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i).num + " " + list.get(i).chu);
			}
			System.out.println("========");
			*/
		}
		Collections.sort(list, (o1,o2) -> o1.num - o2.num);
		for(int i = 0;i<list.size();i++) {
			sb.append(list.get(i).num+" ");
		}
		System.out.println(sb);
	}
	
	static class Data{
		int num;	//숫자
		int chu;	//추천
		public Data(int num, int chu) {
			this.num = num;
			this.chu = chu;
		}
	}
}
```

## 문제 풀이
list 활용해서 후보들의 사진을 저장했습니다.

class를 활용해서 후보의 번호와 추천횟수를 저장합니다.

맨 처음 list의 size가 0일때는 무조건 한 번 넣어주고 시작했습니다.

list를 검사해서 넣어주려는 후보번호와 같은 후보가 있으면 추천수를 +1하고 넘어갑니다.

사진틀이 다 찼을 경우
   - list 에서 가장 낮은 추천수를 찾습니다.(min)
   - list 뒤에서 부터 넣어줬기 때문에 min값이 여러개라도 앞에 있는 경우가 가장 오래된 경우이기 때문에 그 후보를 제거해주고 for문을 종료합니다.

list에 새로운 후보를 추가해줍니다.

list의 후보(num)으로 정렬해서 출력해줍니다.