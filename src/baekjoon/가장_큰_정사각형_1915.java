package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N, M ≤ 1,000
 *
 * 시간 제한: 1초
 * O(N * M * min(N, M)) 시간복잡도 걸리므로 최악의 경우 O(10^9)가 되어 간당간당하게 시간 초과 안 걸림
 *
 * [회고]
 * dp 너무 어렵다 .. 처음엔 3차원 배열(dp[x][y][row])을 활용하여 정사각형의 크기를 구해주려고 했는데 메모리 초과 걸렸음
 * 해결할 아이디어가 생각나지 않아 구글링 함 ..
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N * M * min(N, M))
 * 메모리: 67696 KB, 시간: 1768 ms
 **************************************************************************************/

public class 가장_큰_정사각형_1915 {
    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String row = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(row.charAt(j - 1)));
            }
        }

        System.out.print(getSquare());
    }

    private static int getSquare() {
        int[][] dp = new int[n + 1][m + 1];

        // 부분합 채워주기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
            }
        }

        int ans = 0;

        // (1, 1) 부터 (n, m) 위치에 따라 가능한 정사각형의 크기를 구해줌
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (map[i][j] == 1) {
                    int row = Math.min(n, m);
                    int res = 1;

                    // 정사각형 변의 길이
                    for (int r = 1; r <= row; r++) {
                        if (i + r > n || j + r > m) break;
                        int size = dp[i + r][j + r] - dp[i + r][j - 1] - dp[i - 1][j + r] + dp[i - 1][j - 1];

                        // 만약 (r+1) 변 길이의 정사각형을 만들 수 없다면, (r+1) 이상의 정사각형을 못 만드므로 종료 처리
                        if (size != (r + 1) * (r + 1)) break;
                        res = size;
                    }
                    ans = Math.max(res, ans);
                }
            }
        }
        return ans;
    }
}
