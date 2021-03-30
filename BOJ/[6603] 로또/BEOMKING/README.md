# [6603] 로또

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int permu[];
    static int numbers[];
    static StringBuilder sb;
    static final int lotto = 6;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;
            permu = new int[k];
            numbers = new int[lotto];
            for (int i = 0; i < k; i++) {
                permu[i] = Integer.parseInt(st.nextToken());
            }
            combination(0, 0);
            sb.append("\n");
        }
        System.out.print(sb.toString().trim());
    }
    static void combination(int start, int count){
        if(lotto == count){
            for (int i = 0; i < lotto; i++) {
                sb.append(numbers[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < k; i++) {
            numbers[count] = permu[i];
            combination(i + 1, count + 1);
        }
    }
}

```

## 문제풀이
아 힐링 된다..