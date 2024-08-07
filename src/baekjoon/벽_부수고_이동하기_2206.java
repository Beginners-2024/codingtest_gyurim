package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 2초
 *
 * [회고]
 * 반례 찾는 게 넘 어렵다.. 골드 3 이상 문제는 일반적인 조회 1번만 수행한다면 틀리는 군 ..
 *
 * # 반례
 * 6 5
 * 00000
 * 11110
 * 00000
 * 01111
 * 01111
 * 00010
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N*M)
 * 메모리: 174112 KB, 시간: 796 ms
 **************************************************************************************/

public class 벽_부수고_이동하기_2206 {
    private static int n, m;
    private static int[][] map;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static class Cell {
        int x, y;
        int broken;

        Cell(int x, int y, int broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        System.out.print(getDistance());
    }

    private static int getDistance() {
        int[][][] distance = new int[n][m][2];
        // distance[i][j][0]: 0만 지나온 경우의 거리
        // distance[i][j][1]: 벽(1)도 지나온 경우의 거리

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j][0] = Integer.MAX_VALUE;
                distance[i][j][1] = Integer.MAX_VALUE;
            }
        }

        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(0, 0, 0));
        distance[0][0][0] = 1; // (0, 0)은 map[0][0] = 0이기에, 거리 1 설정

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (oob(nx, ny)) continue;

                if (map[nx][ny] == 0) { // 이동할 수 있는 칸인 경우
                    // (nx, ny) 까지 오는 경로에 '0'만 있었다면 => 이전의 distance[0] + 1 값과 비교해, 현재 distance[0]을 세팅
                    if (distance[cur.x][cur.y][0] != Integer.MAX_VALUE && distance[nx][ny][0] > distance[cur.x][cur.y][0] + 1) {
                        distance[nx][ny][0] = distance[cur.x][cur.y][0] + 1;
                        queue.offer(new Cell(nx, ny, cur.broken));
                    }
                    // (nx, ny) 까지 오는 경로에 '1'도 있었다면 => 이전의 distance[1] + 1 값과 비교해, 현재 distance[1]을 세팅
                    if (distance[cur.x][cur.y][1] != Integer.MAX_VALUE && distance[nx][ny][1] > distance[cur.x][cur.y][1] + 1) {
                        distance[nx][ny][1] = distance[cur.x][cur.y][1] + 1;
                        queue.offer(new Cell(nx, ny, cur.broken));
                    }
                } else if (map[nx][ny] == 1) { // 벽인 경우
                    // (nx, ny) 까지 오는 경로에 '0'만 있었다면 => 이전의 distance[0] + 1 값과 비교해, 현재 distance[1]을 세팅
                    if (distance[cur.x][cur.y][0] != Integer.MAX_VALUE && distance[nx][ny][1] > distance[cur.x][cur.y][0] + 1) {
                        distance[nx][ny][1] = distance[cur.x][cur.y][0] + 1;
                        queue.offer(new Cell(nx, ny, cur.broken + 1));
                    }

                    // (nx, ny) 까지 오는 경로에 '1'도 있었다면 => 이전에 이미 칸을 지나왔기에 현재 칸(nx, ny)을 지날 수 없음
                }
            }
        }

        int arrivedDistance = Math.min(distance[n - 1][m - 1][0], distance[n - 1][m - 1][1]);
        if (arrivedDistance == Integer.MAX_VALUE) arrivedDistance = -1;

        return arrivedDistance;
    }

    private static boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}
