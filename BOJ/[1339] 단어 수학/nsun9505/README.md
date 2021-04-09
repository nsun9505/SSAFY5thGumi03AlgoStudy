# [1339] 단어 수학

## 분류
> 그리디 알고리즘

## 코드
```java
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] inputs = new String[N];
        Element[] positions = new Element[26];
        for(int i=0; i<26; i++)
            positions[i] = new Element(i, 0);
        for(int i=0; i<N; i++) {
            // 뒤집기
            inputs[i] = br.readLine();

            int mul = 1;
            for(int idx=inputs[i].length()-1; idx>=0; idx--, mul *= 10){
                int alphabet = inputs[i].charAt(idx) - 'A';
                positions[alphabet].sum += mul;
            }
        }

        // 자릿수 합이 가장 큰 것을 알기 위해 정렬
        Arrays.sort(positions);

        int num = 9;
        int sum = 0;
        for(int i=0; i<26; i++){
            if(positions[i].sum == 0)
                break;
            sum += positions[i].sum * num--;
        }
        sb.append(sum);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element implements Comparable<Element>{
        int alphabet;
        int sum;

        public Element(int alphabet, int sum) {
            this.alphabet = alphabet;
            this.sum = sum;
        }

        @Override
        public int compareTo(Element o) {
            return o.sum - this.sum;
        }
    }
}
```

## 문제 풀이
단어에서 나오는 알파벳의 위치를 자릿수 값을 모두 더해서 자릿수들의 합으로 정렬해서 가장 큰 자릿수 합을 가지는 알파벳에 9부터 차례대로 할당하면 됩니다.

이것이 성립하는 이유는 많이 나오는 것도 중요하다고 생각할 수 있지만, 알파벳의 자릿수 합이 더 큰 수에 더 큰 수를 곱한다면 더 큰 수를 얻을 수 있기 때문입니다.

예를 들어, 아래와 같은 예시가 있다고 하겠습니다.

```
2
AB
BB

답 : 188
```
- A는 10의 자리수를 가지므로 A의 자릿수 합은 10입니다.
- B는 10의 자리수 한 번, 1의 자리에서 2번 나오므로 자릿수의 합을 모두 더하면 12가 되기 때문에 12 * 9가 10 * 9보다 큰 것을 볼 수 있습니다.

그래서 먼저 각 입력 문자열에서 알파벳이 어느 자리에 나오는지 알아내고, 그 자릿수만큼 해당 알파벳 배열에 더해주면 됩니다.

이 작업이 모두 끝나면 sum 값을 기준으로 내림차순 정렬해서 알파벳에 9, 8, ... , 0 순으로 값을 주고 sum 값이랑 곱한 값을 모두 더해서 출력하면 됩니다.