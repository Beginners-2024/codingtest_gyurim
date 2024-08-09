package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 3 ≤ K ≤ 1,000,000
 * file <= 10,000
 *
 * int는 약 10^9 의 숫자를 담을 수 있음
 * long은 약 10^18 의 숫자를 담을 수 있음
 *
 * -2,147,483,648 <= int <= 2,147,483,647
 * -9,223,372,036,854,775,808 <= long <= 9,223,372,036,854,775,807
 *
 * 최악의 경우, pq에 들어갈 파일의 비용이 10,000 * 1,000,000 = 10^10 이상 범위의 비용이 들어갈 수 있기에
 * pq의 자료형을 **long**으로 선언해줘야 함
 *
 * 시간 제한: 2초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(T * 2K * logK) = O(T * 2*10^6 * 6) = T * 12 * 10^6
 * 메모리: 276476 KB, 시간: 3004 ms
 **************************************************************************************/

public class 파일_합치기3_13975 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());
        while (t-- > 0) { // O(T)
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>(); // 최소 힙
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < k; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            // 최소 비용 계산
            sb.append(calculateCost(pq)).append('\n'); // O(2K * logK)
        }
        System.out.print(sb);
    }

    private static long calculateCost(PriorityQueue<Long> pq) { // O(2K * logK)
        long cost = 0L;
        int fileCount = 0; // 2를 넘지 않는다.
        long fileSum = 0L;

        while (!pq.isEmpty()) {
            if (fileCount == 2) {
                cost += fileSum;
                pq.offer(fileSum); // O(logK)
                fileCount = 0;
                fileSum = 0;
            } else {
                fileSum += pq.poll(); // O(logK)
                fileCount++;
            }
        }
        cost += fileSum;

        return cost;
    }
}
