package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 0 ≤ N ≤ 100,000
 * 0 ≤ K ≤ 100,000
 * 시간 제한: 2초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(3 * MAX) = O(MAX)
 * 메모리: 18700 KB, 시간: 164 ms
 **************************************************************************************/

public class 숨바꼭질_1697 {
    private static int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, k));
    }

    private static int bfs(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);

        int[] dp = new int[MAX];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 세 가지 이동을 시도
            // 1. x -> x - 1
            if (cur - 1 >= 0) {
                if (dp[cur - 1] > dp[cur] + 1) {
                    dp[cur - 1] = dp[cur] + 1;
                    queue.offer(cur - 1);
                }
            }

            // 2. x -> x + 1
            if (cur + 1 < MAX) {
                if (dp[cur + 1] > dp[cur] + 1) {
                    dp[cur + 1] = dp[cur] + 1;
                    queue.offer(cur + 1);
                }
            }

            // 3. x -> 2 * x
            if (2 * cur > 0 && 2 * cur < MAX) {
                if (dp[2 * cur] > dp[cur] + 1) {
                    dp[2 * cur] = dp[cur] + 1;
                    queue.offer(2 * cur);
                }
            }
        }

        return dp[k];
    }

}
