# [1062] 가르침

## 분류
> 백트랙킹

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] strings;
    static int answer = 0;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        strings = new int[N];
        HashSet<Integer> letterSet = new HashSet<>();

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            int num = 0;
            for(int idx=0; idx<tmp.length; idx++){
                num |= (1 << (tmp[idx] - 'a'));
                if(letterSet.contains((1 << (tmp[idx] - 'a'))))
                    continue;
                letterSet.add((1 << (tmp[idx] - 'a')));
            }
            strings[i] = num;
        }
        list.addAll(letterSet);

        solution(0, 0, 0);
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int cntOfLetter, int index, int letters){
        if(cntOfLetter > K)
            return;
        if(cntOfLetter == K || index >= list.size()){
            int cnt = 0;
            for(int i=0; i<N; i++){
                // 지금까지 배운 글자로 입력 받은 단어를 읽을 수 있는지 검사
                if((strings[i] & letters) == strings[i])
                    cnt++;
            }
            answer = answer< cnt ? cnt : answer;
            return;
        }

        // 단어 배우기
        for(int i=index; i<list.size(); i++){
            solution(cntOfLetter+1, i+1, (letters | list.get(i)));
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

단어를 비트로 바꾼 값이 세팅되고, 배울 수 있는 글자들도 리스트로 바꿔줍니다.

이제 글자 리스트에서 조합으로 K개 글자 또는 K개 이하의 단어를 배웠을 때

즉, 배운 글자에 해당하는 비트가 letter이라는 비트에 마스킹되므로 이것과 입력받은 단어와 and 연산해서 입력받은 단어가 나온다면 해당 단어를 읽을 수 있는 것이므로 카운트를 합니다.

마지막으로 카운트한 값과 답을 비교하여 카운트한 값이 크다면 답을 갱신하면 됩니다.