import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{	//톱니바퀴
	static int[][] arr;
	static int[] check;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new int[4+1][8];	//0번째 사용 x 	
		
		for(int i=1;i<5;i++) {
			String str =br.readLine();
			for(int c=0;c<8;c++) {
				arr[i][c]=str.charAt(c)-'0';
			}
		}
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0;t<tc;t++) {
			check = new int[4+1];
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());		//톱니바퀴번호
			int dir = Integer.parseInt(st.nextToken());		//1:시계, -1:반시계
			check[num]=dir;
			
			left(num,dir);
			right(num,dir);
			
			for(int i=1;i<5;i++) {
				if(check[i]!=0) {
					dial(i,check[i]);
				}
			}
		}
		
		int res = 0;
		
		if(arr[1][0] ==1) {
			res+=1;
		}
		if(arr[2][0] ==1) {
			res+=2;
		}
		if(arr[3][0] ==1) {
			res+=4;
		}
		if(arr[4][0] ==1) {
			res+=8;
		}
		System.out.println(res);
	}

	private static void left(int num, int dir) {
		if(num-1<1) {	//범위
			return;
		}
		if(arr[num-1][2] != arr[num][6]) {
			int go = 0;
			if(dir==1) {
				go=(-1);
			}
			else
				go=1;
			check[num-1]=go;
			left(num-1,go);
		}		
	}

	private static void right(int num, int dir) {
		if(num+1>4) {
			return;
		}
		if(arr[num+1][6]!=arr[num][2]) {
			int go = 0;
			if(dir==1) {
				go=-1;
			}
			else
				go=1;
			check[num+1]=go;
			right(num+1,go);
		}	
	}

	private static void dial(int num, int dir) {
		if(dir==1) {	//시계
			int tmp = arr[num][7];
			for(int i=7;i>0;i--) {
				arr[num][i]=arr[num][i-1];
			}
			arr[num][0]=tmp;
		}
		else {			//반시계
			int tmp = arr[num][0];
			for(int i=1;i<8;i++) {
				arr[num][i-1]=arr[num][i];
			}
			arr[num][7]=tmp;
		}
	}
}















