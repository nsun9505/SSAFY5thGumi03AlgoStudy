package 이분탐색;

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
