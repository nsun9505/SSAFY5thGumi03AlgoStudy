# [2508] 나무 자르기

## 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805_나무자르기 {
    static int N, M, tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);
        int start = 0;
        int end = tree[N - 1]; // 나무의 최대 높이
        int answer = 0; // 최대 높이
        long now = Integer.MAX_VALUE; // 현재 수집나무

        while (start <= end){
            int mid = (start + end) / 2;
            long dok2 = 0;
            for (int i = 0; i < N; i++) { // 나무양
                if(tree[i] > mid) dok2 += (tree[i] - mid);
            }
            if(dok2 == M) { // 원하는 양에 딱 맞으면
                answer = mid;
                break;
            }
            if(M < dok2 && dok2 < now && mid > answer) { // 원하는 양보다 크고 현재 가진 양보다 적고
                now = dok2;
                answer = mid;
            }
            if(M < dok2){ // 원하는 양보다 많다면
                start = mid + 1; // 높이를 높혀서 양을 줄인다
            }else{ // 원하는 양보다 적다면
                end = mid - 1; // 높이를 낮춰 양을 늘린다.
            }
        }
        System.out.print(answer);
    }
}
```

## 문제풀이

이분탐색 문제입니다.

나무의 최대 높이(end)와 최소 높이(start)를 시작점으로 이분 탐색을 수행합니다.

- 중간 값을 구해서 for문으로 나무의 양을 구합니다.

- 구한 나무의 양이 원하는 양이라면 현재 높이를 저장하고 종료합니다.

- 적어도 M의 양 이상을 구해야하기 때문에 M 이하일 경우는 넘어가고 지금까지 구한 양의 최소값보다 적고 M 이상이라면 현재 높이와 나무의 양을 저장합니다.

- 4 2000000000
  1000000000 999999995 1000000000 999999995 의 경우 이분탐색을 진행하다보면 mid값이

  250000000이 되는 경우 나무의 양이 21억이 넘어가기 때문에 정확한 값이 측정이 안됩니다. 

  그래서 long 형을 사용해주어야합니다.