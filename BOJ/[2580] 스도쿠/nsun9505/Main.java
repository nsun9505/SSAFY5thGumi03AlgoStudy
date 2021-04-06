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

//        System.out.println("----------------------------");
//        for(int i=0; i<MAX; i++){
//            for(int j=0; j<MAX; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("----------------------------");

        Element elem = list.get(index);
        for(int i=1; i<=MAX; i++){
            int num = 1 << i;
            int rec = rectangle[parent[elem.row][elem.col]];
            int row = rows[elem.row];
            int col = cols[elem.col];
            if((col & num) == 0 && (row & num) == 0 && (rec & num) == 0){
                board[elem.row][elem.col] = i;
                rectangle[parent[elem.row][elem.col]] |= num;
                rows[elem.row] |= num;
                cols[elem.col] |= num;

                boolean ret = solution(index+1);
                if(ret)
                    return ret;

                board[elem.row][elem.col] = 0;
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