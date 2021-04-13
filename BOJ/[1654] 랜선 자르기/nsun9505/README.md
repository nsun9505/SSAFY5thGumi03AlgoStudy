# [1654] 랜선 자르기

## 분류
> 이분탐색
>
> 파라메트릭 서치

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        long N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        for(int i=0; i<K; i++)
            arr[i] = Integer.parseInt(br.readLine());

        // 랜선 최소 길이는 1
        long left = 1;
        // 랜선의 최대 길이는 Int 범위 맥스
        long right = Integer.MAX_VALUE;
        long answer = 0;

        // 이분 탐색
        while(left <= right){
            // mid 값 정하기 : mid 길이만큼 랜선을 자름
            long mid = (left + right) / 2;

            // mid 길이만큼 랜선을 자름.
            long cnt = 0;
            for(int i=0; i<K; i++)
                cnt += arr[i]/mid;

            // mid 길이만큼 잘랐을 때
            // mid 길이를 가지는 랜선의 개수 = cnt
            // cnt가 N보다 크거나 같다면 찾고자 하는 답
            if(cnt >= N){
                left = mid + 1;
                answer = Math.max(answer, mid);
            } 
            // cnt가 N보다 작다면 mid 길이를 줄여서 다시 잘라봐야 함.
            else {
                right = mid - 1;
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
K개의 랜선이 주어지고, N개 이상의 랜선을 가져가기 위해 랜선들을 임의의 M 길이만큼 잘라야 합니다.
   - 문제에서 N개보다 많이 만드는 것도 N개를 만드는 것에 포함한다고 했으니 N개 이상은 모두 답이 될 수 있다!!!

여기서도 파라메트릭 서치를 이용합니다.

랜선을 자르는 길이는 자연수이므로 이미 정렬이 되어 있죠!

그러니깐 이분탐색을 사용할 수 있다고 생각합니다.

그러면 랜선의 최소 길이를 1로 하고(0은 될 수가 없쥬..), 최대 길이는 입력으로 주어진 랜선 중에서 가장 긴 걸로 해도 되고 int 범위에서 가장 큰 값을 해도 됩니다.

그러면 1 ~ MAX 사이에서 mid를 구하고 mid만큼 잘랐을 때 mid 길이를 가지는 랜선의 개수를 카운트합니다.

카운트한 값이 N보다 작다면! mid가 크다는 것입니다.
   - A 나누기 B에서 A가 B보다 작으면 몫은 0이기 때문

mid가 크다면 mid를 줄이기 위해 right = mid - 1을 해줍니다.

만약 카운트한 값이 N보다 크거나 같다면, 일단 N개 이상의 랜선을 가질 수 있으므로 답으로 채택합니다.
   - 그리고 mid를 더 크게 만들기 위해 left = mid + 1을 해서 mid의 크기를 늘립니다.
   - 그리고 다시 left ~ right 범위에서 mid를 구하고, mid만큼 잘랐을 때 N 이상인지 보고 답을 갱신하는 식으로 탐색을 이어나가면 됩니다.

탐색을 이어나가다가 left와 right가 엇갈리면 더이상 탐색이 필요없으므로 답을 출력하고 종료하면 됩니다.