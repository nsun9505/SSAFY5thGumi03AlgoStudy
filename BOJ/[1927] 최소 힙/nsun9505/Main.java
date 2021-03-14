import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Heap heap = new Heap();
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                if(heap.isEmpty())
                    sb.append(0 + "\n");
                else
                    sb.append(heap.pop() + "\n");
            } else {
                heap.push(num);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Heap{
        int[] data;
        int heapSize;

        public Heap() {
            this.data = new int[100000];
            this.heapSize = 0;
        }

        boolean isEmpty(){
            if(heapSize == 0)
                return true;
            return false;
        }

        void push(int item){
            this.heapSize++;
            data[this.heapSize] = item;

            int index = this.heapSize;

            while(index != 1 && this.data[index/2] > item){
                this.data[index] = this.data[index/2];
                index /= 2;
            }

            this.data[index] = item;
        }

        int pop(){
            int ret = this.data[1];
            this.data[1] = this.data[this.heapSize--];

            int index = 1;
            int child = index * 2;
            int item = this.data[1];
            while(child <= this.heapSize){
                if(child < this.heapSize && this.data[child] > this.data[child+1])
                    child++;

                if(item < this.data[child])
                    break;

                this.data[index] = this.data[child];
                index = child;
                child = index * 2;
            }

            this.data[index] = item;

            return ret;
        }
    }
}