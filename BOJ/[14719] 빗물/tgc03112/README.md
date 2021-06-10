# [14719] 빗물

## 분류

> 구현
>
> 시뮬레이션

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] arr = new int[W];
		st=new StringTokenizer(br.readLine());

		for(int i=0;i<W;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}

		int res=0;
		for(int i=0;i<W;i++) {
			//나 포함 왼쪽으로 가면서 나보다 높은 기둥 구하기
			int left=i;
			for(int l=i;l>=0;l--) {
				if(arr[l]>arr[left]) {
					left=l;
				}
			}
			//나 포함 오른쪽으로 가면서 나보다 높은 기둥
			int right=i;
			for(int r=i;r<W;r++) {
				if(arr[r]>arr[right]) {
					right=r;
				}
			}
			//왼쪽 오른쪽 중에 작은 기둥 - 내높이 => 현재 위치에서의 빗물 받은 넓이
			int min=Math.min(arr[left], arr[right]);
			int high=min-arr[i];
			if(high>0) {
				res+=high;
			}
		}
		System.out.println(res);
	}
}
```

## 문제풀이

처음에 2차원 배열로 만들어서 한층씩 탐색하는 방법을 생각했지만 다시 생각해보니 현재 위치에서 물 넓이를 구하고 전체적인 물 넒이를 다 더해주면 되는 문제였습니다.

배열을 가로로 탐색하며 내 위치에서 왼쪽방향, 오른쪽방향으로 탐색하며 내 현재 높이보다 높은 기둥을 찾으면 됩니다.

그리고 오른쪽과 왼쪽 기둥중에 작은것을 선택합니다. 작은것을 선택하는 이유는 작은기둥만큼만 물을 채울 수 있기 때문입니다.

그래서 그중 작은 기둥-내현재 높이를 해주면 i번째에 채울 수 있는 물의 칸수가 나옵니다.

이런식으로 인덱스를 ++해가며 마지막까지 탐색해서 모든 물의 넓이를 res에 누적해주면 됩니다.
