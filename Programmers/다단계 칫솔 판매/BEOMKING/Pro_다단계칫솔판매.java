package 구현.자료구조;

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
