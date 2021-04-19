# [3237] 두 수의 합

## 분류
> 투포인트

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		int sum = 0;

		int start = 0;
		int end = n-1;
		
		while(start<end) {
			if(arr[start]+arr[end]==x) {
				sum++;
//				end=end-1;	//둘중아무거나 순서 상관없음 ->어차피 다 고려될 예정
				start=start+1;
			}
			else if(arr[start]+arr[end]>x) {
				end=end-1;
			}
			else {
				start=start+1;
			}
		}
		System.out.println(sum);
	}
}
```

## 문제 풀이
일단 이번주가 투포인터로 푸는 주라서 아이디어는 쉽게 생각난거 같습니다.

배열중에 두개고르는 쌍만 고르면 되기 때문에 속는셈치고 모든조합을 해봤더니 당연히 시간초과가 나왔습니다^^

그래서 배열을 정렬을 해주고 양 끝에서부터 중간으로 좁혀오면서 조건을 체크해줬습니다

배열 양 끝에서 좁혀오면서 만약 그 수가 조건과 같으면 결과를 +1해줍니다. 그리고 여기에서 end--를 해주든 start++을 해주든 상관없습니다.

어차피 둘 다 고려될 경우이기 때문입니다.

그리고 만약 그 합이 조건 X보다 작다면 start를 증가시켜 줍니다. 중간으로 다가올수록 start에서 출발한 수는 커지기 때문입니다(정렬되었기 때문)

반대로 그 수가 조건X보다 작다면 end를 한 번 내려줍니다. (수가 작아지게 하기 위해서)

while문 끝까지 돌면 종료됩니다. while문 조건은 start < end 입니다. (i < j이기 때문) 