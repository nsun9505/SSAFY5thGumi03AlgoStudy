# [1927] 최소힙

## 분류
> 자료구조
>
> 우선순위 큐

## 코드
```java
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int input = sc.nextInt();
            if(input != 0){
                priorityQueue.add(input);
            }else {
                if (priorityQueue.size() == 0) {
                    sb.append(0 + "\n");
                } else {
                    sb.append(priorityQueue.poll() + "\n");
                }
            }
        }
        System.out.print(sb.toString().trim());
    }
}
```

## 문제 풀이
PriorityQueue를 사용했습니다. 전 구현할 능력이 아직 없으니까요
