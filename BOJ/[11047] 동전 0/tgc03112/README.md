# [11047] 동전 0

## 분류
> 그리디

## 코드
```java
import java.util.Scanner;

public class Main {	//동전0

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();	//가치
		int sum =0;
		int[] arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		
		for(int i=N-1;i>=0;i--) {
			sum+=K/arr[i];
			K%=arr[i];
		}
		System.out.println(sum);
	}
}

```

## 문제 풀이
매우 그리디하게 가진 돈을 순서대로 받아오고 뒤에서 부터 큰 돈 순으로? 깎아 내려오며 풀었습니다 ~