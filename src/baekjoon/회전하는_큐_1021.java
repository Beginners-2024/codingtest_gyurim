package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(M * N)
 * 메모리: 14216 KB, 시간: 136 ms
 **************************************************************************************/

public class 회전하는_큐_1021 {
    private static LinkedList<Integer> deque;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        deque = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            deque.offerLast(i);
        }

        st = new StringTokenizer(br.readLine());
        int count = 0;

        while (m-- > 0) {
            int find = Integer.parseInt(st.nextToken());
            int findIdx = deque.indexOf(find);

            if (deque.peekFirst() == find) {
                deque.pollFirst();
                continue;
            }

            if (findIdx > deque.size() - findIdx) {
                count += operation3(find);
            } else {
                count += operation2(find);
            }
        }

        System.out.println(count);
    }

    public static int operation2(int find) {
        int count = 0;

        while (deque.peekFirst() != find) {
            int num = deque.pollFirst();
            deque.offerLast(num);
            count++;
        }

        deque.pollFirst();

        return count;
    }

    public static int operation3(int find) {
        int count = 0;

        while (deque.peekFirst() != find) {
            int num = deque.pollLast();
            deque.offerFirst(num);
            count++;
        }

        deque.pollFirst();

        return count;
    }
}
