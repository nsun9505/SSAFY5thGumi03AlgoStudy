import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		good("");
	}

	private static void good(String res) {

		if(res.length() == N) {	//종료조건
			System.out.println(res);	//출력하고 종료
			System.exit(0);
		}		
		else {
			for(int i=1;i<=3;i++) {	//1, 2, 3 하나씩 넣어보면서 가능한 수열인지 확인
				if(check(res+i)) {
					good(res+i);
				}
			}
		}
	}

	private static boolean check(String str) {
		int leng = str.length()/2;	//최대 확인은 길이의 반만 확인하면 됨 (ex : 12/13 )
		
		for(int i=1;i<=leng;i++) {	//길이의 절반까지 확인해줌
			int fin = str.length()-i;	//뒤에서부터 확인 -> 수열이 하나씩 늘어갈 때 항상 맨 뒤의 숫자가 변경되기 때문 
			if(str.substring(fin-i, fin).equals(str.substring(fin, fin+i)))
				return false;
		}
		return true;
	}
}