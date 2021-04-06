# [2580] 스도쿠

## 분류
> 백트랙킹

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 9;
    static int[][] board = new int[MAX][MAX];
    static int[][] parent = new int[MAX][MAX];
    static int[] rows = new int[MAX];
    static int[] cols = new int[MAX];
    static int[] rectangle = new int[MAX];
    static ArrayList<Element> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0; i<MAX; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<MAX; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] > 0) {
                    rows[i] |= (1 << board[i][j]);
                    cols[j] |= (1 << board[i][j]);
                } else {
                    list.add(new Element(i, j));
                }
            }
        }
//        System.out.println(list.size());
        int index = 0;
        for(int i=0; i<MAX; i+=3){
            for(int j=0; j<MAX; j+=3){
                for(int row=i; row<i+3; row++){
                    for(int col=j; col<j+3; col++){
                        if(board[row][col] > 0)
                            rectangle[index] |= (1 << board[row][col]);
                        parent[row][col] = index;
                    }
                }
                index++;
            }
        }

        solution(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean solution(int index){
        if(index >= list.size()) {
            for(int i=0; i<MAX; i++){
                for(int j=0; j<MAX; j++){
                    sb.append(board[i][j] + " ");
                }
                sb.append("\n");
            }
            return true;
        }

        Element elem = list.get(index);
        for(int i=1; i<=MAX; i++){
            int num = 1 << i;

            // 작은 정사각형(3x3)
            int rec = rectangle[parent[elem.row][elem.col]];
            // 행
            int row = rows[elem.row];
            // 열
            int col = cols[elem.col];
            
            // i(1~9)가 행, 열 그리고 빈칸이 속한 3x3 사각형에서 한 번도 안 나온 수라면 선택
            // 어느 한 곳이라도 나왔다면 선택하지 않음.
            if((col & num) == 0 && (row & num) == 0 && (rec & num) == 0){

                // i를 빈칸에 쓴다.
                board[elem.row][elem.col] = i;

                // 비트 연산자를 사용해서 i번째 비트를 킴.
                rectangle[parent[elem.row][elem.col]] |= num;
                rows[elem.row] |= num;
                cols[elem.col] |= num;


                boolean ret = solution(index+1);
                if(ret)
                    return ret;

                // ret이 false라는 것은 여기에 현재 칸에 i를 놓았을 떄
                // 스도쿠를 완성하지 못한다는 것이므로 원상복구
                board[elem.row][elem.col] = 0;

                // 비트 연산자를 사용해서 i번째 비트를 뺌.
                rectangle[parent[elem.row][elem.col]] &= (~num);
                rows[elem.row] &= (~num);
                cols[elem.col] &= (~num);
            }
        }
        return false;
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
```

## 문제 풀이
전형적인 백트랙킹 문제!

스도쿠를 풀기 위해서는 가로, 세로, 그리고 빈칸이 속한 3x3 정사각형에서 

한 번도 나오지 않은 숫자를 선택해야 합니다.

그 숫자가 무엇인지는 입력받을 때 알 수 있습니다.

그리고 각 행과 열 그리고 3x3 정사각형에 어떤 수들이 있는지 알기 위해서 비트마스킹을 사용했습니다.

예를 들어, 0번째 행에 1, 5, 7, 9가 있다고 하면 rows[0]은 101010101이 됩니다.
   - 왜냐하면 1 << board[i][j]를 통해서 입력받은 숫자 크기만큼 1을 shift left하기 때문입니다.
   - 0이 나온 경우에는 따로 0인 위치를 저장하면 되겠습니다.

그러면 행과 열, 3x3 정사각형에 대해서 비트마스킹으로 표현하고, 0인 위치가 속한 rows, cols, rec(= 3x3 정사각형)을 검사합니다.

검사해서 rows, cols, rec에서 한 번도 안 나온 숫자라면 해당 숫자를 0인 위치에 쓰고 다음 0이 있는 위치로 이동합니다.

그러다가 0인 위치에 스도쿠 규칙에 맞게 숫자를 썼다면! true를 리턴해서 더 이상 진행되지 않도록 하고 board를 출력하면 됩니다.