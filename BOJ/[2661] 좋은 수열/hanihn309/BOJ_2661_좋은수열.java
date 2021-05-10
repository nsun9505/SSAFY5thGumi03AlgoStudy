import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661_좋은수열 { // 210510
	static int N;
	static boolean findResult = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		backTracking("1");
	}
	
	private static boolean isPromosing(String str) {
		int size = str.length();
		
		for(int i = 1; i <= size / 2; i++) { // 절반까지만 확인하면 됨
			String A = str.substring(size - i); // 끝에서부터 길이 1~size까지의 부분 수열
			String B = str.substring(size - i - i, size - i); // A와 같은 길이부터 A 시작 앞까지의 부분 수열
			
			if(A.equals(B)) // 동일한 부분 수열이 있다면 나쁜 수열
				return false;
		}
		return true;
	}
	
	private static void backTracking(String sequence) {
		if(sequence.length() == N) {
			System.out.println(sequence);
			System.exit(0); // 강제 종료
		}
		
		for(int i = 1; i <= 3; i++) { // 수열은 1,2,3으로 이루어져있음
			if(isPromosing(sequence+i)) { // 현재 수열에 i 수를 붙였을 때 좋은 수열이라면
				backTracking(sequence+i);
			}
		}
	}
}