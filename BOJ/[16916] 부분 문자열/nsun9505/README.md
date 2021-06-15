# [16916] 부분 문자열

## 분류

> 문자열
>
> KMP

## 코드

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] input = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();

        sb.append(KMP(input, pattern));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] makeFailFunction(char[] pattern){
        int patternSize = pattern.length;
        int[] fail = new int[patternSize];
        int j=0;

        for(int i=1; i<patternSize; i++){
            // 다른 경우 j-1를 인덱스로 이전 것으로 옮겨서 보기
            while(j > 0 && pattern[i] != pattern[j])
                j = fail[j-1];
            // 같으면 j길이만큼 같다고 저장
            if(pattern[i] == pattern[j])
                fail[i] = ++j;
        }
        return fail;
    }

    private static int KMP(char[] input, char[] pattern) {
        int[] fail = makeFailFunction(pattern);

        int j=0;
        for(int i=0; i<input.length; i++){
            // 다를 경우 뒤에 것을 보자.
            while(j > 0 && input[i] != pattern[j])
                j = fail[j-1];
            // 같을 경우
            if(input[i] == pattern[j]){
                // 패턴과 동일한 부분 문자열을 찾은 경우 리턴 0
                if(j == pattern.length-1)
                    return 1;
                // 아직 pattern의 끝까지 안 왔다면 j 증가
                else
                    j++;
            }
        }
        return 0;
    }
}

```

## 문제 풀이

KMP를 사용해서 풀었습니다.

KMP는 접미사와 접두사를 사용해서 fail 함수를 만들고

fail 함수를 기반으로 패턴과 동일한 부분 문자열을 찾는 알고리즘입니다.

KMP를 사용해서 패턴에 맞는 문자열을 찾으면, 바로 1을 리턴해서 프로그램을 끝내도록 했습니다.

KMP 알고리즘 수행이 끝났어도 패턴과 일치하는 부분 문자열을 찾지 못한 경우에는 0을 리턴하면 됩니다.
