package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ n ≤ 1,000
 * 0 ≤ m ≤ 15×n
 * 1 ≤ ai ≤ 1,000,000
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(2M * logN + N * logN)
 * 메모리: 15344 KB, 시간: 176 ms
 **************************************************************************************/

public class 카드_합체_놀이_15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        while (n-- > 0) { // O(N)
            Long num = Long.parseLong(st.nextToken());
            pq.add(num);
        }

        long cardSum = 0L;
        int cardCount = 0; // 최대 2

        while (m > 0) { // O(2M) 번 반복 => O(2M * logN)
            if (cardCount == 2) {
                pq.offer(cardSum);
                pq.offer(cardSum);
                cardCount = 0;
                cardSum = 0L;
                m--;
            } else {
                cardSum += pq.poll(); // O(logN)
                cardCount++;
            }
        }

        long grade = 0;
        while (!pq.isEmpty()) { // O(N) 번 반복 => O(NlogN)
            grade += pq.poll(); // O(logN)
        }

        System.out.println(grade);
    }
}
