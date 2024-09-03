package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 1,000
 * 시간 제한: 0.5초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 14624 KB, 시간: 108 ms
 **************************************************************************************/

public class RGB거리_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] minCost = new int[n][3];
        minCost[0][0] = cost[0][0];
        minCost[0][1] = cost[0][1];
        minCost[0][2] = cost[0][2];

        for (int i = 1; i < n; i++) {
            minCost[i][0] = Math.min(minCost[i - 1][1], minCost[i - 1][2]) + cost[i][0];
            minCost[i][1] = Math.min(minCost[i - 1][0], minCost[i - 1][2]) + cost[i][1];
            minCost[i][2] = Math.min(minCost[i - 1][0], minCost[i - 1][1]) + cost[i][2];
        }

        int result = Math.min(minCost[n - 1][0], Math.min(minCost[n - 1][1], minCost[n - 1][2]));
        System.out.print(result);
    }
}
