# [1806] 부분합

## 분류
> 투 포인터

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        int sum = arr[0];
        int answer = Integer.MAX_VALUE;

        while(end < N){
            if(sum >= S){
                int len = end - start + 1;
                answer = Math.min(answer, len);
                sum -= arr[start++];
            }

            else{
                if(++end < N)
                    sum += arr[end];
            }
        }

        int result = answer;
        if(result == Integer.MAX_VALUE)
            result = 0;
        sb.append(result);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
투 포인트를 사용해서 풀 수 있습니다!

시작부분에 start, end를 두어서 start ~ end 범위 안에 드는 것을 더한 것이 S 이상인지 체크하면 됩니다.

S 이상이 되는 것을 찾기 위해서 end와 start를 적절히 움직이면 됩니다.

start가 움직이는 경우는 start ~ end 범위 안에 있는 모든 수를 더했을 때 S이상일 때 start를 하나 증가시켜보는 겁니다.
   - start를 하나 증가시킨 후의 sum 값이 S 이상이라면 `start+1 ~ end`가 `start ~ end`보다 짧으니 우리가 찾는 답의 후보가 될 것입니다.
   
end가 움직이는 경우는 start ~ end 범위 안에 있는 모든 수를 더했을 때 S 미만일 떄 end를 하나 증가시켜서 sum 값에 값을 더하는 것입니다.
   - end를 증가시켜서 end가 위치한 값을 sum에 더했을 때 S 이상이라면 우리가 찾는 답의 후보가 될 수 있습니다.
   - end 위치에 있는 값을 더했음에도 sum이 S 미만인 경우는 계속 end를 증가시키면서 sum에 더해주면 됩니다.
   - 더하다가 S 이상인 sum 값을 만들 수 없다면 0을 출력하면 됩니다.