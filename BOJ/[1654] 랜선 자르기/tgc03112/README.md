# [1654] 랜선 자르기

## 분류
> 이분탐색
>
> 파라메트릭 서치

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
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		for(int i=0;i<K;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		long max = arr[K-1];
		long start = 1;
		long mid = 0;
		
		while(start <= max) {
			mid = (start+max)/2;
			long sum = 0;
//			System.out.println("mid : "+mid);
			for(int i=0;i<K;i++) {
				sum+=arr[i]/mid;
			}
			if(sum>=N) {
				start = mid+1;
			}
			else{
				max = mid-1;
			}
		}
		System.out.println(max);
	}
}
```

## 문제 풀이
나무자르기와 비슷하게 이분탐색으로 풀었습니다.

N개 이상의 랜선을 구하기 위해 가장 긴 랜선을 반으로 잘라주며 모든 랜선을 그 나눈 값으로 나눠주고 그 합이 N이상이 되면 조건을 만족합니다.

여기서 로직은 맞는데 계속 틀렸습니다가 나오길래 구글검색을 보니 나무자르기와 같은 실수로 계속 int로 설정해서 틀렸습니다.

또 두번째 실수는 start 초기값을 0으로 줘서 0으로 나눠지는 경우도 있게 했습니다. 문제에서 자연수라고 했기 때문에 start는 1이 되어야 맞습니다.

문제를 똑바로 읽고 이제 시간초과 뿐 아니라 수의 범위를 초과하는것도 고려하며 풀어야겠다고 생각했습니다..