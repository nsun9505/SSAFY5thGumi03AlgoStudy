# [1927] 최소힙

## 분류
> 자료구조
>
> 우선순위 큐

## 코드
```java
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
            this.data = new int[100001];
            this.heapSize = 0;
        }

        boolean isEmpty(){
            if(heapSize == 0)
                return true;
            return false;
        }

        void push(int item){
            // 힙의 사이즈를 하나 늘리고
            this.heapSize++;

            // 데이터 삽입
            data[this.heapSize] = item;

            // 방금 삽입된 데이터의 인덱스를 받아옵니다.
            // 이 값으로 부모의 값을 알아와서 비교하고 변경하기 위함!
            int index = this.heapSize;

            // index != 1 : 루트로 올라왔으므로 더 이상 비교할 부모가 없으므로 while 탈출
            // this.data[index/2] > item : 현재 위치의 부모 값과 삽입하려고 하는 값을 비교하여 참이라면, 부모의 값을 내립니다.
            while(index != 1 && this.data[index/2] > item){
                this.data[index] = this.data[index/2];
                index /= 2;
            }

            // while문을 탈출하면 index 값이 item이 들어갈 위치이므로 값을 넣어주고 끝내면 됩니다.
            this.data[index] = item;
        }

        int pop(){
            // pop으로 인해 삭제될 root 값
            int ret = this.data[1];
            
            // root 값에 힙의 마지막 원소를 넣어서 자리를 찾도록 함.
            this.data[1] = this.data[this.heapSize--];

            // 현재 인덱스
            int index = 1;

            // 자식 인덱스
            int child = index * 2;

            // item : 힙의 마지막에 위치했던 원소
            int item = this.data[1];

            // 자식 인덱스의 값이 heap의 크기보다 작거나 같은 경우만 while문 수행
            while(child <= this.heapSize){

                // 자식 인덱스의 값이 현재 힙 크기보다 작다면, 오른쪽 자식이 존재하지 않음!
                // 작거나 같은 경우는 child가 힙 크기일 수도 있고, 힙 크기 보다 1작을 수도 있습니다.
                // 힙 크기 보다 1작을 경우는 오른쪽 자식이 존재한다는 의미이고, 힙 크기와 자식 인덱스가 같다는 것은 오른쪽 자식이 없음을 의미
                // 오른쪽 자식이 있으면 왼쪽 자식과 오른쪽 자식 중에서 어떤 자식의 값이 더 작은지 비교하여 child를 갱신
                if(child < this.heapSize && this.data[child] > this.data[child+1])
                    child++;

                // 자기 자리를 찾아가기 위해 자식 값과 비교합니다.
                // 최소힙은 부모가 자식보다 작은 값을 가지므로
                // 부모가 자식보다 작다면 더 이상 내려가지 않고 멈춰야 합니다.
                if(item < this.data[child])
                    break;

                // 자식의 값보다 크다면, 해당 자식의 값을 현재 위치(부모)에 넣어주고
                // 현재 위치를 자식 인덱스로 바꾸고, 자식은 바뀐 현재 인덱스의 자식 인덱스로 갱신하면 됩니다.
                this.data[index] = this.data[child];
                index = child;
                child = index * 2;
            }

            // while문을 빠져나오면, 루트를 삭제하고 루트로 올라온 힙의 마지막 원소가 제자리를 찾았다는 의미입니다.
            // index가 item이 들어갈 위치이므로 값을 넣어줍니다.
            this.data[index] = item;

            // 이전에 저장했던 루트의 값을 리턴하면서 끝냅니다.
            return ret;
        }
    }
}
```

## 문제 풀이
최소 힙을 사용하면 쉽게 문제를 해결할 수 있습니다!

util 라이브러리에 있는 PriorityQueue를 사용하면 되겠지만, 저는 Heap을 사용하여 문제를 해결하였습니다.

삽입할 떄는 마지막에 넣어주고 부모와 값을 비교해서 자리를 찾아가도록 했습니다.

삭제는 루트(1번 인덱스)를 지우고 루트 자리에 힙의 가장 마지막 원소를 넣어서 아래 자식들과 비교하면서 자리를 찾아가도록 했습니다.