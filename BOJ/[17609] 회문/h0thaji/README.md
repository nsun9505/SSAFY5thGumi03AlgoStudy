# [17609] 회문

## 분류
> 구현
>
> 문자열

## 코드
```java
package BOJ17609_회문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			sb.append(tqghlans(br.readLine())).append("\n"); // readLine을 tqghlans으로 넘겨주고 받은 값을 sb에 append 시켜줌
		
		}
		System.out.println(sb);
	}
	private static int tqghlans(String s) {
		int cnt = 0;
		int l = 0,r = s.length()-1;
		// 왼쪽 , 오른쪽으로 포인터를 나눠줌
		while(l<=r) { //왼쪽 인덱스가 오른쪽인덱스보다 커져버리면 stop
			if(s.charAt(l) != s.charAt(r)) { //두문자가 다르다면
				
					//왼쪽 +1 인덱스와 오른쪽이 같은지 확인
					for (int start = l+1, end = r; start <= end; start++, end--) {
						if(s.charAt(start)==s.charAt(end)) continue; // 같다면 continue;
						//다르다면 cnt++
						cnt++;
						break;
					}
					
					//왼쪽 인덱스와 오른쪽+1 인덱스 값이 같은지 확인
					for (int start = l, end = r-1; start <= end; start++, end--) {
						if(s.charAt(start)==s.charAt(end)) continue; //같다면 continue;
						//다르다면 cnt++;
						cnt++;
						break;
					}
					//두문자가 달랐으니 cnt++
					cnt++;
					break;
			}else { //두문자가 같다면 l++,r--
				l++;
				r--;
			}
		}

		if(cnt == 0) return cnt; // 회문인경우 > return 0
		else if(cnt > 2) return 2; // 2초과이면 회문X > return 2 xabbay
		else return 1; // cnt가 2거나 1일경우 유사회문 ,  summuus, xabba ,sumumuus  > return 1
		
	}
}
```

## 문제 풀이

- 입력받은 문자열이 회문('0'출력) , 유사회문('1'출력), 회문이 아님('2'출력) 이 세가지 경우를 찾는 문제였습니다.
- l 과 r 두개의 포인터를 주고 두 문자가 같다면 l은 ++ , r은 --를 해주며 l <= r 같을 때까지 찾는 걸 반복하고 해당 반복문을 빠져 나와 0을 출력하게 됩니다.
- 두 문자가 다르다면 왼쪽 문자열을 하나 빼주고 검사를 하고 오른쪽 문자열을 하나 빼주고 검사를 하는 방식으로 회문을 찾았습니다.

- 아래는 처음에 짠 회문을 찾는 메서드 소스입니다. <어떤게 틀렸는지 설명하는 소스이므로 넘어가도 됩니다. 아래쪽에 풀이방법을 자세히 적어놨습니다>

- - ```java
    //반례 : sumumuus
    	private static int tqghlans(String s) {
    		int cnt = 0;
    		int l = 0,r = s.length()-1;
    
    		while(l<=r) {
    			if(cnt >= 2) return 2;
    			//if(cnt == 2) return cnt;
    			//반례:abaaxxbbb
    			if(s.charAt(l) != s.charAt(r)) {
    				
    				if(s.charAt(l+1) == s.charAt(r)) {
    					l++;
    				}else if(s.charAt(l) == s.charAt(r-1)) {
    					r--;
    				}else {
    					cnt++;
    				}
    				cnt++;
    			}else {
    				l++;
    				r--;
    			}
    		}
    
    		if(cnt == 1) return cnt;
    		else return cnt;
    		
    	}
    ```

  - 해당 소스에서  'abaaxxbbb'같은경우 cnt가 3이 돼버려서 문자열을 끝까지 순회해버려 시간초과가 나서 cnt >= 2로 고쳤습니다.
  - 그런데 고치고 보니 잘 못 푼소스였습니다. 위에 소스는 두 문자가 다르다면 왼쪽 문자열을 하나 빼주고 검사를 하고 오른쪽 문자열을 하나 빼주고 검사를 하는 방식이지만 한방향으로만 찾게됩니다. 반례로 'sumumuus' 을 들어보겠습니다.
    - 시작점과 끝점을 비교하다 보면 l = 3, r =5 에서 두 문자가 다르므로 if문에 걸립니다.
    - 그러고는 l = 4 와 r= 5를 비교하는데 같으므로 l과 r-4의 문자열 비교는 생략하고 넘어 가버리고는 답이 1로 출력이 되어버립니다. 그래서 지금 소스로 고치게 되었습니다.




- 왼쪽(l = 0) 과 오른쪽(r = s.length()-1)포인터를 나누어주고 l 과 r 을 더해주고 빼주면서 비교를 하였습니다.
- l과 r로 문자열을 검사하다가 같다면 l은 ++해주고 r은 --해주면서 l<=r 이 되면 해당 반복문을 벗어나게 됩니다. 이 문자열은 cnt =0 이므로 회문입니다. cnt=0 경우 return 0 을 해줍니다
  - ex ) abba 
- 비교를 하다가 두 문자가 다르다면 새로운 포인터 start (l+1) , end(r) 을 주고 비교를 시켜 또 다시 다른 문자가 있다면 cnt++를 시키고 벌써 두번 틀렸기 때문에 반복문을 돌려줄 필요가 없으므로 break를 시켰습니다.
- 왼쪽문자열 하나를 빼고 비교하였다면 이번에는 오른쪽 문자열을 하나빼고 비교를 해야합니다. 또 다시 새 포인터 start(l) 과 end(r-1)를 주고 서로를 비교하며 다른 문자가 있다면 cnt++를 시키고 반복문을 더 이상 돌려줄 필요가 없기 때문에 break를 시킵니다.
- 마지막으로 두문자가 달라서 if문에 걸렸기 때문에 cnt++를 해주고 위 두 번의 검사로 이 문자열은 검사가 끝났기 때문에 더 이상 반복문을 돌려줄 필요가 없기 때문에 break를 해줍니다
- cnt > 2 이면 회문이 아니므로 retrun 2를 해줍니다.
  - ex ) xabbay  => cnt = 3이다.
- cnt == 1 || cnt == 2 이면 유사회문이므로 return 1를 해줍니다.
  - ex) xabba => cnt = 2 / sumumuus cnt = 1

