# 다단계 칫솔 판매

## 분류

## 코드
```java
import java.util.HashMap;

class Pro_다단계칫솔판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int answer[] = new int[enroll.length];
        HashMap<String, Infomation> map = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], new Infomation(referral[i], i));
        }

        for (int i = 0; i < seller.length; i++) {
//            Infomation now = map.get(seller[i]); // 현재 seller의 부모와 순서 정보
            String now = seller[i];
            int money = amount[i] * 100; // 현재 seller의 수익

//            while(!now.parent.equals("-")){
            while (!now.equals("-")){
                answer[map.get(now).index] += money - (money / 10); // 수익 = 현재 수익 - (수익의 10%)
                money /= 10; // 위에 넘겨줄 10%
                if(money < 1) break; // 넘겨줄 수익이 없으면 종료
                now = map.get(now).parent; // 부모를 현재
            }
//            answer[now.index] += money - (money / 10);
        }
        return answer;
    }
    public static class Infomation{
        String parent;
        int index;

        public Infomation(String parent, int index) {
            this.parent = parent;
            this.index = index;
        }
    }
}

```

## 문제풀이

Hash를 이용한 구현 문제입니다.

구성원의 부모와 순서를 저장해주는 HashMap을 만듭니다.

seller 배열만큼 반복하면서 while문으로 현재 값이 "-"이 될 때까지 반복하면 됩니다.

주의할 점

- 수익의 * 9 % 10을 해주는 것이 아닌 수익 - (수익 / 10)을 해야한다.  (몫이 다를 수 있다 ex) 12 * 9 / 10 = 10, 12 * 9 / 10 = 1, 합 12 != 11)
- break로 탈출 조건을 줘야함
- 처음에 seller가 10만이라 줄이기 위해 줄이기 위해 seller에 따라 amount 값을 합쳤는데 그러면 나머지가 다를 수 있기 때문에 틀리다.
- 프로그래머스는 string을 비교할 때 == 이 아닌 무조건 equals를 사용해야한다

