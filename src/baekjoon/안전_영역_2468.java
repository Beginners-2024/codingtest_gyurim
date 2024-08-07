package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(100 * (V+E)) = O(100 * N * N)
 * 메모리: 19112 KB, 시간: 216 ms
 **************************************************************************************/

public class 안전_영역_2468 {
    private static int n;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static boolean[][] visit;
    private static int[][] area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        area = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSafeArea = 0;
        for (int height = 0; height <= 100; height++) { // 비가 안 오는 경우도 있기 떄문에, 0부터 시작해줘야 함
            visit = new boolean[n][n];
            int safeArea = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j] && area[i][j] > height) {
                        visit[i][j] = true;
                        dfs(i, j, height);
                        safeArea++;
                    }
                }
            }
            maxSafeArea = Math.max(maxSafeArea, safeArea);
        }

        System.out.println(maxSafeArea);
    }

    private static void dfs(int x, int y, int height) { // O(V+E)
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (oob(nx, ny)) continue;
            if (visit[nx][ny]) continue;
            if (area[nx][ny] <= height) continue;

            visit[nx][ny] = true;
            dfs(nx, ny, height);
        }
    }

    private static boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}
