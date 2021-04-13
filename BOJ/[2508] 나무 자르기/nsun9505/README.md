# [2508] 나무 자르기

## 분류
> 이분탐색

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 2000000000;
        int answer = 0;
        while(left <= right){
            int mid = (left + right) / 2;

            // 잘라보기
            long remain = 0;
            for(int i=0; i<N; i++) {
                if(arr[i] - mid > 0)
                    remain += (arr[i] - mid);
            }

            // remioan < M : 자르는 크기가 너무 커서 잘리지 않음.
            if(remain < M) {
                right = mid - 1;
            }
            // remain >= M : 자르는 크기가 작아서 나무를 자른 후의 길이들의 합이 M을 넘어서는 경우
            else{
                left = mid + 1;
                answer = Math.max(mid, answer);
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
이분탐색을 사용한 파라메트릭 서치입니다.

이분탐색을 사용하려면 일단 정렬이 되어 있어야 합니다.

나무를 자르기 위한 절단기의 높이들은 자연수이므로 이미 정렬이 되어 있습니다.

그래서 절단기 높이를 정하고, 잘랐을 때! 얼마가 남는지를 보는 것입니다.

높이를 정하고 주어진 나무를 모두 잘라봅니다.

만약 절단기 높이가 나무들의 길이보다 높다면 잘리는게 없을 것입니다.

이 말은 절단기 높이가 너무 크다! 이므로 right를 줄어주면 됩니다.

그러면 절단기 높이만큼 나무들을 잘랐을 때 잘린 나무의 길이의 합이 M보다 크거나 같은 경우일 것입니다.

문제에서 우리는 적어도 M미터의 나무가 필요하므로 자른 후의 나머지 나무의 길이를 모두 합한 값이 M 이상이 되도록 하는 절단기 높이를 찾으면 됩니다!

주의할 점은 자른 후의 남은 나무의 길이의 합이 int 범위를 넘어설 수 있습니다.
   - N이 최대 백만이고, 주어진 나무의 높이가 10억이라고 할 때
   - M이 2억이라고 해보겠습니다.
   - 높이가 10억인 나무가 백만개가 있고 그거를 어떤 높이만큼 잘랐을 때 int 범위를 벗어날지 안 벗어날지를 계산해보시면 되겠습니다.
   - 예를 들어 절단기의 높이가 5억이라면 10억에서 5억을 빼면 5억이고 이 5억이 백만개가 있으므로 int 범위를 무조건 넘어서게 될 것입니다.