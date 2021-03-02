# [5430] AC - JAVA

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

        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t<T; t++){
            String cmd = br.readLine();

            int N = Integer.parseInt(br.readLine());
            // 입력으로 들어오는 [1,2,3]에서 양 끝 [ ] 없애고, ","로 자르기
            StringTokenizer st = new StringTokenizer(br.readLine().replaceAll("\\[", "").replaceAll("]", ""), ",");
            String[] list = new String[N];

            // 배열에 담기
            for(int i=0; i<N; i++)
                list[i] = st.nextToken();

            // isError : 크기가 0인데 D 명령이 들어온 경우 true, 그렇지 않으면 계속 false 유지
            boolean isError = false;

            // isReverse : 뒤집혔는지 아닌지 체크하는 변수
            boolean isReverse = false;

            // start : 시작 부분 index
            // end : 끝 부분 index
            int start = 0;
            int end = list.length-1;

            // cmd = "RDD"와 같은 입력으로 받은 함수
            for(int i=0; i<cmd.length(); i++){
                // R이면 뒤집기
                if(cmd.charAt(i) == 'R')
                    // 실제로 뒤집지는 않고 뒤집어 졌는지 아닌지를 판단할 수 있게 isReverse 값 변경
                    // 뒤집혀 있을 때 다시 뒤집으면 원래가 되므로 false
                    // 뒤집히지 않은 상태에서 뒤집으면 뒤집힌 상태가 되므로 true
                    isReverse = isReverse == false ? true : false;
                else{
                    // start가 end보다 크면 줄어들다가 더이상 줄어들 수 없음을 의미
                    // 거기에 D를 수행하면 error를 출력해야 하므로 isError를 true로 두고 break! -> 더 이상 진행하지 않아야 한다.
                    if(start > end){
                        isError = true;
                        break;
                    }

                    // 실제로 지우는 것이 아니라, start ~ end 범위에서 뒤집혔으면 end를 줄이고, 뒤집히지 않았으면 start를 줄인다.
                    // 뒤집혀 있을 때 앞은 맨 뒤이므로 뒤에서 문자를 제거해야 하므로 end가 가리키는 위치를 하나 줄임으로써 범위에 포함되지 않도록 한다.
                    // 뒤집히지 않았으면 앞에서 문자를 하나 제거해야 하므로 start가 가리키는 위치를 하나 증가시킴으로써 범위에 포함되지 않도록 한다.
                    if(isReverse) end--;
                    else start++;
                }
            }

            // 주의 : String으로 계속 더하면 시간초과가 나는 것 같습니다... 아닌가?
            StringBuilder ret = new StringBuilder("error");
            // Error가 발생하지 않았다면!
            if(!isError){
                ret.setLength(0);
                ret.append("[");
                // 현재 배열이 뒤집혀 있다면 맨 끝에서부터 맨 처음으로 가면서 추가
                if(isReverse) {
                    for (int i = end; i >= start; i--)
                        ret.append(list[i] + (i == start ? "" : ","));
                } else {
                    // 현재 배열이 뒤집히지 않았다면 맨 처음부터 맨 끝까지 가면서 추가
                    for (int i = start; i <= end; i++)
                        ret.append(list[i] + (i == end ? "" : ","));
                }
                ret.append("]");
            }
            sb.append(ret.toString() + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
시간초과가 많이 뜨는 문제였습니다.

R과 D에 따라 명령을 수행하면 됩니다.
   - 입력으로 주어지는 배열을 String 배열에 담았습니다.
   - index로 바로 접근할 수 있기 떄문입니다.

명령이 R일 경우에는 뒤집습니다.
   - isReverse = !isReverse

명령이 D일 경우에는 맨 앞에서 문자 하나를 제거합니다.
   - 실제로 제거하지는 않고, start ~ end라는 범위를 주어서 범위의 값을 줄이도록 하였습니다.
   - 뒤집혀 있다면(isReverse = true) end가 처음부분이므로 end를 하나 줄이고
   - 뒤집혀 있지 않다면(isReverse = false) start가 처음부분이므로 start를 하나 증가시켜서
   - 앞에 문자를 제거하는 것처럼 보이도록 하였습니다.
   - 만약 start > end라면 범위가 0를 의미하므로 error가 발생하였다는 의미로 isError를 true로 갱신합니다.
   - 그리고 더 이상 진행할 필요가 없으니 break를 걸어서 for문을 빠져나옵니다.

마지막에 error가 발생하지 않아서 문자열을 출력하는 부분은 아래와 같습니다.
   - 뒤집혀 있으면(isReverse = true) end가 처음부분을 의미하므로 end에서 start로 감소하는 방향으로 ret에 담아줍니다.
   - 뒤집혀 있지 않으면(isReverse = false) start가 처음부분을 의미하므로 start에서 end로 증가하는 방향으로 ret에 담아줍니다.
   - 주의할 점은 StringBuilder를 사용해야 시간초과가 뜨지 않는 것 같습니다.
   - 그냥 String ret에 + 연산으로 문자열을 합치면 계속 문자열을 생성하기 때문에 시간 초과가 뜨는 것같습니다.
   - 그래서 StringBuilder를 사용해서 기존 문자열에 추가하는 방식으로 하면 시간초과가 뜨지 않는 것 같습니다.