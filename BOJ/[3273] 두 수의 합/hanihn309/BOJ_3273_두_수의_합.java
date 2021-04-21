import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두_수의_합 { // 210421
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int[] array = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(array);
		
		int left = 0, right = n-1;
		int count = 0; // 조건을 만족하는 쌍의 개수
		
		while(left < right) { // left가 right보다 작은 동안만 돌기
			int result = array[left] + array[right]; // 양 끝값을 더해보기
			 
			if (result < x) // 더한 값이 찾고자하는 x값보다 작으면 left 값을 늘려주기
				left++;
			else if (result > x) // 더한 값이 찾고자하는 x값보다 크면 right 값을 줄여주기
				right--;
			else { // 더한 값이 찾고자하는 x값과 같으면 count 늘려주고, 다른 쌍이 더 있을수도 있으니 left, right 값을 조정
				count++;
				left++;
				right--;
			}
		}
		
		System.out.println(count);
	}
}