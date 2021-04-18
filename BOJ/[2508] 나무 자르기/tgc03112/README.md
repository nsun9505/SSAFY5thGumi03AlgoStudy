# [2508] 나무 자르기

## 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<arr.length;i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int start = 0;
		int end = arr[N-1];
		
		while(start<=end) {
			int mid = (start+end) / 2;
			
			long tree = 0;
			
			for(int i=0;i<N;i++) {
				if(arr[i]>mid) {	
					tree += arr[i]-mid;	//잘려나간 나무
				}
			}
			if(tree==M) {
				System.out.println(mid);
				System.exit(0);
			}
			else if(tree<M) {
				end = mid-1;
			}
			else{
				start = mid+1;
			}
		}
		System.out.println(end);
	}
}
```

## 문제 풀이
이분탐색을 사용해서 푸는 문제였습니다.

이 문제의 관건은 나무 길이를 long 으로 설정해야 하는 것이였습니다.

나무의 길이대로 정렬하고 조건에 맞으면 출력 아니라면 범위를 end와 start로 조절하여 나무를 다시 자르는 과정을 반복하면 됩니다.