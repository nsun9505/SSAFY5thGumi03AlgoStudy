import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int res;
	static String S,P;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		P = br.readLine();
		res = 0;
		
		KMP();
		
		System.out.println(res);
	}
	
	private static void KMP() {

		int[] arr = makeTable(P);
		
		int Slength = S.length();
		int Plength = P.length();
		
		int idx = 0;
		for(int i=0;i<Slength;i++) {
			while(idx>0 && S.charAt(i)!=P.charAt(idx)) {
				idx=arr[idx-1];
			}
			
			if(S.charAt(i)==P.charAt(idx)) {
				if(idx == Plength-1) {	//길이가 P길이만큼 된경우가 나왔다면
					idx=arr[idx];
					res=1;
					break;
				}
				else {
					idx++;
				}
			}
		}
	}
	
	private static int[] makeTable(String P) {
		int n = P.length();
		//찾아야될 문자열 만큼 배열 길이 만듦
		int[] arr = new int[n];
		
		int idx = 0;
		for(int i=1;i<n;i++) {
			//일치하는 문자가 발생했을 때(idx>0)
			while(idx>0 && P.charAt(i) != P.charAt(idx)) {
				//연속적으로 더 일치하지 않으면 직전의 
				idx = arr[idx-1];
			}
			if(P.charAt(i)==P.charAt(idx)) {
				//문자열이 일치할 때 +1 해준 값을 배열에 넣어준다
				idx++;
				arr[i]=idx;
			}
		}
		return arr;
	}
}