## [1000] A+B – JAVA

## 분류
> 구현

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        sb.append((Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

```

## 문제 풀이
[문제를 어떻게 풀었는지 또는 어떻게 접근하여 풀었는지에 대해 작성하시면 됩니다.]