import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Element> zeroList = new ArrayList<>();         // 0인 위치 저장
    static ArrayList<Element> oneList = new ArrayList<>();          // 1인 위치 저장
    static Queue<Element> queue = new LinkedList<>();               // BFS에 사용할 큐
    static HashMap<Element, Integer> areaMap = new HashMap<>();     // BFS로 구한 각 구역의 넓이

    // 0인 위치에 1을 놓았을 때 주변에 여러 개의 1이 있을 수 있고, 그 1들이 연결되어 있을 수도 있고 아닐 수도 있으므로
    // set에 담아서 중복을 제거
    static HashSet<Element> areaSet = new HashSet<>();             

    static int N, M;
    static int[][] map;

    // 임의의 위치가 BFS를 돌릴 때, 첫 시작 위치는 BFS를 돌리며 만난 1인 위치들의 부모가 됩니다.
    static Element[][] parent;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        parent = new Element[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    zeroList.add(new Element(i, j));
                else
                    oneList.add(new Element(i, j));
            }
        }

        // 1로 연결된 위치들의 넓이를 구함
        // parent[start.row][start.col] = null : 아직 넓이를 구하지 않은 영역
        // parent[start.row][start.col] != null : 넓이를 이미 구한 영역
        for(Element start : oneList){
            if(parent[start.row][start.col] != null)
                continue;
            // 넓이 구하기
            BFS(start);
        }


        int answer = 0;
        // 0인 위치에 1을 놓아보고 주변에 1이 있다면 해당 1이 속하는 영역의 넓이를 이미 구해진 넓이를 가져와서 구하기
        for(Element start : zeroList){
            areaSet.clear();

            // 0 주변에 1이 있다면 해당 위치가 속하는 구역의 넓이를 알아오기
            for(int dir=0; dir<4; dir++){
                int nx = start.row + dx[dir];
                int ny = start.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(map[nx][ny] == 0)
                    continue;
                
                // 1인 위치가 있다면 해당 위치가 속하는 영역의 부모(BFS를 시작한 곳)를 가져온다.
                // BFS를 시작한 곳에 넓이를 저장했기 때문이다.
                areaSet.add(parent[nx][ny]);
            }

            // 0인 위치에 1을 놓았을 때 구역의 넓이 구하기
            int sum = 1;
            for(Element elem : areaSet)
                sum += areaMap.get(elem);

            answer = Math.max(answer, sum);
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 영역 넓이 구하기
    public static void BFS(Element start){
        queue.offer(start);
        parent[start.row][start.col] = start;
        int cnt = 0;

        while(!queue.isEmpty()){
            Element cur = queue.poll();
            cnt++; // 넓이 구하기 = 1의 개수 카운트

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(map[nx][ny] == 0)
                    continue;

                // nx, ny가 1이고, parent[nx][ny]가 null이면 아직 방문되지 않는 영역을 뜻함.
                if(parent[nx][ny] == null) {
                    parent[nx][ny] = start;
                    queue.offer(new Element(nx, ny));
                }
            }
        }

        areaMap.put(start, cnt);
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return row == element.row &&
                    col == element.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}