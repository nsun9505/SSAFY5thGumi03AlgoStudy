# [스택/큐] 기능 개발 

## 분류
> 자료구조
>
> 스택/큐

## 코드
```java
 public static int[] solution(int[] progresses, int[] speeds) {
    	int idx = 0;
        int con =0;
        int[] tmp = new int[100];
        while(con != progresses.length){
            
              for(int i = 0; i < progresses.length ; i++){
                  if(progresses[i] != 0 && progresses[i] < 100){
                       progresses[i] += speeds[i];
                  }                   
              }
            for(int a : progresses) {
            	System.out.print(a+" ");
            }
            System.out.println();
            int cnt = 0;
            for(int i = 0; i < progresses.length; i++){
                if(progresses[i] == 0) continue;
                if(progresses[i] >= 100){
                    progresses[i] = 0;
                    cnt++;                       
                }else break;
            }
            if(cnt!= 0){
                tmp[idx++] = cnt;
                 con += cnt;
            }
        }
   
     int[] answer = new int[idx];
     for (int i = 0, j = 0; i < idx; i++, j++) {
			answer[i] = tmp[j];
		}
     return answer;
    }
}
```

## 문제 풀이

- 각 기능의 진도가 100%일 대 배포가 가능하며 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 되지 않습니다. 또한 배포는 하루에 한 번만 할 수 있고 기능 진행 속도는 하루를 기준 속도입니다.

  즉, 기능이 들어온 순서대로 진도가 100%가 된다면 배포해주고 다음 순서확인해서 기능 완성이 되었다면 그 기능 또한 배포를 해주며 해당 하루에 몇개의 기능을 배포했는지 체크해주며 결과를 출력시켰습니다.

  - progresses 배열에 기능의 진행도가 순서대로 입력되므로 순서대로 입력받은 speeds(기능 진행 속도)를 더해주었습니다.
    - 기능의 진행도가 100이 넘고 해당 기능이 배포 순서이면 0으로 바꾸었으며, 진행도가 0이 아니거나 100이상이 넘으면 진행속도를 더해주지 않고 넘어갔습니다.

  - 하루 배포가능한 기능의 수를 순서대로 체크합니다.
    - 0이면 이미 배포를 했으면 넘어가고 100이상이면 배포가 가능하므로 해당 기능 개선 진행률을 0으로 바꾸어주며 , 배포가능한 횟수(cnt++)를 증가 시켜줍니다.
    - 순서대로 배포가 가능하기에 해당 기능을 배포하지 못한다면 다음 기능이 100%라도 배포를 하지 못하므로 break로 체크 반복문을 빠져나옵니다.
  - 배포가능한 기능이 있다면 cnt != 0입니다. 그러므로 tmp[idx]에 기능 가능한 횟수를 담아주며 idx ++ 또한 기능 배포 완료수(con += cnt)를 cnt 만큼 증가 시켜줍니다.
  - answer(결과) 배열에 tmp를 옮겨 담은 후 (idx만큼 담았으니 answer 배열은 idx 크기만큼 생성), answer배열을 return 합니다.

