# [17609] 회문

## 분류
> 구현
>
> 문자열

## 코드
```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String str = br.readLine();

            // 회문인지 아닌지 체크
            int ans =  check(0, str, 0, str.length()-1);
            ans = ans > 2 ? 2 : ans;
            sb.append(ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int check(int cnt, String str, int start, int end){
        if(cnt >= 2)
            return 0;

        for(;start<end; start++, end--){
            if(str.charAt(start) == str.charAt(end))
                continue;
                // start 위치를 지우거나, end 위치를 지우거나
            else if(str.charAt(start+1) == str.charAt(end)
                || str.charAt(start) == str.charAt(end-1)){
                return Math.min(check(cnt+1, str, start+1, end),
                                check(cnt+1, str, start,end-1)) + 1;
            } else {
                return 2;
            }
        }
        return 0;
    }
}
```

## 문제 풀이
전체를 체크하면 아마 시간초과가 발생할 겁니다.

처음과 끝에 포인터를 각각 두고 검사를 합니다.
   - 처음을 start, 끝을 end라고 하겠습니다.

쉽게 결정할 수 있는 문제는 회문이거나 회문도 아니고 유사회문도 아닌 경우일 것입니다.
   - 회문인 경우에는 start와 end가 같아지거나 엇갈릴 때까지 아무런 문제없이 진행되어서 check()가 return 0을 하는 경우입니다.
   - 회문도 아니고 유사회문도 아닌 경우는 두 개의 문자를 지워야 하는 경우이므로 start+1과 end가 같지 않고 start와 end-1도 같지 않은 경우를 의미합니다.
      - start+1과 end가 같다는 것은 start가 가리키는 위치의 알파벳을 지운다는 것이고, start와 end-1이 같다는 것은 end가 가리키는 위치의 알파벳을 지우면 회문이 될 수 있다는 것을 의미합니다.
      - 하지만 start+1과 end가 같지 않고, start와 end-1이 같지 않다는 것은 일단 2개의 알파벳을 지워야 하므로 check()에서 바로 return 2를 해서 더이상 진행되지 않도록 하면 됩니다.

마지막으로는 검사하는 문제는 유사회문일 경우입니다.
   - start 위치의 알파벳을 지워서 회문을 만드는지, 아니면 end 위치의 알파벳을 지워서 회문을 만드는지 두 가지 경우를 모두 체크해야 합니다.
   - 왜냐하면 반례로 `XYXYAAYXY`를 보면 처음 X와 마지막 Y가 같지 않지만, 처음에 있는 X를 지울 경우에는 `YXYAAYXY`가 되기 때문에 유사회문이 됩니다!
   - 하지만, 뒤에 있는 `Y`를 지울 경우 `XYXYAAYX`가 되어서 유사회문도 아닌 문자열이 되어 버립니다.
   - 그러므로 start 위치를 지운 경우와 end 위치를 지운 경우에 대해서 각각 check 함수를 호출하면 되겠습니다. 

cnt가 2 이상인 경우에는 바로 return으로 함수를 종료하도록 합니다.
   - 왜냐하면 cnt가 2이상이라는 것은 start+1과 end가 같은 경우 또는 start외 end-1이 같은 경우에 check()가 호출되는데 이 호출 횟수가 2 이상이라면 유사회문도 될 수 없으므로 바로 return 해서 종료하면 됩니다.