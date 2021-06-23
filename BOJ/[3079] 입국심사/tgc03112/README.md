# [3079] 입국심사

## 분류

> 이분탐색

## 코드

```java
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//심사대 개수
		long M = Long.parseLong(st.nextToken());	//줄 선사람
		
		long[] arr = new long[N];
		long max = 0;
		
		for(int i=0;i<N;i++) {
			arr[i]=Long.parseLong(br.readLine());	//심사대 시간
			max = Math.max(arr[i], max);	//심사대 중에 가장 긴 시간 * 줄선사람 = 최대범위
		}
		
		long start = 1;
		long end = max*M;
		long res = end;
		long mid = 0;
		
		while(start<=end) {
			mid = (start+end)/2;
			
			long cnt = 0;
			
			for(int i=0;i<N;i++) {	//mid 초에 몇 명의 인원이 통과할 수 있는지 계산
				cnt += mid/arr[i];
			}
			
			if(cnt >= M) {	//친구를 다 심사할 수 있는 시간이라면
				res = Math.min(mid, res);	//둘 중작은것
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		System.out.println(res);
	}
}

```

## 문제풀이

이분탐색 알고리즘을 사용했습니다.

아이디어를 떠올리고 long을 사용해야겠다고 생각하면 생각보다 어렵지않은 문제였습니다.

먼저 1초 ~ 걸릴 수 있는 가장 긴 시간(가장 긴 심사대 시간 * 친구의 수) 범위 내에서 이분탐색을 통해 탐색 해줍니다. 가장 긴시간은 모든 친구들이 가장 긴 심사대에서 심사를 받는 경우입니다.(최악)

cnt로 그 시간대에 통과할 수 있는 사람의 수를 계산한 뒤 만약 친구들이 다 통과할 수 있는 시간이라면 res에 계속해서 작은값을 저장해주고 mid - 1 을 해서 더 작은 경우가 있는지 탐색해줍니다.

만약 반대로 친구들이 통과할 수 없는 시간대라면 시간을 늘려서 친구들이 다 통과할 수 있는 시간을 찾을 수 있도록 합니다.
