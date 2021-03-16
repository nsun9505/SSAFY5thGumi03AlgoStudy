# [13335] 트럭 - JAVA

## 분류
> 구현
>
> 트럭

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {	//트럭

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> bridge= new LinkedList<>();	//다리위
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	//트럭의 수 
		int W = Integer.parseInt(st.nextToken());	//다리의 길이 
		int L = Integer.parseInt(st.nextToken());	//최대 하중
		
		int[] wait = new int[n];	//트럭 대기 
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			wait[i] = Integer.parseInt(st.nextToken());
		}
		
		int time = 0;	//시간	
		int weight = 0;	//하중
		
		for(int i=0;i<n;i++) {
			while(true) {
				if(bridge.isEmpty()) {
					time++;
					bridge.add(wait[i]);
					weight +=wait[i];
					break;
				}
				else if(bridge.size()==W) {
					weight-=bridge.peek();	//맨앞트럭 무게 빼주기
					bridge.poll();
				}
				else {
					if(weight + wait[i]<=L) {
						time++;
						bridge.add(wait[i]);
						weight +=wait[i];
						break;
					}
					else {
						time++;
						bridge.add(0);	//맨 앞 트럭을 앞으로 밀어줌
					}
				}
			}
		}
		time+=W;
		System.out.println(time);
	}
}
```

## 문제 풀이
대기하는 트럭들은 wait 배열에 , 다리는 bridge라는 queue를 만들어서 풀었습니다.

다리 위가 1.비었을때 2.꽉 찼을 때 3.나머지 경우로 나누어서 풀었습니다.

다리위에서 어떻게 하면 size를 늘리면서 트럭을 앞으로 밀어주는지 모르겠어서 검색해보니 queue에 0을 추가해 하중은
추가하지 않으면서 다리 위 공간을 채워주는 방식으로 사용했습니다.