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
