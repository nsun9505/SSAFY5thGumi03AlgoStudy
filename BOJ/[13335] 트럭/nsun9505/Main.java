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