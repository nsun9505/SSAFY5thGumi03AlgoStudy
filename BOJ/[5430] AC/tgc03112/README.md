# [5430] AC - JAVA

## 분류
> 구현

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st ;
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0;t<tc;t++) {
			StringBuilder sbb = new StringBuilder();
			String str = br.readLine();
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine().replace("[","").replace("]",""),",");

			for(int i=0;i<n;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			System.out.println(Arrays.toString(arr));
			
			int start = 0;	//start랑 end로 배열 출력범위 지정 
			int end = arr.length-1;
			
			boolean flag = false;
			boolean err = false;	//D에서 에러 발생한 경우 
			
			for(int i=0;i<str.length();i++) {
				if(str.charAt(i)=='R') {
					if(flag) {
						flag=false;	//원래상태
					}
					else flag=true;	//뒤집힌상태
				}
				else {
					if(start>end) {	// 모두 삭제됨 
						err=true;
						break;
					}
					if(flag) {	//뒤집힌상태면 뒤에 end 부분 잘라냄
						end--;
					}
					else start++;	//원래 상태면 앞 start부분 잘라냄
				}
			}
			if(err) {	//D에서 에러 발생한 경우 
				sbb.append("error");
			}
			else {
				sbb.append("[");
				if(flag) {	//뒤집힌상태
					for(int i=end;i>=start;i--) {
						sbb.append(arr[i]);
						if(i==start) break;
						sbb.append(",");
					}
				}
				else {	//원래상태
					for(int i=start;i<=end;i++) {
						sbb.append(arr[i]);
						if(i==end) break;
						sbb.append(",");
					}
				}
				sbb.append("]");
			}
			sb.append(sbb.toString()+"\n");
		}
		System.out.print(sb.toString());
	}
}
```

## 문제 풀이
 처음 시도는 list를 이용해서 앞 뒤 삭제 연산을 진행하고 list를 출력하려고 했으나 시간초과가 계속 떴습니다.
(list시간초과 왜 뜬지 모르겠네요..ㅜ)
그래서 다시 배열로 돌아와 앞 뒤 출력범위를 조절하는 방향으로 진행하였습니다.
 자바 문법을 확실히 숙지하지못해서 
st = new StringTokenizer(br.readLine().replace("[","").replace("]",""),","); 를 작성하는 부분이 은근 헷갈렸습니다..
또한 마지막 출력에서 하나씩 출력하니 시간초과 납니다. StringBuilder로 한번에 출력해야 시간초과 나지않는거 같네요.
2
DD
2
[1,2]
R
1
[100]
초기화 안해서 위의 반례에 걸렸습니다..한 번 확인해보시면 좋을거같습니다.