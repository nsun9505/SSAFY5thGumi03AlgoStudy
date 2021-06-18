import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int R = 6, C = 4;
    static int[][] greenBoard = new int[R][C];
    static int[][] blueBoard = new int[R][C];
    static HashMap<Integer, Block> greenMap = new HashMap<>();
    static HashMap<Integer, Block> blueMap = new HashMap<>();
    static int score = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int index = 1;
        for(int i=0; i<N; i++, index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            Block greenBlock = createBlock(index, 0, col, type);
            if(type == 2) type = 3;
            else if(type == 3) type = 2;
            Block blueBlock = createBlock(index, 0, row, type);

            greenMap.put(index, greenBlock);
            blueMap.put(index, blueBlock);

            greenBlock.move(greenBoard);
            blueBlock.move(blueBoard);

            remove(greenBoard, greenMap);
            remove(blueBoard, blueMap);

            shift(greenBoard, greenMap);
            shift(blueBoard, blueMap);
        }
        sb.append(score + "\n");
        sb.append((countBlock(greenBoard) + countBlock(blueBoard)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int countBlock(int[][] board){
        int count = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(board[i][j] > 0)
                    count++;
            }
        }
        return count;
    }

    public static void remove(int[][] board, HashMap<Integer, Block> map){
        while(true){
            int removedRow = checkRow(board);
            if(removedRow == -1)
                break;
            score++;
            removeRow(board, map, removedRow);
            simpleMove(board, map, removedRow-1);
        }
    }

    public static void shift(int[][] board, HashMap<Integer, Block> map){
        while(checkOneRow(board)){
            removeRow(board, map, R-1);
            simpleMove(board, map, R-1);
        }
    }

    public static void simpleMove(int[][] board, HashMap<Integer, Block> map, int startRow){
        if(startRow < 0)
            return;

        for(int row=startRow; row>=0; row--){
            for(int col=0; col<C; col++){
                if(board[row][col] == 0)
                    continue;
                int index = board[row][col];
                Block block = map.get(index);
                Element element = block.getElement(row, col);
                board[element.row][element.col] = 0;
                element.move();
                board[element.row][element.col] = index;
            }
        }
    }

    public static void removeRow(int[][] board, HashMap<Integer, Block> map, int row){
        for(int col=0; col<C; col++){
            int index = board[row][col];
            if(index == 0)
                continue;
            Block block = map.get(index);
            Element element = block.getElement(row, col);
            block.remove(element);
            board[row][col] = 0;
            if(block.elements.size() == 0)
                map.remove(index);
        }
    }

    public static int checkRow(int[][] map){
        int row = -1;

        for(int i=R-1; i>=2; i--){
            int count = 0;
            for(int j=0; j<C; j++){
                if(map[i][j] > 0)
                    count++;
            }

            if(count == C)
                return i;
        }

        return row;
    }

    public static boolean checkOneRow(int[][] map) {
        int row = 1;
        for (int j = 0; j < C; j++) {
            if (map[row][j] > 0)
                return true;
        }
        return false;
    }

    public static Block createBlock(int index, int row, int col, int type){
        List<Element> elements = new ArrayList<>();
        elements.add(new Element(row, col));
        if(type == 2)
            elements.add(new Element(row, col+1));
        if(type == 3)
            elements.add(new Element(row+1, col));
        Block block = new Block(index, elements);
        return block;
    }

    static class Block{
        int index;
        List<Element> elements;

        public Block(int index, List<Element> elements) {
            this.index = index;
            this.elements = elements;
        }

        public void move(int[][] map){
            for(Element element : elements)
                map[element.row][element.col] = 0;
            while(true){
                boolean isBreak = false;
                for(Element element : elements){
                    if(!element.isMove(map)){
                        isBreak = true;
                        break;
                    }
                }

                if(isBreak)
                    break;

                for(Element element : elements)
                    element.move();
            }

            for(Element element : elements)
                map[element.row][element.col] = index;
        }

        public void remove(Element element){
            elements.remove(element);
        }

        public Element getElement(int row, int col){
            Element findElement = null;
            for(Element element : elements) {
                if (element.row == row && element.col == col){
                    findElement = element;
                    break;
                }
            }
            return findElement;
        }
    }


    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean isMove(int[][] map){
            int nx = row + 1;
            if(nx >= map.length)
                return false;
            if(map[nx][col] != 0)
                return false;
            return true;
        }

        public void move(){
            row += 1;
        }
    }
}
