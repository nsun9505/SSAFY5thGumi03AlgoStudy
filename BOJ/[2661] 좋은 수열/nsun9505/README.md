# [2661] 좋은 수열

## 분류
> 백트랙킹

## 코드
```java
import java.io.*;
import java.util.ArrayList;

public class Main {
    static int N;
    static char[] str;
    static ArrayList<String> list = new ArrayList<>();
    static char[] arr = {'1', '2', '3'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        str = new char[N];

        solution('0');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean solution(char prev){
        // 가장 작은 수(1->2->3)부터 문자를 삽입하므로
        // 만든 문자열의 길이가 N인 경우는 가장 작은 숫자를 의미하므로 바로 끝내기 위해서 true 리턴
        if(sb.length() == N)
            return true;

        for(char ch : arr){
            // 이전에 추가한 문자는 추가하지 않기
            if(ch == prev)
                continue;
            
            // 문자를 추가하고
            sb.append(ch);
            // 임의의 길이의 인접한 두 개의 부분 수열이 동일하면 
            if(!check(sb.toString())) {
                // 빼고 다른 문자를 추가하기
                sb.setLength(sb.length()-1);
                continue;
            }

            // 임의의 길이의 인접한 두 개의 부분 수열이 존재하지 않은 경우
            if(solution(ch))
                return true;    // solution이 true이면 더 이상 진행하지 않고 끝내기

            sb.setLength(sb.length()-1);
        }
        // 만들 수 없는 경우 false 리턴
        return false;
    }

    // 임의의 길이를 잡고 인접한 부분 문자열 비교
    public static boolean check(String str){
        int length = str.length();
        int loop = length / 2;
        int start = length - 1;
        int end = length;

        for(int i=1; i<=loop; i++){
            String left = str.substring(start-i, end-i);
            String right = str.substring(start, end);
            if(left.equals(right))
                return false;
            start--;
        }
        return true;
    }
}
```

## 문제 풀이
완전탐색으로 풀면 됩니다!

백트랙킹은 `임의의 길이의 인접한 두 개의 부분 수열이 동일`한 경우
   - 방금 추가한 문자말고 다른 문자를 추가합니다.
   - 즉, 재귀호출의 깊이를 하나 증가시키지 않는 것입니다.

그리고 1 -> 2 -> 3 와 같이 작은 숫자부터 추가하므로 길이가 같으면서 

두 개의 부분 수열이 없이 길이가 N인 숫자를 만들었다는 것은

가장 작은 숫자임을 뜻합니다.

그래서 길이가 N인 숫자를 만들었다면 다른 것은 찾아보지 않고 바로 종료시키기 위해 true를 리턴하면 됩니다.

이제 `임의의 길이의 인접한 두 개의 부분 수열`을 찾는 방법을 알아야 합니다.
   - 임의의 길이를 가지며 인접한 두 개의 부분 수열이라는 것은 
   - 길이가 같으면서 인접한 동일한 부분 수열을 의미합니다.
   - 그리고 길이가 같으면서 인접한 동일한 부분 수열의 최대 길이는 N/2밖에 되지 않습니다.
   - 왜냐하면 N의 길이를 가지는 수열에서 N/2를 중심으로 왼쪽과 오른쪽 부분 수열만 비교하면 되기 때문입니다.
   - 예를 들어, `12`가 만들어지면 길이가 1인 `1`과 `2`를 비교합니다.
   - `121`인 경우는 이미 `12`를 비교했으니깐 방금 추가한 `1`과 이전에 추가된 `2`를 검사하면 됩니다.
   - 즉, 방금 추가한 문자에서 길이를 1, 2, 3, ... , N/2로 늘려가면서 비교하고 같으면 false를 리턴해서 방금 추가한 문자열을 지우도록 합니다.
   - true를 리턴하면 방금 추가한 문자열을 추가해도 문제가 없음을 의미합니다.