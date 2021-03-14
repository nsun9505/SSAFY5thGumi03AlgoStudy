```
# [17609] 회문 - JAVA

## 분류
> 구현

## 코드
​```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_17609_회문 {
    static char str[];
    static int len;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            str = br.readLine().toCharArray();
            len = str.length;
            int start = 0;
            int end = len - 1;
            int count = 0;
            
            int result = palindrome(start, end, count);
            sb.append(result + "\n");

        }
        System.out.println(sb.toString().trim());
    }
    
    static int palindrome(int start, int end, int count){ // 시작 값, 끝 값, 삭제 횟수
        while(start <= end){ // 시작 값이 끝 값보다 커지면 종료
            if(str[start] == str[end]){
                start += 1;
                end -= 1;
            }else if(str[start] == str[end - 1] && str[start + 1] == str[end]){ // 시작 값을 지워도 다음이 대칭이고 끝 값을 지워도 다음이 대칭이 되는 경우
                count += 1;
                if(palindrome(start + 1, end - 2, count) < 2 || palindrome(start + 2, end - 1, count) < 2){ // 둘 다 해봐서 하나라도 된다면
                    return 1; // 이미 하나는 삭제해야하므로 0은 불가능
                }else{
                    return 2;
                }
            }else if(str[start] == str[end - 1]){ // 끝 값을 지웠을 때 대칭이라면
                start += 1;
                end -= 2;
                count += 1;
            }else if(str[start + 1] == str[end]){ // 시작 값을 지웠을 때 대칭이라면
                start += 2;
                end -= 1;
                count += 1;
            }else{
                count += 2;
            }
            if(count >= 2) return 2;
        }
        return count;
    }
}
​```

## 문제 풀이
중요한 부분
- 양 끝값에서 시작하는 것
- wtwccwt처럼 시작 값을 지워 start + 1, end가 대칭이 되고 끝 값을 지워 start, end - 1이 대칭이 되는 경우를 해결하는 것

대칭이 잘 되거나 시작 값이나 끝 값 하나만 지워 대칭이 되는 경우는 count 값을 계산해 그대로 리턴해주면 됬으나 시작 값을 지워도 다음이 대칭, 끝 값을 지워도 다음이 대칭이 될 때 모든 경우를 다 진행해봐야합니다.
두 경우 중 하나라도 회문이 될 경우 이미 count는 1이므로 1을 리턴해주면 해결 되는 문제입니다.
```