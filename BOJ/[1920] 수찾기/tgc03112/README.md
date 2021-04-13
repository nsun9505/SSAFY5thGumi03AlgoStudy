# [1920] 수찾기

## 분류
> 이분탐색

## 코드
```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = null;
		int N = sc.nextInt();	//찾아질 대상 
		int[] find = new int[N];
		for(int i=0;i<N;i++) {
			find[i] = sc.nextInt();
		}
		Arrays.sort(find);
		int M = sc.nextInt();
		
		for(int i=0;i<M;i++) {
			int num = sc.nextInt();
			int start = 0;
			int end = N-1;
			int res = 0;
			
			while(start<=end) {
				int mid = (start+end)/2;
				
				if(find[mid] == num) {
					res=1;
					break;
				}
				if(num<find[mid]) {
					end=mid-1;
				}
				else if(num>find[mid]) {
					start = mid+1;
				}
			}
			System.out.println(res);
		}
	}
}
```

## 문제 풀이
이분탐색을 연습해보는 문제였습니다.

수를 입력받고 mid 중간값을 계속해서 비교해준 다음에 중간값이 찾으려는 수보다 작으면 mid를 end로 바꾸어 범위를 반으로 줄여주고 mid보다 크다면 start를 조절해주어 범위를 계속해서 반으로 줄여나가면 됩니다.

못찾으면 res가 그대로 0이 출력되고 mid값으로 찾으려는 값을 찾으면 res가 1로 바뀌며 while문 종료합니다.