package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 100
 * 1 ≤ L ≤ N
 *
 * 1. 0열 ~ N-1열까지, 길의 개수를 체크
 * 2. 0행 ~ N-1행까지, 길의 개수를 체크
 * 3. 길이 될 수 있는지 체크
 *      - 모든 칸을 체크하며,
 *          - 단차 차이가 있어 슬로프를 놓아야하는데 조건에 맞지 않아 슬로프를 놓지 못한다면 => 길이 될 수 없다 판정
 *          - 단차 차이가 없어 슬로프를 놓지 않아도 되는 경우거나, 슬로프를 놓을 수 있다면 => 길이 될 수 있다 판정
 *
 * 위 과정을 수행하며 길의 개수를 체크한 뒤, 출력
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N * N * L)
 * 메모리: 14920 KB, 시간: 156 ms
 **************************************************************************************/

public class 경사로_14890 {
    private static int N, L;
    private static int[][] map;         // 칸의 높이 저장
    private static boolean[][] slope;   // 슬로프 설치 여부 저장

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
        for (int i = 0; i < N; i++) { // O(NN)
            boolean isRoad = true;

            for (int j = 1; j < N; j++) {
                int prev = map[j - 1][i];   // 이전 칸의 길 높이
                int cur = map[j][i];        // 현재 칸의 길 높이

                // 이미 슬로프 설치되어 있다면, 슬로프 설치를 고려하지 않음
                if (slope[j][i]) continue;

                // 슬로프 설치할 수 있는 지 체크
                if (!isLayColumnSlope(j, i, prev, cur)) { // O(L)
                    isRoad = false;
                    break;
                }
            }
            if (isRoad) roadCount++;
        }

        // slope 초기화 (길은 행 or 열로 이루어져있기 때문에 이전에 설치된 슬로프가 다른 길의 슬로프에 영향을 줄 수 없음)
        slope = new boolean[N][N];

        // 행 - 길의 갯수를 체크
        for (int i = 0; i < N; i++) { // O(NN)
            boolean isRoad = true;

            for (int j = 1; j < N; j++) {
                int prev = map[i][j - 1];
                int cur = map[i][j];

                // 이미 슬로프 설치되어 있다면, 슬로프 설치를 고려하지 않음
                if (slope[i][j]) continue;

                // 슬로프 설치할 수 있는 지 체크
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

    private static boolean isLayRowSlope(int x, int y, int prev, int cur) { // O(L)
        // prev: 이전 칸의 높이
        // cur: 현재 칸의 높이
        // x, y: 현재 칸의 인덱스
        if (prev + 1 == cur) { // (1, 2) 처럼 올라가는 형태
            for (int diff = 1; diff <= L; diff++) { // L 개의 칸에 대해서, 슬로프를 놓을 수 있을 지 조건 체크
                int ny = y - diff;

                // 슬로프를 설치할 수 없는 조건 (이른 종료)
                if (oob(x, ny)) return false;
                if (map[x][ny] != prev) return false;
                if (slope[x][ny]) return false;
            }

            // 슬로프 설치 가능한 경우이기에 슬로프 설치
            for (int diff = 1; diff <= L; diff++) {
                int ny = y - diff; // 체크할 칸
                slope[x][ny] = true;
            }
        } else if (prev == cur + 1) { // (2, 1) 처럼 내려가는 형태
            for (int diff = 1; diff <= L; diff++) { // L 개의 칸에 대해서, 슬로프를 놓을 수 있을 지 조건 체크
                int ny = y + diff - 1; // 체크할 칸

                // 슬로프를 설치할 수 없는 조건 (이른 종료)
                if (oob(x, ny)) return false;
                if (map[x][ny] != cur) return false;
                if (slope[x][ny]) return false;
            }

            // 슬로프 설치 가능한 경우이기에 슬로프 설치
            for (int diff = 1; diff <= L; diff++) {
                int ny = y + diff - 1;
                slope[x][ny] = true;
            }
        } else return prev <= cur + 1 && prev + 1 >= cur; // prev와 cur의 차이가 2 이상이라면, false 리턴

        return true;
    }

    private static boolean isLayColumnSlope(int x, int y, int prev, int cur) { // O(L)
        // prev: 이전 칸의 높이
        // cur: 현재 칸의 높이
        // x, y: 현재 칸의 인덱스
        if (prev + 1 == cur) { // (1, 2) 처럼 올라가는 형태
            for (int diff = 1; diff <= L; diff++) { // L 개의 칸에 대해서, 슬로프를 놓을 수 있을 지 조건 체크
                int nx = x - diff; // 체크할 칸

                // 슬로프를 설치할 수 없는 조건 (이른 종료)
                if (oob(nx, y)) return false;
                if (map[nx][y] != prev) return false;
                if (slope[nx][y]) return false;
            }

            // 슬로프 설치 가능한 경우이기에 슬로프 설치
            for (int diff = 1; diff <= L; diff++) {
                int nx = x - diff;
                slope[nx][y] = true;
            }
        } else if (prev == cur + 1) { // (2, 1) 처럼 내려가는 형태
            for (int diff = 1; diff <= L; diff++) { // L 개의 칸에 대해서, 슬로프를 놓을 수 있을 지 조건 체크
                int nx = x + diff - 1; // 체크할 칸

                // 슬로프를 설치할 수 없는 조건 (이른 종료)
                if (oob(nx, y)) return false;
                if (map[nx][y] != cur) return false;
                if (slope[nx][y]) return false;
            }

            // 슬로프 설치 가능한 경우이기에 슬로프 설치
            for (int diff = 1; diff <= L; diff++) {
                int nx = x + diff - 1;
                slope[nx][y] = true;
            }
        } else return prev <= cur + 1 && prev + 1 >= cur; // prev와 cur의 차이가 2 이상이라면, false 리턴

        return true;
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
