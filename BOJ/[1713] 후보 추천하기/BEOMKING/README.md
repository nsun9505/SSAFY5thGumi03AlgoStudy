# [1713] 후보 추천하기

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int student[] = new int[101];
        int time[] = new int[101];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = 0;
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(student[num] == 0) count += 1; // 새로운 학생 추가
            if(count > N){ // 학생이 추가되어 사진틀이 초과된다면
                int min = Integer.MAX_VALUE;
                int index = 0;
                for (int j = 0; j < student.length; j++) {
                    if(student[j] > 0 && min >= student[j]){ // 추천수가 0보다 크고 현재 최소 추천수보다 작거나 같다면
                        if(min == student[j]) { // 현재 최소 추천수와 같다면
                            if (time[index] > time[j]) { // 시간이 더 오래됬다면
                                continue;
                            }
                        }
                        min = Math.min(min, student[j]);
                        index = j;
                    }
                }
                student[index] = 0;
                time[index] = 0;
                count -= 1;
            }
            for (int j = 0; j < time.length; j++) { // 기존에 있는 후보자들의 시간 증가
                if(student[j] > 0) time[j] += 1;
            }
            if(student[num] == 0) { // 새로 추가된 후보라면
                time[num] += 1;
            }
            student[num] += 1;
        }
        for (int i = 0; i < student.length; i++) {
            if(student[i] > 0){
                System.out.print(i + " ");
            }
        }
    }
}
```

## 문제 풀이
그냥 시키는 대로 하면 되는 구현 문제입니다.

후보자들의 수가 많지 않으므로 배열을 이용했습니다.

사진틀보다 후보자가 많아지면 추천수를 비교하고 같다면 time 배열을 이용해 시간을 비교했습니다.

