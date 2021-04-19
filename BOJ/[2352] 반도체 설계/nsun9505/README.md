# [2352] 반도체 설계

## 분류
> 이분 탐색

## 코드
```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(st.nextToken()));

        for(int i=1; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(list.get(list.size()-1) < num){
                list.add(num);
            }
            else {
                int index = lowerbound(list, num);
                list.set(index, num);
            }
        }
        sb.append(list.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int lowerbound(ArrayList<Integer> list, int target){
        int left = 0;
        int right = list.size()-1;

        while(left < right){
            int mid = (left + right)/2;

            if(list.get(mid) >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
```

## 문제풀이
이분탐색을 사용해서 풀 수 있습니다.

LIS에서 길이를 알아내는 데 이분탐색을 써서 푸는 것과 동일합니다.
   - 길이는 같지만 내용은 다를 수도 있음 주의해야 함.

해결한 방식은 i번째 포트와 연결하는 반대편 포트 번호가 주어지면 해당 포트 번호가 현재 list에서 몇 번째에 위치하는지를 알아냅니다.
   - 어느 위치인지는 이분탐색의 lower bound를 사용하면 입력 받은 수가 들어갈 수 있는 가장 왼쪽 위치를 알아올 수 있습니다.

위치를 알아냈으면 해당 위치에 실제로 넣어줍니다!

넣어준다는 것이 삽입이 아니라, 해당 위치의 값을 현재 입력 받은 값으로 변경하는 것입니다.

그러면 항상 list는 정렬된 상태를 유지하게 됩니다.

list 마지막 원소보다 큰 값이 들어오면 list 맨 뒤에 값을 추가해주면 정렬된 상태를 유지하게 됩니다.

정렬된 상태를 유지한다는 것은 연결선이 서로 꼬이지 않음을 의미합니다.

그러면 마지막까지 돌려서! list의 크기를 출력하면 끝!