# [15652] N과 M(4)

## 분류
> 백트랙킹

## 코드
package bkj_15652_N과M_4; // 210331

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준 15652 : N과 M (4) - 중복순열_비내림차순
	static int N, M;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		result = new int[M];
		
		permutationRepetNondecs(0, 1);
		
		System.out.println(sb.toString());
	}
	
	static void permutationRepetNondecs(int cnt, int start) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= N; i++) {
			result[cnt] = i;
			
			permutationRepetNondecs(cnt+1, i);
		}
	}
}


## 문제풀이
- 수열인데 살짝 변형된 문제고 크게 어렵지는 않습니다.
- N개 중에 M개를 고르는 것은 수열과 똑같고, 같은 수를 여러번 골라야한다는 것은 수열과 차이가 좀 있습니다.
- 재귀 돌릴 때, 시작하는 수를 변수로 넘겨주는데, 현재 내가 고른 수부터 시작되도록 넘겨주면 끝인 아주 간단한 소스입니다.