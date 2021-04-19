# [3237] 두 수의 합

## 분류
> 투포인트

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(br.readLine());

        int i = 0;
        int j = N-1;
        Arrays.sort(arr);

        int answer = 0;
        while(i < j){
            int sum = arr[i] + arr[j];

            // i번쨰와 j번째가 같으면 두 수의 합이 X인 것을 찾음.
            // i는 하나 증가시키고, j는 하나 감소시킴.
            if(sum == X){
                i++;
                j--;
                answer++;
            }

            // sum보다 작으면 i를 하나 증가시켜서 좀 더 큰 값을 j번째에 더하기
            else if(sum < X){
                i++;
            }

            // sum보다 크면 j를 하나 줄여서 좀 더 작은 값을 i번째에 더하기
            else{
               j--;
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

```

## 문제 풀이
투 포인트로 쉽게 해결할 수 있습니다!

먼저 배열을 정렬합니다.
   - 순서를 지켜야하는 그런 제약이 없으므로 정렬해도 상관없습니다.

정렬된 배열에서 두 수의 합이 X인 경우를 찾으면 됩니다.

시작하는 인덱스를 0으로 잡고(= i), 마지막 인덱스를 N-1(=j)로 잡아봅니다

그러면 i번째와 j번쨰를 더했을 때 X라면 카운트를 하나 증가시키고 i는 1증가, j는 1감소 시키면 됩니다.

만약 i번째와 j번째를 더한 값이 X보다 크다면 j를 감소시킵니다.
   - 왜냐하면, j-1번째 값은 j번째 값보다 작습니다.
   - i번째와 j-1번쨰를 더한다면 i번째와 j번째를 더한 값보다는 작아지므로 X에 가까워질 수 있을 것입니다.
   - 이 때문에 배열을 정렬한 것입니다.

그리고 i번쨰와 j번째를 더한 값이 X보다 작다면 i를 증가시킵니다.
   - 왜냐하면, i+1번째 값은 i번째 값보다 큽니다.
   - i+1번째와 j번째를 더한다면 i번째와 j번째를 더한 값보다는 크므로 X에 가까워질 수 있을 것입니다.

이렇게 작냐 크냐에 따라 i를 증가시키거나, j를 감소시키면서 두 수의 합이 X가 되는 것을 찾으면 됩니다.

그리고 i와 j는 같아질 수 없으므로 i < j와 같이 while문 조건을 주어서 탈출하도록 하였습니다.