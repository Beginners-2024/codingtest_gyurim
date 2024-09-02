package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 메모리: 40168 KB, 시간: 356 ms
 **************************************************************************************/

public class 미세먼지_안녕_17144 {
    private static int[][] area;
    private static int r, c;

    private static int[] dx = {0, 0, 1, -1}; // 오, 왼, 하, 상
    private static int[] dy = {1, -1, 0, 0};

    private static int[][] airPurifierDir;

    private static List<Index> airPurifier;

    private static class Index {
        int x, y;

        Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        area = new int[r][c];
        airPurifier = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());

                if (area[i][j] == -1) {
                    airPurifier.add(new Index(i, j));
                }
            }
        }

        setAirPurifierDir();
        while (t-- > 0) {
            // 확산
            spreadDust();
            // 공기청정기 작동
            runAirPurifier();
        }

        System.out.print(countDust());
    }

    private static int countDust() {
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (area[i][j] == -1) continue;
                count += area[i][j];
            }
        }
        return count;
    }

    private static void spreadDust() {
        int[][] afterDust = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                afterDust[i][j] = area[i][j];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (area[i][j] > 0) {
                    int dust = area[i][j] / 5; // 확산될 먼지
                    int dir = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (!oob(nx, ny)) { // 범위 안
                            if (area[nx][ny] == -1) continue; // 공기청정기 존재

                            afterDust[nx][ny] += dust;
                            dir++;
                        }
                    }

                    if (afterDust[i][j] - (dust * dir) > 0) {
                        afterDust[i][j] -= (dust * dir);
                    } else afterDust[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                area[i][j] = afterDust[i][j];
            }
        }
    }

    private static void runAirPurifier() {
        int[][] afterAir = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (airPurifierDir[i][j] == 0) continue;
                if (area[i][j] == 0) continue;

                int dir = airPurifierDir[i][j] - 1;

                int nx = i + dx[dir];
                int ny = j + dy[dir];

                if (oob(nx, ny)) continue;

                afterAir[nx][ny] = area[i][j];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (area[i][j] == -1) continue;
                if (airPurifierDir[i][j] != 0) {
                    area[i][j] = afterAir[i][j];
                }
            }
        }
    }

    private static void setAirPurifierDir() {
        airPurifierDir = new int[r][c];

        // 1번 공기청정기 작동
        Index first = airPurifier.get(0);
        for (int i = first.y + 1; i < c - 1; i++) {
            airPurifierDir[first.x][i] = 1; // 오른쪽
        }

        for (int i = first.x; i > 0; i--) {
            airPurifierDir[i][c - 1] = 4; // 위쪽
        }

        for (int i = c - 1; i > 0; i--) {
            airPurifierDir[0][i] = 2; // 왼쪽
        }

        for (int i = 0; i < first.x; i++) {
            airPurifierDir[i][0] = 3; // 아래쪽
        }

        // 2번 공기청정기 작동
        Index second = airPurifier.get(1);
        for (int i = second.y + 1; i < c - 1; i++) {
            airPurifierDir[second.x][i] = 1; // 오른쪽
        }

        for (int i = second.x; i < r - 1; i++) { // 아래
            airPurifierDir[i][c - 1] = 3;
        }

        for (int i = c - 1; i > 0; i--) {
            airPurifierDir[r - 1][i] = 2; // 왼쪽
        }

        for (int i = r - 1; i > second.x; i--) {
            airPurifierDir[i][0] = 4; // 위쪽
        }

    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}
