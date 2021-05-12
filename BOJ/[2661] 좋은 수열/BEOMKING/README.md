# [2661] 좋은 수열

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661_좋은수열 {
    static int N;
    static String result = "";
    static boolean end = false;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        process("");
        System.out.println(result);
    }
    public static void process(String str){
        if(end) return;

        if(str.length() == N) {
            result = str;
            end = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if(isavailable(str + i)){
                process(str + i);
            }
        }
    }

    public static boolean isavailable(String str){
        for (int i = 1; i <= str.length() / 2; i++) {
            int last = str.length() - i;
            if(str.substring(last, last + i).equals(str.substring(last - i, last))){
                return false;
            }
        }
        return true;
    }
}
```

## 문제 풀이
완전 탐색 문제입니다.

재귀를 이용합니다. 기저 조건은 현재 문자열의 길이가 N과 같아진다면 조건 하나를 충족하므로 end가 true로 바뀌게 되고 종료됩니다.

문자열이 N의 길이가 될 때까지 process 함수를 반복합니다.

for문을 통해 현재 자리값에 1, 2, 3 중 하나를 넣었을 때 그 값이 가능한지를 확인하는 isavailable 함수를 이용합니다.

가능하지 않다면 전 자리로 돌아가(현재 process 함수가 종료되고 그 전으로 돌아가기 때문) 다음  수를 넣고 1, 2, 3 모두 안된다면 그 전의 자리로 돌아가 다음 수를 시작하면서 최소 값을 찾을 것입니다. 그래서 최소값을 찾기 위한 조건을 따로 주지 않아도 됩니다.

isavailable 함수에서 i가 1에서 str / 2 만큼 반복하면서 수열을 확인합니다.

연속된 수열을 확인하는 마지막은 문자열 길이를 2로 나누고 그 두개를 비교하는 것이 마지막 확인이기 때문입니다.

Ex) 1 2 1 3 1 2 -> 1 2 1 // 3 1 2 두 부분을 비교하는 것이 마지막 비교