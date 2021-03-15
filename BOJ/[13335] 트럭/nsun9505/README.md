# [13335] 트럭 - JAVA

## 분류
> 구현
>
> 트럭

## 코드
```java
import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Integer> readyQueue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            readyQueue.add(Integer.parseInt(st.nextToken()));

        int curWeight = L;
        Deque<Element> ingDeque = new LinkedList<>();
        int sec = 0;
        while(!ingDeque.isEmpty() || !readyQueue.isEmpty()){
            for(Element elem : ingDeque)
                elem.sec++;

            if(!ingDeque.isEmpty() && ingDeque.peek().sec == W){
                Element elem = ingDeque.pollFirst();
                curWeight += elem.w;
            }

            if(!readyQueue.isEmpty() && readyQueue.peek() <= curWeight){
                curWeight -= readyQueue.peek();
                ingDeque.add(new Element(0, readyQueue.poll()));
            }

            sec++;
        }
        sb.append(sec);

        bw.write(sb.toString());
        bw.flush();
    }

    static class Element{
        int sec;
        int w;

        public Element(int sec, int w) {
            this.sec = sec;
            this.w = w;
        }
    }
}
```

## 문제 풀이
대기하는 트럭은 Queue에 담고, 다리 위에 있는 트럭은 Deque에 담아서 풀었습니다.

다리에 있는 트럭들을 이동시킵니다!
   - sec이라고 표현했는데, 다리 위의 위치로 봐도 무방합니다.

먼저 다리에서 내려올 트럭이 있는지 조사합니다.
   - 맨 앞에 있는 트럭 하나만 보면 됩니다!
   - 다음 트럭은 1초가 지난 후에 보면 되기 때문입니다.

맨 앞 트럭이 이제 다리를 벗어난다면, 해당 트럭의 무게만큼 현재 다리가 버틸 수 있는 무게에 더해줘서 다음 트럭이 올라올 수 있도록 합니다.

그리고 대기하고 있는 트럭 중에 다리 위에 올라갈 트럭이 있는지 readyQueue의 앞부분을 봅니다.
   - readyQueue가 비워져 있으면 보지 않습니다.
   - 아직 다리를 건너지 못한 트럭이 있다면, 해당 트럭의 무게가 현재 다리가 견딜 수 있는 무게보다 작거나 같으면 다리위로 올립니다!
   
다리를 건너고 있는 트럭이 담겨져 있는 Deque와 다리를 건너기 위해 트럭들이 기다리고 있는 Queue가 모두 비워지면 모든 트럭이 지나기까지 걸린 시간을 출력하면 됩니다.