package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간복잡도: O(N * W)
 * 메모리: 15320 KB, 시간: 140 ms
 **************************************************************************************/

public class 트럭_13335 {
    private static int n, w, l;
    private static int[] truck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        truck = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {           // O(N)
            truck[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        int weightSum = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {           // O(N)
            while (true) {                      // O(W)
                if (queue.size() == w) {
                    weightSum -= queue.poll(); // 맨 앞의 트럭 제거
                }

                if (truck[i] + weightSum <= l) break;
                queue.offer(0);             // 무게가 l을 넘는 경우
                time++;
            }
            queue.offer(truck[i]);
            weightSum += truck[i];
            time++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(time + w);
        System.out.println(sb);                 // 마지막 트럭이 건너는 시간(w) 추가
    }
}
