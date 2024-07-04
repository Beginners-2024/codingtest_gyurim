package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 <= n <= 10^6
 * 시간 제한: 0.15초 (아마 자바는 좀 더 늘어난 듯)
 *
 * 따라서, 15,000,000 이내의 연산을 만족하는 알고리즘을 구현해야함
 * 최악 N = 10,000,000 이므로, 알고리즘은 O(N)을 만족하는 시간복잡도를 가져야함
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 20780 KB, 시간: 184 ms
 **************************************************************************************/

public class 일로_만들기_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[n] = 0;

        for (int i = n; i > 1; i--) {
            if (i % 3 == 0) {
                dp[i / 3] = Math.min(dp[i] + 1, dp[i / 3]);
            }

            if (i % 2 == 0) {
                dp[i / 2] = Math.min(dp[i] + 1, dp[i / 2]);
            }

            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }
        System.out.println(dp[1]);
    }
}
