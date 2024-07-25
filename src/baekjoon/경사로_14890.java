package baekjoon;

import java.io.*;
import java.util.*;

public class 경사로_14890 {
    private static int N, L;
    private static int[][] map;
    private static boolean[][] slope;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        slope = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int roadCount = 0;

        // 열 - 길의 갯수를 체크
        for (int i = 0; i < N; i++) {
            boolean isRoad = true;

            for (int j = 1; j < N; j++) {
                int prev = map[j - 1][i];
                int cur = map[j][i];

                if (slope[j][i]) continue; // 이미 슬로프가 놓여있다면, 슬로프 고려 대상 아님

                if (!isLayColumnSlope(j, i, prev, cur)) {
                    isRoad = false;
                    break;
                }
            }
            if (isRoad) roadCount++;
        }

        // slope 초기화
        slope = new boolean[N][N];

        // 행 - 길의 갯수를 체크
        for (int i = 0; i < N; i++) {
            boolean isRoad = true;

            for (int j = 1; j < N; j++) {
                int prev = map[i][j - 1];
                int cur = map[i][j];

                if (slope[i][j]) continue;

                if (!isLayRowSlope(i, j, prev, cur)) {
                    isRoad = false;
                    break;
                }
            }

            if (isRoad) roadCount++;

            for (int j = 0; j < N; j++) {
                slope[i][j] = false;
            }
        }
        System.out.println(roadCount);
    }

    private static boolean isLayRowSlope(int x, int y, int prev, int cur) {
        if (prev + 1 == cur) { // (1, 2) 처럼 올라가는 형태
            for (int diff = 1; diff <= L; diff++) {
                int ny = y - diff;

                if (oob(x, ny)) return false;
                if (map[x][ny] != prev) return false;
                if (slope[x][ny]) return false;
            }

            // 슬로프 설치 가능
            for (int diff = 1; diff <= L; diff++) {
                int ny = y - diff;
                slope[x][ny] = true;
            }
        } else if (prev == cur + 1) { // (2, 1) 처럼 내려가는 형태
            for (int diff = 1; diff <= L; diff++) {
                int ny = y + diff - 1;

                if (oob(x, ny)) return false;
                if (map[x][ny] != cur) return false;
                if (slope[x][ny]) return false;
            }

            // 슬로프 설치 가능
            for (int diff = 1; diff <= L; diff++) {
                int ny = y + diff - 1;
                slope[x][ny] = true;
            }
        } else return prev <= cur + 1 && prev + 1 >= cur;

        return true;
    }

    private static boolean isLayColumnSlope(int x, int y, int prev, int cur) {
        if (prev + 1 == cur) { // (1, 2) 처럼 올라가는 형태
            for (int diff = 1; diff <= L; diff++) {
                int nx = x - diff;

                if (oob(nx, y)) return false;
                if (map[nx][y] != prev) return false;
                if (slope[nx][y]) return false;
            }

            // 슬로프 설치 가능
            for (int diff = 1; diff <= L; diff++) {
                int nx = x - diff;
                slope[nx][y] = true;
            }
        } else if (prev == cur + 1) { // (2, 1) 처럼 내려가는 형태
            for (int diff = 1; diff <= L; diff++) {
                int nx = x + diff - 1;

                if (oob(nx, y)) return false;
                if (map[nx][y] != cur) return false;
                if (slope[nx][y]) return false;
            }

            // 슬로프 설치 가능
            for (int diff = 1; diff <= L; diff++) {
                int nx = x + diff - 1;
                slope[nx][y] = true;
            }
        } else return prev <= cur + 1 && prev + 1 >= cur;

        return true;
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
