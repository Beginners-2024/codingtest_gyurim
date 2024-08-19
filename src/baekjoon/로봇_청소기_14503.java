package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 메모리: 14436 KB, 시간: 108 ms
 **************************************************************************************/

public class 로봇_청소기_14503 {
    private static int n, m;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        // 0: 북, 1: 동, 2: 남, 3: 서

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(program(x, y, d));
    }

    private static class Index {
        int x, y;
        int d;

        Index(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    private static int program(int x, int y, int d) {
        int count = 0;

        Queue<Index> queue = new LinkedList<>();
        queue.offer(new Index(x, y, d));

        if (map[x][y] == 1) return count;

        while (!queue.isEmpty()) {
            Index cur = queue.poll();

            // 1. 현재 칸이 아직 청소되지 않은 경우 현재 칸 청소
            if (!visit[cur.x][cur.y]) {
                visit[cur.x][cur.y] = true;
                count++;
            }

            boolean canMove = false;
            int nd = cur.d;

            for (int i = 0; i < 4; i++) {
                nd = (nd + 3) % 4;
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];

                // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                if (!oob(nx, ny) && !visit[nx][ny] && map[nx][ny] == 0) {
                    canMove = true;
                    queue.offer(new Index(nx, ny, nd));
                    break;
                }
            }

            // 2. 현재 칸의 주변 4칸 중 처옷되지 않은 빈 칸이 없는 경우
            if (!canMove) {
                int nx = cur.x - dx[cur.d];
                int ny = cur.y - dy[cur.d];

                if (!oob(nx, ny) && map[nx][ny] == 0) {
                    queue.offer(new Index(nx, ny, cur.d));
                } else break;
            }
        }

        return count;
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
