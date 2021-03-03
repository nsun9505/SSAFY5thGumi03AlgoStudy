```
# [5430] AC - JAVA

## 분류
> 구현

## 코드
​```java
import java.io.*;
import java.util.*;

public class 백준_5430_AC {
    static char function[];
    static Deque<String> array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb; // string bulider 사용으로 출력 시간 감소
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            sb = new StringBuilder();
            String func =br.readLine();
            function = func.toCharArray(); // 함수 배열
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            if(n == 0){ // 빈 배열
                if(func.contains("D")) {
                    System.out.println("error");
                }else{
                    System.out.println("[]");
                }
                continue;
            }
            str = str.substring(1, str.length() - 1); // 파싱
            array = new LinkedList<>();
            st = new StringTokenizer(str, ",");
            for (int i = 0; i < n; i++) {
                array.add(st.nextToken()); // 숫자로 변환 필요 x 메모리 감소
            }

            int R = 0; // 뒤집힌 횟수
            boolean error = false; // 에러 확인
            for (char f: function) {
                if(f == 'R'){
                    R += 1;
                }else{
                    if(array.size() == 0) {
                        System.out.println("error");
                        error = true;
                        break;
                    }
                    if(R % 2 == 0){ // 정방향
                        array.pollFirst();
                    }else{ // 역방향
                        array.pollLast();
                    }
                }
            }

            if(!error) {
                sb.append("[");
                int len = array.size();
                if(R % 2 == 0) {
                    for (int i = 0; i < len; i++) {
                        if (i == len - 1) {
                            sb.append(array.pollFirst());
                        } else {
                            sb.append(array.pollFirst());
                            sb.append(",");
                        }
                    }
                }else{
                    for (int i = len; i > 0; i--) {
                        if (i == 1) {
                            sb.append(array.pollLast());
                        } else {
                            sb.append(array.pollLast());
                            sb.append(",");
                        }
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}
​```

## 문제 풀이
입력 값 파싱, 시간 최소화가 중요했습니다.
입력 값은 substring을 이용해 첫 문자와 마지막 문자를 제거시켰고 ','를 구분자로 사용해 입력 받았습니다.
n이 0이면 빈 배열을 입력 받기 때문에 error를 출력하면 된다고 생각할 수 있지만 빈 배열을 입력 받아도 함수가 R만 받는다면 [] 그대로 출력해야한다는 경우도 있었습니다.
역정렬을 반복하지 않기 위해 R의 개수를 세서 짝수이면 정방향 첫 번째 값 제거, 홀수이면 역방향 마지막 값을 제거하기 위해 Deque을 사용했습니다.
이러면 2000대의 시간으로 통과할 수 있는데 string builder를 사용하면 시간을 더 줄일 수 있습니다.

## 궁금한 점
처음에 큐나 덱을 정렬을 해보려했는데 새로운 큐, 덱을 생성하지 않고 정렬하는 방법이 있을까?
```