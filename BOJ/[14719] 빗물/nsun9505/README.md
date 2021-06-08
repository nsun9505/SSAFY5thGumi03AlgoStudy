# [14719] 빗물

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    public static int[] heights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        heights = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++)
            heights[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        for(int i=0; i<W; i++){
            // 왼쪽에서 현재 높이(heights[i])보다 가장 큰 것의 위치 찾기
            int left = findLeftMax(i, heights[i]);

            // 오른쪽에서 현재 높이(heights[i])보다 가장 큰 것의 위치 찾기
            int right = findRightMax(i, heights[i]);

            // 둘 중 하나라도 찾을 수 없다면 채울 수 없음.
            if(left == -1 || right == -1)
                continue;

            // 둘 중 작은 것을 선택
            int min = Math.min(heights[left], heights[right]);
            // 작은 것과의 차이를 구해서 answer에 더함.
            int diff = Math.abs(min - heights[i]);
            answer += diff;
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findLeftMax(int startIndex, int max){
        int findIndex = -1;
        for(int i=startIndex-1; i>=0; i--){
            if(max < heights[i]){
                findIndex = i;
                max = heights[i];
            }
        }
        return findIndex;
    }

    public static int findRightMax(int startIndex, int max){
        int findIndex = -1;
        for(int i=startIndex+1; i<W; i++){
            if(max < heights[i]){
                findIndex = i;
                max = heights[i];
            }
        }
        return findIndex;
    }
}
```

## 문제풀이
웅덩이의 전체적으로 물을 채우는 방법보다는 각각의 높이에서 채울 수 있는 높이를 구하는 것이 쉽다고 생각했습니다.

그래서 각 위치(i)의 높이를 기준으로 왼쪽에서 가장 큰 것과 오른쪽에서 가장 큰 것을 찾습니다.

만약 그런 위치가 왼쪽 이나 오른쪽 한 곳에서라도 없으면 채울 수 없는 것이므로 다음 위치로 넘어갑니다.

현재 위치의 높이를 기준으로 왼쪽에서 가장 높은 것과 오른쪽에서 가장 높은 것을 찾았다면 둘 중 작은 것을 선택합니다.

왜냐하면 둘 중에 큰 것을 선택하면 물이 넘치게 됩니다.

둘 중에 높이가 낮은 것과 현재 위치(i)의 높이를 뺀 것이 현재 위치에 채울 수 있는 물의 양이 됩니다.

양을 구했으니 출력할 정답에 출력해주면 됩니다.