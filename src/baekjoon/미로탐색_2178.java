package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N, M ≤ 100
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NM)
 * 메모리: 14672 KB, 시간: 140 ms
 **************************************************************************************/

public class 미로탐색_2178 {
    private static int n, m;
    private static int[][] maze;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static class Index {
        int x, y, time;

        Index(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String mazeRow = st.nextToken();

            for (int j = 0; j < m; j++) {
                maze[i][j] = mazeRow.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        // (0, 0) -> (n-1, m-1)
        Queue<Index> queue = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];
        queue.add(new Index(0, 0, 1));
        visit[0][0] = true;

        int escapeTime = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Index cur = queue.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                escapeTime = Math.min(escapeTime, cur.time);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!oob(nx, ny) && maze[nx][ny] == 1 && !visit[nx][ny]) {
                    queue.add(new Index(nx, ny, cur.time + 1));
                    visit[nx][ny] = true;
                }
            }
        }

        return escapeTime;
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
