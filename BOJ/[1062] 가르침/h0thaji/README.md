# [1062] 가르침

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N,K, res = 0; // 단어 개수, 가르칠 알파벳 수, 결과
	static String[] word; // 입력 받을 단어 배열
	//a c n t i
	static boolean[] v = {true,false,true,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,true,false,false,false,false,false,false}; // 방문체크배열 , acint은 무조건 포함이므로 true
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		word = new String[N];
		
		if(K < 5) sb.append(res); // 남극언어는 모든 단어에 acint가 포함이므로 5글자 이상을 가르쳐야지만 단어를 읽을 수 있으므로 k가 5미만이면 0을 출력
		else if(K == 26) sb.append(N); // 알파벳 26개를 다 가르칠 수 있으면 모든 단어를 읽을 수 있으니 N
		else { // 5 < K < 26 이면
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				word[i] = str.substring(4,str.length()-4); // anta / tica는 모든 단어에 포함이므로 사이에 있는 단어만 확인 하기위해 잘라서 배열에 넣는다
				
			}
			
			dfs(0,0); // 탐색
			sb.append(res);
		}
			
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int cnt, int start) {
		if(cnt == K-5) { // acint를 제외하고 나머지 알파벳
			int sum = 0; // 읽을 수 있는 단어 수
			
			for (int i = 0; i < N; i++) {
				boolean ok = true;
				for (int j = 0; j < word[i].length(); j++) {
					if(!v[word[i].charAt(j) -'a']) { // 가르치지 않은 알파벳이 있는 경우 해당 단어를 읽을 수 없으므로
						ok = false;
						break;
					}
					
				}
				if(ok) sum++; // true이면 읽을 수 있는 단어이므로 단어 수 +1
			}
			
			res = Math.max(sum, res); // 결과값과 sum의 최대값이 결과값이 됨
			return;
		}
		
		for (int i = start; i < 26; i++) { // 26개의 알파벳 중에서 K-5를 뽑아야 하므로 반복문 start부터 시작해서 재귀를 탈때마다 cnt,i +1씩해줌
			if(!v[i]) { // 방문하지 않았을 때 => 이미 가르친 단어가 아닐 때
				v[i] = true; // 가르치고, 뽑고, 방문하고
				dfs(cnt+1,i+1); // 재귀
				v[i] = false; // 해당 알파벳 가르쳤을 경우를 다 확인 했으니 false로 변경
			}
		}
		
	}

}

```

## 문제풀이

- K개의 알파벳을 학생들에게 가르쳐 남극단어 N개 중 읽을 수 있는 단어 개수의 최대값을 출력하는 문제입니다.
- 알파벳 26개 중에 K개를 뽑는 것, 즉 순서가 상관이 없고 중복이 없는 조합을 뽑는 문제입니다.
  - 남극단어는 anta , tica로 무조건 이루어져 있으므로 남극단어들은 a,n,t,i,c를 포함
  - 즉, 남극단어를 읽으려면 antic , 5개이상의 알파벳을 배워야합니다.(=> antic 5 알파벳을 포함시키고 K-5개만 확인)
  - 또한 알파벳 26자를 다 배우게 되면 모든 단어를 읽을 수 있습니다.(=> K=26이면 N이 결과값)

- 조합을 만들기 전 if , else문으로 K<5, K==26, else 3가지 경우로 나뉘어 결과값을 결정합니다.
- word 배열 , 입력할 단어(가르칠 단어)를 저장하는 배열인데 anta , tica는 무조건 포함이니 해당 단어는 자르고 나머지 문자열을 배열에 삽입합니다.
- 알파벳을 조합해 해당 단어들을 최대한 많이 읽을 수 있는 경우를 결과값으로 출력합니다.