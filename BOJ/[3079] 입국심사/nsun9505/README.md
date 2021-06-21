# [3079] 입국심사

## 분류

> 이분탐색

## 코드

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N;
        long M;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        long maxSec = 0;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            maxSec = Math.max(arr[i], maxSec);
        }

        long left = 0;
        long right = maxSec * M;
        long sec = Long.MAX_VALUE;
        while(left <= right){
            long mid = (left + right) / 2;

            long cnt = 0;
            for(int i=0; i<N; i++)
                cnt += (mid / arr[i]);

            if(M <= cnt) {
                right = mid - 1;
                if(mid < sec)
                    sec = mid;
            }
            else
                left = mid + 1;
        }
        sb.append(sec);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이

이분탐색을 사용해서 풀었습니다.

우리가 알고자 하는 것은 M명의 친구가 N개의 심사대를 거쳐서 심사가 끝나는 시간을 최소로 하는 것입니다.

그렇다면 어떤 시간 K가 있다고하면 각 심사대에서 K 시간안에 처리할 수 있는 사람들을 알아낼 수 있습니다.

그러면 K 시간동안 각 심사대가 처리할 수 있는 사람들의 수를 모두 더합니다.

- 주어진 시간(K) / (각 심사대에서 걸리는 시간)

모두 더한 값을 cnt라고 하면 cnt는 K 시간동안 처리할 수 있는 사람의 수가 됩니다.

여기서 이분탐색을 쓰면 됩니다.

시간을 기준으로 이분탐색을 돌립니다. 시간의 최댓값을 잡고, left와 right를 초기화한 후에 이분탐색을 돌립니다.

주어진 M보다 cnt가 크다면 해당 K시간 동안 M명을 충분히 처리할 수 있으므로 right 값을 내립니다.

하지만 M보다 cnt가 작다면 K 시간 동안 M명보다 적게 처리하므로 left 값을 올려서 cnt가 M과 같거나 크도록 만들어야 합니다.

답은 cnt가 M보다 크거나 같을 때 K값이 정답이 되므로 정답과 K 중 더 작은 것을 선택해서 갱신한 뒤에 이분탐색이 끝나면 답을 출력하면 됩니다.

right를 `(심사대 중 가장 오래걸리는 시간) * (친구들의 수)`로 설정한 이유는 가장 오래걸리는 심사대로 모든 친구들이 갔을 떄가 가장 오래 걸리는 시간이기 때문입니다.
