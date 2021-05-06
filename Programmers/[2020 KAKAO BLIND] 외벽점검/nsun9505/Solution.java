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