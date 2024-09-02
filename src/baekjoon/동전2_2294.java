package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 메모리: 14352 KB, 시간: 124 ms
 **************************************************************************************/

public class 동전2_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n + 1];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        // 동전을 사용해서, 가치의 합이 k원이 되도록 + 동전의 개수가 최소가 되도록
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                int coin = coins[j];
                if (i - coin >= 0) {
                    if (dp[i - coin] == Integer.MAX_VALUE) continue;
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if (dp[k] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
