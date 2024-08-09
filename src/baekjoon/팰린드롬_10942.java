package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 2초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N * N + M)
 * 메모리: 229944 KB, 시간: 728 ms
 **************************************************************************************/

public class 팰린드롬_10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {          // 거리 1
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;

                if (len == 2) {                 // 거리 2
                    if (arr[i] == arr[j]) dp[i][j] = 1;
                } else {                        // 거리 2 이상
                    if (arr[i] == arr[j]) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (m-- > 0) { // O(M)
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // check 팰린드롬
            sb.append(dp[s][e]).append("\n");
        }

        System.out.print(sb);
    }
}
