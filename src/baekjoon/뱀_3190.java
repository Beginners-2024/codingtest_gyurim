package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 메모리: 14392 KB, 시간: 108 ms
 **************************************************************************************/

public class 뱀_3190 {
    private static int[][] map;
    private static int n;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Queue<Move> move; // 뱀 머리의 방향을 바꿀 정보 저장

    private static class Move {
        int time;
        char d;

        Move(int time, char d) {
            this.time = time;
            this.d = d;
        }
    }

    private static class Index {
        int x, y;

        Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1; // 사과 위치
        }

        move = new LinkedList<>();
        int l = Integer.parseInt(br.readLine());

        while (l-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            move.add(new Move(x, c));
        }

        System.out.println(program());
    }

    private static int program() {
        int time = 0;
        int d = 1; // 오른쪽 방향

        Deque<Index> snake = new ArrayDeque<>();
        snake.offer(new Index(1, 1));
        map[1][1] = 2;

        while (!snake.isEmpty()) {
            time++;
            // 뱀 머리 인덱스: (nx, ny)
            int nx = snake.peekLast().x + dx[d];
            int ny = snake.peekLast().y + dy[d];

            if (oob(nx, ny) || map[nx][ny] == 2) break; // 벽 or 뱀 자신을 만남 -> 종료 조건

            if (map[nx][ny] == 0) {
                Index tail = snake.pollFirst();
                map[tail.x][tail.y] = 0;
            }

            // 뱀 머리를 위치 시킴
            map[nx][ny] = 2;
            snake.offerLast(new Index(nx, ny));

            if (move.isEmpty()) continue; // 뱀 머리 방향을 더이상 바꿀 필요 X

            if (time == move.peek().time) { // 뱀 방향을 회전
                if (move.peek().d == 'L') {
                    d = (d + 3) % 4;
                } else if (move.peek().d == 'D') {
                    d = (d + 1) % 4;
                }
                move.poll();
            }
        }
        return time;
    }

    private static boolean oob(int x, int y) {
        return x <= 0 || y <= 0 || x > n || y > n;
    }
}
