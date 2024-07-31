package baekjoon;

/**************************************************************************************
 * Deque 시간복잡도: O(1)
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 20228 KB, 시간: 396 ms
 **************************************************************************************/

import java.io.*;
import java.util.*;

public class 덱_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Deque<Integer> deque = new ArrayDeque<>();

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("push_front")) {
                int x = Integer.parseInt(st.nextToken());
                deque.offerFirst(x);
            } else if (cmd.equals("push_back")) {
                int x = Integer.parseInt(st.nextToken());
                deque.offerLast(x);
            } else if (cmd.equals("pop_front")) {
                if (deque.size() > 0) {
                    System.out.println(deque.pollFirst());
                } else {
                    System.out.println("-1");
                }
            } else if (cmd.equals("pop_back")) {
                if (deque.size() > 0) {
                    System.out.println(deque.pollLast());
                } else {
                    System.out.println("-1");
                }
            } else if (cmd.equals("size")) {
                System.out.println(deque.size());
            } else if (cmd.equals("empty")) {
                if (deque.size() == 0) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            } else if (cmd.equals("front")) {
                if (deque.size() == 0) {
                    System.out.println("-1");
                } else {
                    System.out.println(deque.peekFirst());
                }
            } else if (cmd.equals("back")) {
                if (deque.size() == 0) {
                    System.out.println("-1");
                } else {
                    System.out.println(deque.peekLast());
                }
            }
        }
    }
}
