package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 메모리: 14648 KB, 시간: 108 ms
 **************************************************************************************/

public class 동전_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> coins = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                coins.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine());
            int[] dp = new int[m + 1];

            // n 가지 동전으로 금액 m을 만드는 모든 방법의 수
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                int coin = coins.get(i);

                for (int j = coin; j <= m; j++) {
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
            System.out.println(dp[m]);
        }
    }
}
