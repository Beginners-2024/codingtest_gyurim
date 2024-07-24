package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000 = 10^5
 * -2^31 < x < 2^31
 *------------------------------------------------------------------------------------
 * 시간복잡도:
 * 메모리:  KB, 시간:  ms
 **************************************************************************************/

public class 절댓값힙_11286 {
    private static class Number implements Comparable<Number> {
        int abs;
        int origin;

        Number(int abs, int origin) {
            this.abs = abs;
            this.origin = origin;
        }

        @Override
        public int compareTo(Number o) {
            if (this.abs == o.abs) {
                return Integer.compare(this.origin, o.origin); // 작은 거 먼저 (오름차순)
            }
            return Integer.compare(this.abs, o.abs); // 작은 거 먼저 (오름차순)
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Number> pq = new PriorityQueue<>(); // 최소 힙
        while (n-- > 0) { // O(N)
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (pq.isEmpty()) sb.append("0").append("\n");
                else sb.append(pq.poll().origin).append("\n");
            } else {
                // 배열에 x를 넣는 연산
                pq.offer(new Number(Math.abs(x), x));
            }
        }

        System.out.print(sb);
    }
}
