# [20061] 모노미노도미노2

## 분류

> 구현
>
> 시뮬레이션

## 코드

```java
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

```

## 문제 풀이

전형적인 시뮬레이션 문제입니다.

초록색 보드에 내리는 것은 그냥 설명에 따라 블록을 아래로 내리면 됩니다.,

파란색 보드에 블록을 옮기는 것은 문제에 따르면 오른쪽으로 옮겨야 합니다.

하지만, 파란색 보드도 초록색 보드처럼 블록을 아래로 내리면서 시뮬레이션 돌리는 것이 가능합니다.

파란색 보드에 블록을 내릴 때 처음 주어진 블록의 row, col을 바꾸고, 2인 경우에는 3으로, 3인 경우에는 2로 바꾸면 초록색 보드처럼 블록을 아래로 내리면서 쌓을 수 있습니다.

블록을 떨어뜨리고, 각 행이 꽉 찼는지 확인합니다.

행이 꽉 찬 경우는 해당 행에 존재하는 블록을 모두 지운 후에는 그 이전에 있는 블록을 모두 한 칸씩 내려줍니다.

이것을 계속 반복해주다가 더 이상 꽉 찬 행이 없다면 지우고 내리고 하는 작업을 멈춥니다.

만약 지워진 것이 없다면 1번 행을 보고 블록이 존재하면 5번 행에 존재하는 블록을 지우고 모든 블록을 한 칸씩 내려줍니다.

다시 또 1번 행을 보고 블록이 존재하면 위와 동일하게 합니다.

행이 꽉 차서 지운 경우에는 score를 1증가시켜줍니다.

N개의 블록을 쌓는 것이 끝나면 파란색 보드와 초록색 보드에 있는 블록의 수를 카운트한 값과 score 값을 더해서 정답으로 출력하고 프로그램을 종료하면 됩니다.
