# [6603] 로또

## 분류
> 백트랙킹

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] arr;
    static final int LOTTO_MAX_SIZE = 6;
    static int[] lotto = new int[LOTTO_MAX_SIZE];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            // K가 0이라면 종료
            if(K == 0)
                break;

            arr = new int[K];
            for (int i = 0; i < K; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            // 6개 로또 번호 뽑기
            solution(0, 0);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int index, int start){
        // 6개의 로또 번호를 뽑았다면
        if(index == LOTTO_MAX_SIZE){
            // 출력
            for(int lottoNumber : lotto)
                sb.append(lottoNumber + " ");
            sb.append("\n");
            return;
        }

        // start : arr에서 시작할 번호
        // index : 6개 중에서 몇 번째인지
        // index+1 : 하나를 뽑았으므로 다음에 뽑을 위치
        // i+1 : arr의 i번째는 사용했으므로 다음 위치부터 사용하도록 함.
        for(int i=start; i<arr.length; i++){
            lotto[index] = arr[i];
            solution(index+1, i+1);
        }
    }
}
```

## 문제풀이
K개의 숫자에서 로또 번호로 사용할 6개를 뽑으면 출력하고 되돌아가는 방법으로 문제를 해결했습니다.

arr에 입력으로 받은 숫자들을 넣어두고, lotto에는 6개의 로또 번호를 저장했습니다.

solution(index, start)에서 index는 몇 번째 로또 번호인지를 의미하고, start는 arr의 몇 번째부터 시작해야 하는지를 알려주는 것입니다.
   - 순서는 상관없이 조합으로 뽑아야 하기 때문에 start를 해서 이전에 사용된 것은 다음 호출에서 사용할 수 없도록 했습니다.
   - 그리고 index가 6이 안 됐어도, start가 arr의 크기보다 커지면 for문에서 조건문이 false이므로 더 이상 재귀호출을 하지 않게 됩니다.
   - index가 6이 되면 출력하고 호출을 더이상 이어나가지 않도록 했습니다.