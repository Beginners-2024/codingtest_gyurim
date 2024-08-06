package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 0.1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NlogN)
 * 메모리: 29592 KB, 시간: 344 ms
 **************************************************************************************/

public class 가운데를_말해요_1655 {
    private static int n;
    private static PriorityQueue<Integer> minHeap;
    private static PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) { // O(N)
            int num = Integer.parseInt(br.readLine());
            sb.append(getMiddle(num)).append("\n"); // O(logN)
        }
        System.out.print(sb);
    }

    private static int getMiddle(int num) { // O(logN)을 넘지 않도록 구현
        // minHeap.peek() => index[middle + 1] 저장
        // maxHeap.peek() => index[middle] 저장

        if (maxHeap.size() == minHeap.size()) {
            // renew
            if (maxHeap.size() == 0) maxHeap.offer(num);
            else {
                int nextMiddle = minHeap.peek();

                if (num > nextMiddle) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(num);
                } else {
                    maxHeap.offer(num);
                }
            }
        } else { // maxHeap.size() != minHeap.size()
            if (maxHeap.size() > minHeap.size()) {
                int middle = maxHeap.peek();

                if (middle > num) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(num);
                } else {
                    minHeap.offer(num);
                }
            }
        }

        return maxHeap.peek();
    }
}
