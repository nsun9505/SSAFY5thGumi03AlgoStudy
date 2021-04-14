# [1920] 수찾기

## 분류
> 이분탐색

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
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

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<M; i++){
            int findNumber = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(arr, findNumber)+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = (left + right) / 2;

            if(arr[mid] == target)
                return 1;

            if(arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return 0;
    }
}
```

## 문제 풀이
이분탐색을 사용해서 풀었습니다.

왜냐하면 입력으로 주어진 N 개의 숫자에서 M개의 데이터를 찾기 위해서는 O(N^2)입니다.

N이 100,000까지 주어지므로 시간 복잡도를 만족하지 못합니다.

이분탐색을 사용한다면 O(NlogN)에 가능합니다.

이분탐색을 하기 위해 입력으로 주어진 `N개의 데이터를 정렬`하고, M개의 데이터를 찾기 위해 이분 탐색의 target 값으로 주면 됩니다.

찾으면 1을 바로 리턴하고, 찾기 못하고 left와 right가 엇갈린다면 0을 리턴하면 됩니다.