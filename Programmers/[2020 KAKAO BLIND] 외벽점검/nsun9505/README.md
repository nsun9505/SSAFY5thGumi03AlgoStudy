# [2020 KAKAO BLIND] 외벽 점검

## 분류
> 구현
>
> 순열

## 코드
```java
import java.util.Arrays;

public class Solution {
    static int N;
    static int[] weaks;
    static int[] tmp;
    static int ans = 10;
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        weaks = weak;
        tmp = Arrays.copyOf(weak, weak.length);
        permutation(0, dist);
        if(ans == 10)
            return -1;
        return ans;
    }

    public static void permutation(int index, int[] dist){
        if(index == dist.length){
            int result = check(dist);
            if(result == -1)
                return;
            ans = Math.min(ans, result);
            return;
        }

        for(int i=index; i<dist.length; i++){
            swap(dist, i, index);
            permutation(index+1, dist);
            swap(dist, i, index);
        }
    }

    public static int check(int[] dist) {
        int result = dist.length+1;

        for (int start = 0; start < weaks.length; start++) {
            for(int i=start; i<weaks.length; i++)
                tmp[i] = weaks[i];
            for (int i = 0; i < start; i++)
                tmp[i] = weaks[i] + N;

            int cnt = 0;
            int index = start;
            for (int idx = 0; idx < dist.length; idx++) {
                int cur = tmp[index] + dist[idx];
                cnt++;
                index = (index + 1) % tmp.length;
                while(index != start){
                    if(tmp[index] > cur)
                        break;
                    index = (index + 1) % tmp.length;
                }

                if(index == start)
                    break;
            }
            if(index == start)
                result = Math.min(cnt, result);
        }
        if(result == dist.length+1)
            return -1;
        return result;
    }

    public static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
```

## 문제 풀이
외벽을 점검하는 친구가 어느 위치에서 시작하느냐에 따라 필요한 친구의 수가 달라집니다.

그래서 이 친구들의 순서를 가지고 취약 지점을 하나씩 출발지점으로 선택해서 순서대로 돌려봅니다.

만약 친구들의 어떤 순서를 통해 어떤 취약 지점에서 점검을 시작했을 때 친구를 사용한 횟수가 최소가 된다면 그게 답이 됩니다.

친구들의 순서를 알아야 했기 때문에 순열을 사용했습니다.

dist의 길이가 최대 8밖에 되지 않기 때문에 충분하다고 생각했습니다.

그리고 하나의 순열이 주어지면 각 취약 지점을 시작위치로 하여 점검을 돌려보면 됩니다.

n이 12이고, 취약 지점이 {1, 5, 6}이라면 1에서도 시작해보고, 5에서도 시작해보는 방식입니다.

그러면 5에서 시계방향으로 시작한다면 1은 5입장에서는 13입니다. 왜냐하면 12다음에 1이고 5의 입장에서는 12를 지나야 1이 나오기 때문입니다.

만약에 취약 지점이 3에도 있었다면, 5입장에서는 15입니다! 왜냐하면 12를 지나서 3만큼 가야 3이 나오기 때문에 15로 볼 수 있습니다.

그러면 시작하는 위치 뒤에 있는 원소들은 N을 더해줘서 시작 위치에 따라 취약지점의 위치를 변경해줘서 몇 명의 친구를 사용하는지 확인하면 됩니다.

그리고 그 중에서 가장 적은 친구 수를 사용한 경우가 답이 됩니다.