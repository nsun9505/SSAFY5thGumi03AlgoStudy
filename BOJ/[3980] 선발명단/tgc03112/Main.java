import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;
	static boolean[] visited;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C = Integer.parseInt(br.readLine());	//테케
		
		for(int tc=0;tc<C;tc++) {
			map = new int[11][11];
			visited = new boolean[11];
			res = 0;
			for(int i=0;i<11;i++) {
				st = new  StringTokenizer(br.readLine());
				for(int j=0;j<11;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			position(0,0);
			System.out.println(res);
		}
	}

	private static void position(int power, int index) {
		if(index==11) {
			res = Math.max(res, power);
			return;
		}

		for(int i=0;i<11;i++) {
			if(map[i][index]!=0 && !visited[i]) {	//능력치가 0이 아니고, 아직 배치되지 않은 경우
				visited[i] = true;	//배치
				position(power + map[i][index] ,index+1);	//능력치를 누적
				visited[i] = false;
			}
		}
	}
}
