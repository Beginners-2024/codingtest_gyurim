package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(T * (V+E)) (이떄, V = L*R*C, E = 6*V)
 * 메모리: 17628 KB, 시간: 144 ms
 **************************************************************************************/

public class 상범_빌딩_6593 {
    private static char[][][] building;
    private static Index origin;
    private static Index dest;
    private static int l, r, c;
    private static int[] dh = {-1, 1, 0, 0, 0, 0};
    private static int[] dx = {0, 0, 0, 0, 1, -1};
    private static int[] dy = {0, 0, 1, -1, 0, 0};

    private static class Index {
        int h; // 높이
        int x, y;
        int time;

        Index(int h, int x, int y, int time) {
            this.h = h;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        while (true) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) break;
            building = new char[l][r][c];

            for (int h = 0; h < l; h++) { // 층
                for (int i = 0; i < r; i++) {
                    String row = br.readLine();
                    for (int j = 0; j < c; j++) {
                        building[h][i][j] = row.charAt(j);

                        if (building[h][i][j] == 'S') {
                            origin = new Index(h, i, j, 0);
                        } else if (building[h][i][j] == 'E') {
                            dest = new Index(h, i, j, 0);
                        }
                    }
                }
                // 빈칸 예정
                br.readLine();
            }

            int time = escapeBuilding();
            if (time == -1) sb.append("Trapped!").append("\n");
            else sb.append("Escaped in ").append(time).append(" minute(s).").append("\n");
        }
        System.out.print(sb);
    }

    private static int escapeBuilding() { // O(V + E)
        boolean[][][] visit = new boolean[l][r][c];
        Queue<Index> queue = new LinkedList<>();
        queue.offer(origin);
        visit[origin.h][origin.x][origin.y] = true;

        int arrivedTime = -1;

        while (!queue.isEmpty()) {
            Index cur = queue.poll();

            if (cur.h == dest.h && cur.x == dest.x && cur.y == dest.y) {
                arrivedTime = cur.time;
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nh = cur.h + dh[i];
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (oob(nh, nx, ny)) continue;
                if (visit[nh][nx][ny]) continue;
                if (building[nh][nx][ny] == '#') continue;

                visit[nh][nx][ny] = true;
                queue.offer(new Index(nh, nx, ny, cur.time + 1));
            }
        }

        return arrivedTime;
    }

    private static boolean oob(int h, int x, int y) {
        return h < 0 || h >= l || x < 0 || x >= r || y < 0 || y >= c;
    }
}
