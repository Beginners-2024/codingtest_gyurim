package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 50
 * 1 ≤ M ≤ 50
 * 1 ≤ K ≤ 2500
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(t * 4 * NM) = O(t * NM)
 * 메모리: 15820 KB, 시간: 168 ms
 **************************************************************************************/

public class 유기농_배추_1012 {
    private static int n, m;
    private static int[][] field;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            field = new int[n][m];
            visit = new boolean[n][m];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                field[x][y] = 1;
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visit[i][j] && field[i][j] == 1) {
                        visit[i][j] = true;
                        dfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!oob(nx, ny) && !visit[nx][ny] && field[nx][ny] == 1) {
                visit[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
