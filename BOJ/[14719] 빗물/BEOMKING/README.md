# [14719] 빗물

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static boolean map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int col = Integer.parseInt(st.nextToken());
            for (int j = 0; j < col; j++) {
                map[j][i] = true; // 블록 생성
            }
        }

        for (int i = 0; i < H; i++) {
            boolean before = false;
            int count = 0;
            for (int j = 0; j < W; j++) {
                if(before && map[i][j] == false){ // 이전에 블록이 있었고 현재 블록이 없다면 
                    count++;
                }else if(before && map[i][j]){ // 이전에 블록이 있었고 현재도 블록이 있다면
                    result += count;
                    count = 0;
                }else if(before == false && map[i][j]){ // 이전에 블록이 없었고 현재 블록이 있
                    before = true;
                }
            }
        }

        System.out.println(result);
    }
}
```

## 문제풀이
구현 문제입니다.

각 층마다 조건을 확인해서 물의 양을 결과 값에 더해주는 방법을 사용했습니다.

확인해야할 경우는 3가지입니다.

- 이전에 블록이 있었고 현재 블록이 없다면

  물이 쌓일 수 있으므로 count를 증가

- 이전에 블록이 있었고 현재도 블록이 있다면

  블록이 연속으로 있을 수도 있고 그 사이에 물이 쌓여있을 수도 있으므로 현재까지 쌓은 count를 결과에 저장

- 이전에 블록이 없었고 현재 블록이 있다면

  물을 쌓기 위해 블록을 true로 변경

이후 저장된 result 값을 출력하면 됩니다.