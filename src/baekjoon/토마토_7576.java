package baekjoon;

import java.util.*;
import java.io.*;

/**************************************************************************************
 * 2 ≤ M,N ≤ 1,000
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(V + E) = O(NM + 4NM) = O(NM)
 * 메모리: 120824 KB, 시간: 612ms
 **************************************************************************************/

public class 토마토_7576 {
    private static int n, m;
    private static int[][] board;
    private static boolean[][] visit;
    private static int lastRipeDay = 0;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static class Tomato {
        int x, y;
        int ripeDay;

        Tomato(int x, int y, int ripeDay) {
            this.x = x;
            this.y = y;
            this.ripeDay = ripeDay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모두 익을 때까지 최소 날짜
        bfs();

        // 안 익은 토마토 있는지 체크
        if (isUnRipeTomato()) {
            System.out.println("-1");
        } else {
            System.out.println(lastRipeDay);
        }
    }

    private static boolean isUnRipeTomato() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) return true;
            }
        }
        return false;
    }

    private static void bfs() {
        Queue<Tomato> queue = setRipeTomato(new LinkedList<>(), 0);

        while (!queue.isEmpty()) {
            Tomato cur = queue.poll();
            lastRipeDay = Math.max(lastRipeDay, cur.ripeDay);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!oob(nx, ny) && !visit[nx][ny] && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    visit[nx][ny] = true;
                    queue.add(new Tomato(nx, ny, cur.ripeDay + 1));
                }
            }
        }
    }

    private static Queue<Tomato> setRipeTomato(Queue<Tomato> queue, int ripeDay) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    queue.add(new Tomato(i, j, ripeDay));
                    visit[i][j] = true;
                }
            }
        }

        return queue;
    }

    private static boolean oob(int x, int y) {
        return x >= n || y >= m || x < 0 || y < 0;
    }
}
