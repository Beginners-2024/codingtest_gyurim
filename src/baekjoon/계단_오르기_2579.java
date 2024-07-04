package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * n <= 300
 * step[i] <= 10,000
 * 시간 제한: 1초
 *
 * O(N) = 300 < 10^9
 * O(N^3) = (10^2)^3 < 10^9
 * 따라서 O(N^3) 시간복잡도까지는 시간초과 안 걸림 (백트래킹 300^300 혹은 2^300 => 시간초과 걸림)
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 14216 KB, 시간: 124ms (0.124초)
 **************************************************************************************/

public class 계단_오르기_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] step = new int[301];
        int[] dp = new int[301];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            step[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = step[1];
        dp[2] = step[1] + step[2];
        dp[3] = Math.max(step[1], step[2]) + step[3];

        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + step[i - 1]) + step[i];
        }

        System.out.println(dp[n]);
    }
}
