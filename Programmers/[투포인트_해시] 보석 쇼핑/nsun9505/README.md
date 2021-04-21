# [2020 카카오 인턴십] 보석 쇼핑

## 분류
> 투포인트
>
> 해시

## 코드
```java
import java.util.HashMap;

public class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        // 각 보석에 번호 붙여주기
        HashMap<String, Integer> map = new HashMap<>();
        int index = 0;
        int[] arr = new int[gems.length];
        for(int i=0; i<gems.length; i++){
            if(!map.containsKey(gems[i]))
                map.put(gems[i], index++);
            arr[i] = map.get(gems[i]);
        }

        
        int start = 0;
        int end = 0;
        int numOfType = 1;
        int[] counts = new int[map.size()];
        counts[arr[0]] = 1;
        int N = arr.length;
        int total = map.size();
        int dist = Integer.MAX_VALUE;

        // start ~ end까지 찾아보기
        while(end < N){
            // start ~ end 범위 안에 진열된 보석 종류가 다 있는 경우
            if(numOfType == total){
                // 길이를 구하고
                int d = (end+1) - (start+1) - 1;

                // 현재 답과 비교하여 갱신
                if(d < dist) {
                    dist = d;
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                }

                // start부분은 빼기
                counts[arr[start]]--;
                // 해당 보석이 start ~ end 사이에 포함된 개수가 0이 되면 보석의 종류 수 감소시키기
                if(counts[arr[start]] == 0)
                    numOfType--;
                // start 위치 변경
                start++;
            }

            else{
                if(++end < N){
                    // 포함시킨 보석의 카운트 증가시키기
                    counts[arr[end]]++;

                    // 추가했을 때 개수가 1이라면 보석 종류의 수를 하나 증가시킴/
                    if(counts[arr[end]] == 1)
                        numOfType++;
                }
            }
        }

        return answer;
    }
}
```

## 문제 풀이
투포인트를 사용하면 쉽게 풀 수 있는 문제입니다.

회전 초밥 문제를 풀었다면 푸는데 어렵지 않을 겁니다!

만약 문제가 어렵게 느껴진다면 카카오 해설을 참고하세요.

먼저 문자열로 된 보석에 인덱스를 매깁니다. 
   - 예를 들면, DIA는 0, RUBY는 1, EMERALD는 2, SAPPHIRE는 3으로 할당해줍니다.

그러면 문자열 배열에서 숫자로 된 배열이 되기 때문에 해당 인덱스를 가진 보석이 start ~ end 범위에 몇개가 포함되었는지 카운트하는 것을 수월하게 할 수 있습니다.

투포인트를 사용해서 start ~ end 범위 안에 있는 보석의 종류 수가 총 보석 종류 수와 동일한지 비교합니다.

보석의 종류 수를 알기 위해서 counts 배열을 두어서 각 보석이 start ~ end 범위에 몇 개가 있는지 알아내고 보석 종류의 수를 알아내는데 사용합니다.

보석 종류 수와 start ~ end 범위 안에 있는 보석 종류의 수가 동일한 경우 start를 1 증가시켜서 보석의 개수를 하나 줄입니다.
   - start를 1 증가시키기 전에 start ~ end는 보석의 종류 수를 모두 포함하는 범위이므로 현재 답과 어느 것이 더 짧은지 찾아보고 갱신하면 됩니다.
   - 그리고 start를 증가시키기 전에 start가 가리키는 보석의 카운트 수를 하나 감소시킵니다.
   - 감소시켰는데 0이 되었다면, 보석의 종류 수가 하나 줄어들어야 하므로 numOfType을 하나 감소시키면 됩니다.
   - 그리고 start를 1 증가시킨 start ~ end 범위에서도 보석 종류의 수가 총 보석의 종류 수와 같은지 비교하는 것을 이어가면 됩니다.

보석 종류 수와 start ~ end 범위 안에 있는 보석 종류의 수가 동일하지 않다면(작다면) end를 증가시켜서 보석을 더 포함시켜 봅니다.
   - 방금 포함시킨 보석 종류가 end를 1증가시키기 전의 범위에서 존재하지 않았던 보석 종류라면 numOfType을 1 증가시킵니다.
   - 이를 판단하기 위해서 counts 배열을 두어서 어떤 보석이 몇 개가 포함되었는지를 알 수 있습니다.