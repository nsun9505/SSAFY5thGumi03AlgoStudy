# [1062] 가르침

## 분류
> 백트랙킹

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] strings;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        strings = new int[N];

        // 기본적으로 anta, tica가 붙음.
        int letter = 0;
        for(char ch : new char[]{'a', 'n', 't', 'i', 'c'})
            letter |= (1 << (ch - 'a'));

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int idx=0; idx<tmp.length; idx++)
                strings[i] |= (1 << (tmp[idx] - 'a'));
        }

        // 남극 단어는 a, n, t, i, c가 있어야 앞부분과 뒷부분의 글자를 읽을 수 있음.
        solution(5, 0, letter);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int cntOfLetter, int index, int letters){
        if(cntOfLetter > K)
            return;
        if(cntOfLetter == K){
            int cnt = 0;
            for(int i=0; i<N; i++){
                // AND 연산을 해서 strings[i]가 그대로 나온다는 것은
                // letters에 strings[i]에 대한 비트가 모두 켜져있음.
                if((strings[i] & letters) == strings[i])
                    cnt++;
            }
            answer = answer< cnt ? cnt : answer;
            return;
        }

        // 입력으로 주어진 단어에 포함된 글자만 배울 필요는 없음.
        for(int i=index; i<26; i++){
            // 아직 배우지 못한 글자라면
            if((letters & (1 << i)) == 0)
                solution(cntOfLetter+1, i+1, (letters | (1 << i)));
        }
    }
}
```

## 문제풀이
백트랙킹과 비트마스킹을 사용해서 문제를 풀었습니다.

배울 수 있는 언어는 HashSet을 사용해서 중복을 제거합니다.

그리고 각 단어에 나오는 글자들을 알아내기 위해서 비트마스킹을 사용했습니다.

예를 들어, abc라는 단어를 비트로 나타내면 111이 됩니다.
   - 즉, 2^0는 a, 2^1는 b, ... , 2^25는 z를 나타냅니다.
   - 글자가 몇번 나타나든 글자 하나만 알면 단어안에 있는 배운 글자는 다 읽을 수 있으므로 |(or) 연산을 통해서 비트 마스킹을 하면 됩니다.

단어를 비트로 바꾼 값이 세팅됩니다.

글자를 배우는 것은 꼭 입력으로 주어진 단어들의 글자만 배우는 것도 가능하겠지만, 모든 영어 소문자를 배워보는 형식도 상관없음.

만약에 입력을 받은 단어의 글자만 배우겠다는 것도 괜찮지만, K가 주어진 단어 중에서 가장 긴 것보다 크다면 K를 만족시키지 못하는 경우가 있어서 쉽게 가기 위해서 a ~ z까지 다 배우는 걸로 카운트하면 됩니다.

문제에서도 K는 26보다 작거나 같은 자연수 또는 0이라고 했으니 26은 소문자 개수를 의미합니다. 

그래서 영어 소문자를 다 배워보는 형식의 for문을 돌리면 됩니다.

그리고 배운 글자에 해당하는 비트가 letter이라는 비트에 마스킹되므로 이것과 입력받은 단어와 and 연산해서 입력받은 단어가 나온다면 해당 단어를 읽을 수 있는 것이므로 카운트를 합니다.

마지막으로 카운트한 값과 답을 비교하여 카운트한 값이 크다면 답을 갱신하면 됩니다.