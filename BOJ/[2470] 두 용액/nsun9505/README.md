# [2470] 두 용액

## 분류
> 이분탐색
>
> 투포인트

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int ans[] = new int[2];
    static int base = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        ans[0] = arr[0];
        ans[1] = arr[N-1];
        base = ans[0] + ans[1];

        for(int i=0; i<N; i++){
            int left = i+1;
            int right = N-1;

            while(left <= right){
                int mid = (left + right) / 2;

                int sum = arr[i] + arr[mid];
                if(Math.abs(sum) < Math.abs(base)){
                    ans[0] = arr[i];
                    ans[1] = arr[mid];
                    base = sum;
                }

                // 배열은 정렬되어 있으므로,
                // i번째 수와 mid번째에 있는 수를 더했을 때
                // 합이 0보다 크다는 것은 right 위치를 줄여서 더하면 0에 가까워질 수 있음을 의미
                if(sum >= 0)
                    right = mid - 1;
                
                // i번째 수와 mid번째에 있는 수를 더했을 때
                // 합이 0보다 작거나 같은 것은 left 위치를 크게해서 더하면 0에 가까워질 수 있음을 의미
                else
                    left = mid+1;
            }
        }

        Arrays.sort(ans);
        sb.append(ans[0] + " " + ans[1]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
투포인터로도 풀 수 있고! 이분탐색으로도 풀 수 있습니다!

이분탐색으로 풀면 먼저 정렬되어 있어야 하므로 정렬을 합니다.

그리고 i번째 수랑 i+1 ~ N 사이의 수 중에서 i랑 더했을 때 0에 가까워지는 수를 찾습니다.

i+1 ~ N 사이의 수 중에서 i번째 수랑 더 했을 때의 값이 sum이라고 하겠습니다.

sum이 0보다 크다는 것은 right를 mid-1로 줄이면 right위치에 있는 있는 값보다 작은 값을 mid가 가리키고 있습니다.

그래서 mid의 위치를 낮추고! 그 위치 값이랑 다시 i번째 수랑 더해서 sum이 0보다 큰지 아니면 작은지에 따라 left와 right를 움직이면 됩니다.