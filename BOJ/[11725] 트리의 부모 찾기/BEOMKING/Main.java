package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_11725_트리의부모찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            tree.get(first).add(second);
            tree.get(second).add(first);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1));
        boolean visited[] = new boolean[N + 1]; // 해당 노드를 방문했는지
        int parent[] = new int[N + 1]; // 각 노드의 부모노드 저장

        while(!queue.isEmpty()){
            Node now = queue.poll();
            parent[now.child] = now.parent; // 현재 노드의 부모 노드
            visited[now.child] = true; // 현재 노드 방문 체크

            for (int i = 0; i < tree.get(now.child).size(); i++) {
                if(visited[tree.get(now.child).get(i)]) continue;
                queue.offer(new Node(now.child, tree.get(now.child).get(i)));
            }
        }
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i] + "\n");
        }
        System.out.print(sb.toString().trim());
    }
    static class Node{
        int parent;
        int child;

        public Node(int parent, int child) {
            this.parent = parent;
            this.child = child;
        }
    }
}
