package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *2 ≤ M ≤ 100
 * 2 ≤ N ≤ 100
 * 1 ≤ H ≤ 100
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(HMN)
 * 메모리: 118356 KB, 시간: 768 ms
 **************************************************************************************/

public class 토마토_7569 {
    private static int[][][] tomatoes;
    private static boolean[][][] visit;

    private static int N, M, H;
    private static int unRipeTomato = 0; // 초기값으로 주어진 덜 익은 토마토의 개수 체크
    private static int changedRiped = 0; // 프로그램을 수행하며 unRipe -> ripe 으로 바뀐 토마토의 개수 체크
    private static List<Index> ripeTomato; // 오늘 unRipe -> ripe로 바뀐 토마토 리스트 (내일 전파될 토마토)

    // 0: 앞, 1: 뒤, 2: 위, 3: 아래, 4: 오른쪽, 5: 왼쪽
    private static int[] dh = {-1, 1, 0, 0, 0, 0};
    private static int[] dx = {0, 0, 1, -1, 0, 0};
    private static int[] dy = {0, 0, 0, 0, 1, -1};

    private static class Index {
        int h;
        int x, y;

        Index(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomatoes = new int[H][N][M];
        visit = new boolean[H][N][M];
        ripeTomato = new ArrayList<>();

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomatoes[k][i][j] = Integer.parseInt(st.nextToken());

                    if (tomatoes[k][i][j] == 0) {
                        unRipeTomato++; // 익지 않은 토마토 개수
                    } else if (tomatoes[k][i][j] == 1) {
                        ripeTomato.add(new Index(k, i, j));
                        visit[k][i][j] = true;
                    }
                }
            }
        }


        if (unRipeTomato == 0) { // 저장된 모든 토마토가 익어있는 상태
            System.out.println(0);
        } else {
            int count = 0;  // 토마토가 모두 익을 때까지 걸리는 기간

            while (true) { // O(HNM)
                runRiped();
                if (ripeTomato.size() == 0) break; // 내일 전파될 토마토의 리스트가 비어있는 경우. 즉, 더이상 전파가 불가능한 상황일 때 while 문 탈출
                count++;
            }

            if (changedRiped != unRipeTomato) { // 프로그램을 수행했음에도 익지 않은 토마토가 있음
                System.out.println(-1);
            } else {
                System.out.println(count);
            }
        }
    }

    private static void runRiped() {
        List<Index> riped = new ArrayList<>(); // 오늘 익혀질 토마토. 즉, 내일 ripe 상태를 전파할 토마토 리스트

        for (Index cur : ripeTomato) { // 오늘 ripe 상태를 전파할 익은 토마토 리스트를 순회하며 인접한 unRipe 토마토 탐지
            for (int i = 0; i < 6; i++) {
                int nh = cur.h + dh[i];
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (oob(nh, nx, ny)) continue;
                if (visit[nh][nx][ny]) continue;

                if (tomatoes[nh][nx][ny] == 0) {
                    visit[nh][nx][ny] = true;

                    Index next = new Index(nh, nx, ny);
                    riped.add(next);
                }
            }
        }

        changedRiped += riped.size(); // 오늘 익혀진 토마토의 개수를 더해줌

        for (Index cur : riped) {
            tomatoes[cur.h][cur.x][cur.y] = 1;
        }

        ripeTomato = new ArrayList<>(riped); // 내일을 위한 토마토 리스트로 초기화
    }

    private static boolean oob(int h, int x, int y) {
        return h < 0 || x < 0 || y < 0 || h >= H || x >= N || y >= M;
    }
}
