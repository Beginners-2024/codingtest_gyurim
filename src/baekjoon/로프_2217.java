package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000
 * 시간 제한: 2초
 *
 * [1번째 접근법] => 실패
 * 식: 최소 로프 중량(rope[0]) x n
 * 위의 식을 사용하려고 했으나, 최소 로프가 다른 로프들의 중량에 비해 많이 작을 경우(평균을 깎아먹는 경우) 포함 안 하는게 이득
 *
 * 반례 => 입력값: 총 7개, 10 2 5 7 8 일 경우, (10, 8, 7)의 로프를 사용해야 가장 많은 중량을 들어올릴 수 있음
 *
 * [2번째 접근법] => 실패
 * sort()를 한 다음, maxW 값이 갱신된다면 for문 바로 종료하는 로직을 추가함
 * 그렇게 구현한 이유는 로프가 더 추가될 수록 로프가 들 수 있는 중량은 줄어들기 때문에 평균값이 줄어든다고 생각했기 때문
 * 하지만, (10, 8)의 로프를 사용한다면 maxW: 16, (10, 8, 7)의 로프를 사용한다면 maxW: 21
 * 따라서, 로프가 추가될 수록 평균값이 줄어든다는 전제가 틀림
 *
 * [3번째 접근법] => 성공
 * 모든 경우의 수를 탐지해서, maxW를 출력하도록 구현
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NlogN)
 * 메모리: 29752 KB, 시간: 344 ms
 **************************************************************************************/

public class 로프_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // k개의 로프를 사용하여 중량이 w인 물체를 들어올릴 때, 각각의 로프에는 모두 고르게 w/k 만큼 중량이 걸림

        int n = Integer.parseInt(st.nextToken());
        int[] rope = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            rope[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rope); // O(NlogN)

        int maxW = 0;
        int count = 1;

        for (int i = n - 1; i >= 0; i--) {
            int w = count * rope[i];
            if (w > maxW) {
                maxW = w;
            }
            count++;
        }
        System.out.println(maxW);
    }
}
