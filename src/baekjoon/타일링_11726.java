package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * N ≤ 1,000
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 14340 KB, 시간: 100 ms
 **************************************************************************************/

public class 타일링_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;

            // dp[i]에 대해서
            // + dp[i-1] 을 해주는 이유 : dp[i-1] 을 만들 수 있는 방법에 마지막에 2x1 타일 놔두기
            // + dp[i-2] 을 해주는 이유 : dp[i-2] 을 만들 수 있는 방법에 마지막에 1x2 타일 2개 상하로 놔두기
            // 따라서 점화식이 dp[i] = dp[i-1] + dp[i-2] 가 나옴
        }
        System.out.print(dp[n]);
    }
}
