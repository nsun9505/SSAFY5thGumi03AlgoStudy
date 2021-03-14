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