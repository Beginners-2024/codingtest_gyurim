package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000
 *
 * [회고]
 * 이 문제는 몇 번의 비교가 필요한지 구하는 문제이기 때문에,
 * 만약 카드 묶음이 1개라면 비교할 필요가 없음
 * 따라서 답은 0이 출력되어야 함
 * => 문제를 잘 읽자..
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NlogN)
 * 메모리: 25460 KB, 시간: 368 ms
 **************************************************************************************/

public class 카드_정렬하기_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 무조건 작은 애들 먼저 합쳐주는 게 이득? ㅇㅇ
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소 힙

        while (n-- > 0) {
            int card = Integer.parseInt(br.readLine());
            pq.offer(card);
        }

        int totalSum = 0; // 총 비교 횟수

        if (pq.size() > 1) {
            int count = 0; // 현재 몇 개의 카드 묶음을 섞었는지 저장하는 변수. 2를 넘지 않는다.
            int sum = 0;

            while (!pq.isEmpty()) { // O(N)
                if (count <= 1) {
                    sum += pq.poll(); // O(logN)
                    count++;
                } else { // 두 개의 묶음이 모인 경우
                    pq.offer(sum); // 다시 우선 순위 큐에 넣어서 비교 대상으로 만들기 // O(logN)
                    totalSum += sum;
                    sum = 0;
                    count = 0;
                }
            }
            totalSum += sum;
        }

        System.out.println(totalSum);
    }
}
