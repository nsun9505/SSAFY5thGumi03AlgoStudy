# [1717] 집합의 표현

## 분류
> 유니온-파인드

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=1; i<=N; i++)
            parent[i] = i;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 합집합 연산
            if(cmd == 0)
                union(a, b);
            else{
                // 부모 찾기
                a = find(a);
                b = find(b);

                // 부모가 같으면 같은 집합에 속한 것이므로 YES
                if(a == b) sb.append("YES\n");

                // 아니면 NO
                else sb.append("NO\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 부무(Root) 찾기
    // 찾으면서 루트 밑으로 가도록 만든다.
    public static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    // 합집합 연산
    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y)
            return;

        if(x > y){
            int tmp = x;
            x = y;
            y = tmp;
        }

        parent[y] = x;
    }
}
```

## 문제풀이
유니온-파인드를 쓰면 쉽게 해결할 수 있습니다.

입력이 0 a b 일때는 합집합 연산이므로 union을 쓰면 됩니다.

입력이 1 a b 이면 find(a), find(b)를 통해서 각 원소가 속한 집합의 루트를 알아냅니다.

루트(부모)가 같으면 같은 집합에 속한 것이므로 YES를 출력합니다.

루트가 다르다면 같은 집합이 아니므로 NO를 출력하면 됩니다.