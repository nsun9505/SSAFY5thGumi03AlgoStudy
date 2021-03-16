# [1713] 후보 추천하기

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<Element> images = new LinkedList<>();
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());
            boolean exist = false;
            for(int j=0; j<images.size(); j++){
                if(images.get(j).num == num){
                    images.get(j).cnt += 1;
                    exist = true;
                    break;
                }
            }

            if(exist)
                continue;

            if(images.size() == N){
                int minCnt = images.get(0).cnt;
                int minIdx = 0;
                for(int j=1; j<images.size(); j++){
                    if(images.get(j).cnt <= minCnt){
                        minCnt = images.get(j).cnt;
                        minIdx = j;
                    }
                }

                images.remove(minIdx);
            }

            images.offerFirst(new Element(num, 1));

        }

        Collections.sort(images, (o1, o2) -> o1.num - o2.num);
        for(Element elem : images)
            sb.append(elem.num + " ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int num;
        int cnt;

        public Element(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
```

## 문제 풀이
LinkedList에 추천받은 후보들을 저장해서 풀었습니다.

현재 추천받은 후보가 LinkedList에 존재한다면, 추천횟수를 증가시키고 다음 추천 후보로 넘어갑니다.

LinkedList에 존재하지 않는다면!

LinkedList가 가득 차서 하나를 지우고 들어가야 하거나

아니면, 아직 LinkedList의 크기가 N이 아니라면 추가만 해줍니다.

아직 LinkedList의 크기가 N보다 작다면 LinkedList의 앞 부분에 현재 후보를 넣어줍니다.
   - 뒤가 아닌 앞에 넣어주는 이유는 맨 앞부터 넣어주면 LinedList는 최신 후보로 정렬되기 때문입니다.

가득 찬 경우는 가장 투표수가 작고, 만약 그런 후보가 많다면 가장 오래된 사진을 제거합니다.
   - LinkedList는 맨 앞부터 최신이므로 뒤로 갈수록 오래된 사진이 됩니다.
   - 그래서 <= 조건을 줘서 투표수가 같거나 작은 경우에도 minIdx를 변경하여 투표수가 같아도 가장 오래된 사진이 있는 위치를 알아 올 수 있습니다.
   - 알아온 minIdx의 사진을 지우고, 새로운 후보의 사진을 LinkedList 앞 부분에 넣어주면 됩니다.