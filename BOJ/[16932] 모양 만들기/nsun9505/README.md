# [16932] 모양만들기

## 분류
> BFS

## 코드
```java
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

```

## 문제풀이
그냥 BFS를 사용해서 0인 위치에 1을 놓고, 거길 시작점으로 BFS를 돌리는 방식으로 하면 시간 초과가 뜹니다!
   - 너무 문제를 쉽게 봤네여..
   - 만약에 더 효율적으로 풀 수 있고 그 방법이 확실하다면 쉽게 풀지말고 생각한 방식대로 풀어봐야 겠습니다.
   - 아니면, 일단 쉬운 방법 -> 안 된다 -> 좀 더 효율적인 또는 정확한 방법 이렇게!

제가 구현한 방식은 미리 1인 위치들의 영역을 구합니다.
   - BFS를 시작한 위치를 BFS를 돌리며 만난 1인 위치의 parent로 지정합니다.
   - parent로 지정하는 이유는 0인 위치를 1로 바꿨을 때, 주변에 1인 위치가 있을 수 있습니다.
   - 1인 위치의 넓이를 알아내기 위해서 BFS를 돌리는 방법도 있지만, 주변에 있는 1인 위치가 어떤 위치에서 BFS를 돌렸고, 
   - 그 시작 위치에 해당 구역의 넓이를 저장함으로써 매번 BFS를 돌리는 것보다 바로 바로 찾아서 계산하는 방식으로 했습니다.

그럼 이제 넓이는 모두 구했고, 0인 위치들에 1을 놓았다는 가정하에 주변(상, 하, 좌, 우)에 1이 있는지 봅니다.
   - 1이 있다면, 해당 위치의 부모(BFS로 넓이를 구할 때, 시작한 위치)를 알아와서 set에 저장합니다.
   - set으로 저장한 이유는 주변에 1이 여러 개인데, 그 1들이 속한 영역이 똑같은 영역이라면 중복된 넓이를 더할 수 있으므로 set에 저장해서 중복을 없앤 것입니다.

0인 위치에 1을 놓았을 때의 넓이를 구해서 현재의 답과 비교해서 방금 구한 넓이가 더 크다면 답을 갱신하면 됩니다.