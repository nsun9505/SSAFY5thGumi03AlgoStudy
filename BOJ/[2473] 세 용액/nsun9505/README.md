# [2473] 세 용액

## 분류
> 투 포인터

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] arr;
    static long[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        long answer = Long.MAX_VALUE;
        ans = new long[3];

        // i위치 오른쪽에 있는 용액 2개를 잡아서 답을 찾음
        for(int i=0; i<N; i++){
            int left = i + 1;
            int right = N - 1;

            while(left < right){
                long sum = arr[i] + arr[left] + arr[right];
                if(Math.abs(sum) <= answer){
                    answer = Math.abs(sum);
                    ans[0] = arr[i];
                    ans[1] = arr[left];
                    ans[2] = arr[right];
                }

                if(sum > 0)
                    right--;
                else
                    left++;
            }
        }

        sb.append(ans[0] + " " + ans[1] + " " + ans[2]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
세 용액이므로 투 포인트 이외에 하나의 포인터가 더 필요합니다.

그래서 left, right를 이외의 포인트를 i로 두었습니다.

i번째 용액과 i번째 왼쪽에 있는 용액들에서 left, right를 뽑아서 3개를 더한 값이 0에 가까워진다면 답으로 채택하면 됩니다.

그리고 3개를 더한 값이 0보다 크다면 두 용액과 같이 right를 줄이고, 0보다 작거나 같다면 left를 증가시키면 됩니다.

만약 left ~ right를 잡고 이 사이에서 i를 찾는다면 절대 답이 안 나옵니다.
   - 문제가 모든 경우의 수를 다 해봐야하는데
   - left, right 사이에서 i를 움직인다면 모든 경우의 수를 해보는 것이 아니므로 문제에 대해서 정답을 맞을 수 없는 것 같습니다.