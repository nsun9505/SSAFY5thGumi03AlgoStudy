# [1450] 냅색문제

## 분류
> 중간에서 만나기
>
> 투 포인트
>
> 이분 탐색

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, C, half;
    static long sub1[], sub2[];
    static ArrayList<Long> group1 = new ArrayList<>();
    static ArrayList<Long> group2 = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        half = N/2;
        sub1 = new long[half];
        sub2 = new long[N-half];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N/2; i++)
            sub1[i] = Integer.parseInt(st.nextToken());
        for(int i=0;i<N-half; i++)
            sub2[i] = Integer.parseInt(st.nextToken());

        calcSubSet(sub1, group1);
        calcSubSet(sub2, group2);
        Collections.sort(group2);

        int answer = 0;
        for(long num : group1){
            answer += (upperBound(group2, C - num));
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void calcSubSet(long[] arr, ArrayList<Long> group){
        for(int i=0; i<(1<<arr.length); i++){
            long sum = 0;
            for(int j=0; j<arr.length; j++){
                if((i & (1 << j)) > 0){
                    sum += arr[j];
                }
            }

            if(sum <= C)
                group.add(sum);
        }
    }

    public static int upperBound(ArrayList<Long> list, long target){
        int left = 0;
        int right = list.size();

        while(left < right){
            int mid = (left + right) / 2;

            if(list.get(mid) > target)
                right = mid;
            else
                left = mid + 1;
        }

        return right;
    }
}
```

## 문제풀이
투 포인트를 쓰는게 맞긴 한데..

투 포인트 중에서는 난이도가 있는 문제가 아닐까 싶습니다.

이 문제가 막힌다면 풀기 전에 `meet in the middle`를 공부해보시고 풀어보는 것을 추천합니다.

이 문제는 부분집합을 다 찾아야하는 문제입니다.

그런데 N이 30이니 2^30 시간 복잡도를 가지므로 1초 안에 해결하지 못합니다.

그러면 어떻게 하느냐!

반으로 쪼갭니다!

최악의 경우 N이 30일 때 반으로 쪼개면 2^15 + 2^15니깐 충분히 풀 수 있습니다.

그러면 입력 받은 숫자를 배열에 담을 떄는 N/2로 나눠서 담습니다.

나눠서 담은 배열의 부분집합을 구합니다.
   - 부분집합들을 구하면서 부분집합의 합을 더해서 C이하 인것만 담습니다.
   - C 초과인 것은 이미 답에 포함이 되지 않기 때문입니다.

부분집합을 구했으니 이제 두 부분집합들의 합을 각각 더해봐서 C 이하인 개수를 알아내면 됩니다.

그러면 group1(0~N/2 부분집합의 합) 각 원소에 group2의 각원소랑 더해보면서 C이하인 값을 찾으면 (N/2)^2이므로 N^입니다..

이를 해결하기 위해 `이분탐색`을 사용합니다.

group2를 정렬하고, group1의 각 원소를 C에서 뺀(C - group1.get(i)) 값을 group2에서 upper bound로 해서 C - group1.get(i) 이하인 수가 몇개인지 찾아볼 수 있습니다.

upper bound로 찾은 개수를 정답에 계속 더해주면 이것이 문제에서 원하는 답이 됩니다.

