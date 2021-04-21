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

public class BOJ_3273_두_수의_합 { // 210421
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int[] array = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(array);
		
		int left = 0, right = n-1;
		int count = 0; // 조건을 만족하는 쌍의 개수
		
		while(left < right) { // left가 right보다 작은 동안만 돌기
			int result = array[left] + array[right]; // 양 끝값을 더해보기
			 
			if (result < x) // 더한 값이 찾고자하는 x값보다 작으면 left 값을 늘려주기
				left++;
			else if (result > x) // 더한 값이 찾고자하는 x값보다 크면 right 값을 줄여주기
				right--;
			else { // 더한 값이 찾고자하는 x값과 같으면 count 늘려주고, 다른 쌍이 더 있을수도 있으니 left, right 값을 조정
				count++;
				left++;
				right--;
			}
		}
		
		System.out.println(count);
	}
}
```

## 문제 풀이
- 투 포인터 문젠데 난이도가 쉽습니다. 이름답게 left와 right 두개의 포인터를 준 후, 각각을 늘려가고 줄여주며 조건에 맞는 답을 구해가면 됩니다.
- 찾아야하는 x값에 해당하는 쌍을 찾기 위해, left와 right값에 해당하는 두 값을 더해서, 그 값이 x보다 작다면 left값을 늘려주고 x보다 크다면 right 값을 줄여줍니다. 찾고자 하는 x값을 찾았다면 다른 쌍이 더 존재할 수도 있으므로, left와 right 값을 다시 조정해서 계속 검사하면 됩니다.