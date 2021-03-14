public static int[] solution(int[] progresses, int[] speeds) {
		int idx = 0; // 일 수 체크
		int con = 0; //반복문을 빠져 나오도록 progresses의 length, 즉 기능 개수가 되면 반복문을 빠져나옴
		int[] tmp = new int[progresses.length]; //answer배열을 완성시켜주기 위한 배열
//		몇일에 걸쳐서 배포를 할지 모름으로 tmp배열을 생성해서 배포가 가능했다면 몇번 기능 배포를 했는지 answer배열에  넣어줌
		// 하루에 하나씩 배포를 한다고 가정한다면 tmp 배열의 크기는 progresses.length 만큼 최대로 가질것
		while(con != progresses.length){ // con의 크기가 기능 개수가 된다면 반복문 탈출

			for(int i = 0; i < progresses.length ; i++){ // 하루가 지나는 반복문
				if(progresses[i] != 0 && progresses[i] < 100){ // 0이면 이미 배포, 100이상이면 배포대기중이므로 진행하지않음
					progresses[i] += speeds[i]; // 진행되는 기능은 속도만큼 더해주어 진행률 증가
				}                   
			}

			int cnt = 0; // 하루 배포가능한 기능 수
			for(int i = 0; i < progresses.length; i++){ // 배포가능한지 순서대로 체크
				if(progresses[i] == 0) continue; // 0이면 이미 배포를 했으므로 넘어감
				if(progresses[i] >= 100){ // 100이상이면 배포가능
					progresses[i] = 0; // 배포했으니 0으로 변경
					cnt++;                      // 기능 배포했으므로 cnt++
				}else break; // 순서대로 배포가능하기에 해당 기능을 배포를 못한다면 다음것도 못하므로 break.
			}
			if(cnt!= 0){ // 배포가능한 기능이 있다면
				tmp[idx++] = cnt; // tmp배열에 담음
				con += cnt; // 기능 배포 완료수
			}
		}

		int[] answer = new int[idx]; // 결과 배열
		for (int i = 0, j = 0; i < idx; i++, j++) { // idx만큼 tmp에 값이 들어있으니 anwser에 tmp의 담아줌 
			answer[i] = tmp[j];
		}
		return answer; // 결과 return
	}
}