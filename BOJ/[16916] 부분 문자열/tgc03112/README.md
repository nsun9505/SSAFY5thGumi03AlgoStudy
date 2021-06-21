# [16916] 부분 문자열

## 분류

> 문자열
>
> KMP

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int res;
	static String S,P;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine();
		P = br.readLine();
		res = 0;

		KMP();

		System.out.println(res);
	}

	private static void KMP() {

		int[] arr = makeTable(P);

		int Slength = S.length();
		int Plength = P.length();

		int idx = 0;
		for(int i=0;i<Slength;i++) {
			while(idx>0 && S.charAt(i)!=P.charAt(idx)) {
				idx=arr[idx-1];
			}

			if(S.charAt(i)==P.charAt(idx)) {
				if(idx == Plength-1) {	//길이가 P길이만큼 된경우가 나왔다면
					idx=arr[idx];
					res=1;
					break;
				}
				else {
					idx++;
				}
			}
		}
	}

	private static int[] makeTable(String P) {
		int n = P.length();
		//찾아야될 문자열 만큼 배열 길이 만듦
		int[] arr = new int[n];

		int idx = 0;
		for(int i=1;i<n;i++) {
			//일치하는 문자가 발생했을 때(idx>0)
			while(idx>0 && P.charAt(i) != P.charAt(idx)) {
				//연속적으로 더 일치하지 않으면 직전의
				idx = arr[idx-1];
			}
			if(P.charAt(i)==P.charAt(idx)) {
				//문자열이 일치할 때 +1 해준 값을 배열에 넣어준다
				idx++;
				arr[i]=idx;
			}
		}
		return arr;
	}
}

```

## 문제 풀이

KMP알고리즘을 사용해서 풀었습니다.

KMP알고리즘은 접두사와 접미사가 일치하는 최대길이를 찾아서 문제를 해결하는 방식입니다.

두개의 문자열을 비교하여 접두사와 접미사가 일치하는 최대의 길이를 찾고 최대 길이를 저장하는 배열을 만듭니다.

모든 경우를 계산하지 않아도 되고 반복되는 연산이 없어지기 때문에 빠르게 뒤로 점프하면서 문자열을 비교하며 찾을 수 있습니다.
