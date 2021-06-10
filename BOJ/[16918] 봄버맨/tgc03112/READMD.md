# [16918] 봄버맨

## 분류

> 구현

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R,C,N;
	static char[][] map;
	static int[][] num;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		num = new int[R][C];

		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=str.charAt(j);
				//현재 폭탄3초가 될 때 터짐
				if(map[i][j]=='O') {
					num[i][j]=3;
				}
			}
		}

		int time=1;
		while(time<N) {
			time++;
			//폭탄 설치
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(map[i][j]=='.') {
						map[i][j]='O';
						num[i][j]=time+3;
					}
				}
			}
			if(time==N)
				break;
			time++;
			bomb(time);
		}
//		System.out.println("===============================");
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void bomb(int time) {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(num[i][j]==time) {
					map[i][j]='.';
					for(int d=0;d<4;d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						if(nx<0 || ny<0 || nx>=R ||ny>=C) continue;
						map[nx][ny]='.';
					}
				}
			}
		}
	}
}
```

## 문제풀이

입출력을 위한 char배열 map 과 폭탄을 터트리는 시간을 계산하기 위한 int배열 num 두 개의 이차원 배열을 사용했습니다.

time을 1 부터 시작해 1초씩 늘려줍니다. 2,4,6,,,등 짝수일때는 폭탄을 설치해줍니다. 3초 뒤에 터트려야 하기 때문에 현재시간+3만큼 해서 num배열에 저장해줍니다.

while 문을 돌면서 시간이 되면 break를 해서 종료한 뒤 map배열을 출력해줍니다.

time 3,5,7,, 홀수일때가 되면 폭탄을 터트려줍니다. 3초뒤에 터트리는 시간으로 계산해서 num배열에 저장했기 때문에 현재 시간과 num에 저장된 시간이 같으면 터트리고 사방에 있는 폭탄들도 터트리면 됩니다.

시간이 될 때 까지 반복해주고 마지막에 출력해주면 끝입니다.
