# [2661] 좋은 수열

## 분류

> 백트랙킹

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		good("");
	}

	private static void good(String res) {

		if(res.length() == N) {	//종료조건
			System.out.println(res);	//출력하고 종료
			System.exit(0);
		}
		else {
			for(int i=1;i<=3;i++) {	//1, 2, 3 하나씩 넣어보면서 가능한 수열인지 확인
				if(check(res+i)) {
					good(res+i);
				}
			}
		}
	}

	private static boolean check(String str) {
		int leng = str.length()/2;	//최대 확인은 길이의 반만 확인하면 됨 (ex : 12/13 )

		for(int i=1;i<=leng;i++) {	//길이의 절반까지 확인해줌
			int fin = str.length()-i;	//뒤에서부터 확인 -> 수열이 하나씩 늘어갈 때 항상 맨 뒤의 숫자가 변경되기 때문
			if(str.substring(fin-i, fin).equals(str.substring(fin, fin+i)))
				return false;
		}
		return true;
	}
}
```

## 문제 풀이

문자열에 숫자를 1 2 3 차례대로 하나씩 넣어가며 좋은 수열이 되는지 체크하면서 반복하는 재귀를 이용한 문제입니다.

재귀의 종료조건은 좋은수열이 되면서 길이가 N만큼 충족되면 종료됩니다.

문자열에 1 2 3 을 넣어주면서 좋은 수열인지 체크합니다. 1 2 3 으로 넣어주는 이유는 좋은수열이 되면서 작은값을 구해야 하기 때문입니다.

check 함수에서 좋은수열인지 확인해줍니다.

만약 121312 여섯자리가 들어왔을때 비교는 121/312 까지만 비교해주면 됩니다. 그래서 for문은 length/2까지 !

1 2 1 3 1 2 가 되었을때 가장 뒤에 변한 숫자를 기준으로만 확인해주면 됩니다. 처음에 접근할때는 중간에 좋은수열들은 어떻게 체크해줘야 하는지 고민했는데 고민할 필요가 없는게 이미 6자리가 만들어졌다는것은 앞에서 좋은수열임을 확인받고 넘어왔기 때문에 뒤에 추가된 숫자를 기준으로만 확인해주면 됩니다.

그래서 1 2 1 3 "1" vs "2" 두개를 비교해주고 1 2 "1 3" vs "1 2" 비교, "1 2 1" vs "3 1 2"비교를 해줘서 좋은 수열이라면 true를 리턴해줍니다.

만약 같은부분이 있어서 좋은수열이 안된다면 false를 리턴해주고 다시 for문에서 1 2 3중 다음 숫자를 넣으면서 반복합니다.

좋은수열이면서 길이가 N으로 충족되면 종료 ㅎㅎ
