# [14891] 톱니바퀴

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
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
```

## 문제 풀이
톱니바퀴를 입력받는대로 저장하기 위해 [4+1][8]의 이차원 배열로 저장했습니다. (0인덱스 사용 x)

처음에는 left와 right를 할 때마다 실제로 배열을 돌려주었는데 나중에 생각해보니 계속 배열을 변경하면 left가 끝나고 right를 할 때 중간에 변경된 
톱니바퀴 때문에 제대로 된 값이 나오지 않아서 check[]배열에 시계:1,반시계:-1 인지 저장해주고 회전 횟수 한번이 끝날 때 마다 일괄적으로 돌려주었습니다.

[4+1][8]배열로 선언해서 0인덱스를 사용하지 않았는데 left와 right안에서 범위 검사할때 살짝 혼돈이 생기는 부분이 있었습니다. 인덱스를 바꿔주며 디버깅하면서 범위를 설정해줬습니다.

처음에는 아이디어를 생각할때는 톱니바퀴가 4개라서 그냥 모든 케이스를 다 때려넣어서? 하드코딩할까도 생각했지만 .. 그냥 역시나 반복해서 호출하는게 편한거같습니다.