package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ R, C ≤ 1000
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(불 bfs: RC + 지훈 bfs: RC) = O(RC)
 * 메모리: 78044 KB, 시간: 588 ms
 **************************************************************************************/

public class 불_4179 {
    private static int r, c;
    private static char[][] maze;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] fireTime; // 불이 전파된 시점을 저장

    private static class Index {
        int x, y;
        int time;

        Index(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        maze = new char[r][c];
        fireTime = new int[r][c];

        int jr = 0;
        int jc = 0;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String mazeRow = st.nextToken();

            for (int j = 0; j < c; j++) {
                maze[i][j] = mazeRow.charAt(j);

                if (maze[i][j] == 'J') {
                    jr = i;
                    jc = j;
                }
            }
        }

        moveFire();
        int escapeTime = moveJihoon(jr, jc);

        if (escapeTime == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(escapeTime);
    }

    private static int moveJihoon(int x, int y) {
        int minEscapeTime = Integer.MAX_VALUE;
        boolean[][] visit = new boolean[r][c];

        Queue<Index> queue = new LinkedList<>();
        queue.add(new Index(x, y, 1));
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            Index cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (oob(nx, ny)) { // 지훈이가 탈출한 경우
                    minEscapeTime = Math.min(minEscapeTime, cur.time);
                } else {
                    if (maze[nx][ny] == '.' && !visit[nx][ny]) {
                        if (fireTime[nx][ny] != 0 && cur.time + 1 >= fireTime[nx][ny]) continue; // 가려고 하는 위치가 이미 불난 상황
                        queue.add(new Index(nx, ny, cur.time + 1));
                        visit[nx][ny] = true;
                    }
                }
            }
        }

        if (minEscapeTime == Integer.MAX_VALUE) return -1;
        return minEscapeTime;
    }

    private static void moveFire() {
        Queue<Index> fireQueue = initQueue(new LinkedList<>());

        while (!fireQueue.isEmpty()) {
            Index cur = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!oob(nx, ny) && maze[nx][ny] != '#' && fireTime[nx][ny] == 0) {
                    fireTime[nx][ny] = cur.time + 1;
                    fireQueue.add(new Index(nx, ny, cur.time + 1));
                }
            }
        }
    }

    private static Queue<Index> initQueue(Queue<Index> queue) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze[i][j] == 'F') {
                    queue.add(new Index(i, j, 1));
                    fireTime[i][j] = 1;
                }
            }
        }
        return queue;
    }

    private static boolean oob(int x, int y) {
        return x >= r || y >= c || x < 0 || y < 0;
    }
}
