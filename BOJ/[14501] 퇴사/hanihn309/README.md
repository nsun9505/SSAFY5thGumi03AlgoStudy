# [14501] 퇴사

## 분류
> 그리디 

## 코드
```java
package bkj_14501_퇴사; // 210407

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 퇴사까지 남은 날
		int[] T = new int[N+1]; // 상담하는데 걸리는 기간
		int[] P = new int[N+1]; // 상담 금액
		
		for(int i = 1; i <= N; i++)  {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] profit = new int[N+2];
		
		for(int i = 1; i <= N; i++) {
			// i날에 일 안하는 경우
			profit[i+1] = Math.max(profit[i+1], profit[i]);
						
			// i날에 일 하는 경우
			if(i+T[i] > N+1) // N일 초과하면 일 시작하면 안됨
				continue;
			
			profit[i+T[i]] = Math.max(profit[i+T[i]], profit[i] + P[i]);
		}
		System.out.println(Math.max(profit[N+1], profit[N]));
	}
}
```

## 문제풀이
- 어려웠어요.. DP.. 수학이다 수학..
- 상윤님한테 설명 오래 듣고 짰는데 if 조건문 잘못 맞춰서 또 틀리고,,, 코드 자체는 길지 않지만 주의해야할게 좀 있는 문제 같습니다.
- 글로 설명하기엔 조금 복잡하지만, 큰 틀은 N일까지 순차적으로 가면서 검사를 해나간다는 것입니다. 1일부터 시작해서, 일을 한다면 1일에 시작한 일이 걸리는 날(T1) 후의 날짜에 금액을 넣어주면 됩니다. 하지만, 다른 날에서 일을 해서 해당 T1후의 날에 일을 마칠수도 있는데, 이 때 '최대 이익'을 구한다는 문제의 조건에 맞게 max값 비교로 값을 넣어주면 됩니다. 그리고 1일에 일을 시작하지 않는다면, 1일까지 벌어놓은 금액을 2일에 그대로 넣어주면 됩니다. 물론 그 날 이미 계산된 값이 들어가있을 수 있기 때문에, 똑같이 max값 비교로 값을 넣어주면 됩니다. 이런 반복을 1일부터 N일까지 해주면 됩니다.
- 주의해야할 점은, 일을 한다는 가정을 했지만 일을 끝나는 날이 N일을 넘어서면 그 날 일을 시작하면 안됩니다. 여기서 제가 틀렸었는데, 일을 시작하지는 않지만 현재까지 벌어놓은 금액은 유지되어야 하므로, 다음 날로 현재까지 벌어놓은 돈을 넣어줘야 합니다.
- 그래서 벌어놓은 돈을 저장하는 배열은 1차원 배열로 저장하면 되고, 값을 넣을 땐 max 비교를 해야한다는 조건만 잘 주면 어렵지만 짧게 코드를 작성할 수 있습니다.