package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 20
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 메모리: 39824 KB, 시간: 256 ms
 **************************************************************************************/

public class Easy2048_12100 {
    private static int n;
    private static int[][] board;
    private static int maxNum = 0;

    private static class BoardInfo {
        int[][] board;
        int moveCount;

        BoardInfo(int[][] board, int moveCount) {
            this.board = board;
            this.moveCount = moveCount;
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(maxNum);
    }

    private static void bfs() {
        int[][] copy = getBoard(board);
        Queue<BoardInfo> queue = new LinkedList<>();
        queue.add(new BoardInfo(copy, 0));

        while (!queue.isEmpty()) {
            BoardInfo cur = queue.poll();

            if (cur.moveCount == 5) { // 5번 이동해서 만들 수 있는 가장 큰 블록의 값 구하기
                maxNum = Math.max(maxNum, getBigNumber(cur.board));
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int[][] moved = moveBoard(i, cur.board);
                queue.add(new BoardInfo(moved, cur.moveCount + 1));
            }
        }
    }

    private static int getBigNumber(int[][] cur) {
        int big = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                big = Math.max(big, cur[i][j]);
            }
        }
        return big;
    }

    private static int[][] moveBoard(int dir, int[][] origin) {
        // dir == 0: 상, 1: 우, 2: 하, 3: 좌
        // 이미 합쳐진 칸은 또다시 합쳐질 수 없기에 큐로 관리
        int[][] copy = getBoard(origin);

        if (dir == 0) { // 상
            moveTop(copy);
        } else if (dir == 1) { // 우
            moveRight(copy);
        } else if (dir == 2) { // 하
            moveDown(copy);
        } else { // 좌
            moveLeft(copy);
        }

        return copy;
    }

    private static int[][] moveTop(int[][] copy) {
        for (int i = 0; i < n; i++) { // y축
            int x = 0;
            Queue<Index> queue = new LinkedList<>();

            for (int j = 0; j < n; j++) { // x축
                if (copy[j][i] == 0) continue; // 빈 칸

                queue.add(new Index(j, i));

                if (queue.size() == 2) {
                    Index cur = queue.poll();
                    Index next = queue.peek();
                    int num = copy[cur.x][cur.y];

                    if (num == copy[next.x][next.y]) { // 같으면 합치기
                        queue.poll();
                        copy[x][cur.y] = num * 2;
                        copy[next.x][next.y] = 0;
                        x++;
                    } else { // 서로 다르면 (2, 4, 0)
                        copy[x][cur.y] = num;
                        x++;
                    }
                }
            }

            if (!queue.isEmpty()) {
                Index last = queue.poll();
                copy[x][i] = copy[last.x][last.y];
                x++;
            }

            for (int j = x; j < n; j++) {
                copy[j][i] = 0;
            }
        }
        return copy;
    }

    private static int[][] moveRight(int[][] copy) {
        for (int i = 0; i < n; i++) {
            int y = n - 1;
            Queue<Index> queue = new LinkedList<>();

            for (int j = n - 1; j >= 0; j--) {
                if (copy[i][j] == 0) continue; // 빈 칸

                queue.add(new Index(i, j));

                if (queue.size() == 2) {
                    Index cur = queue.poll();
                    Index next = queue.peek();
                    int num = copy[cur.x][cur.y];

                    if (num == copy[next.x][next.y]) { // 같으면 합치기
                        queue.poll();
                        copy[cur.x][y] = num * 2;
                        copy[next.x][next.y] = 0;
                        y--;
                    } else { // 서로 다르면 (2, 4, 0)
                        copy[cur.x][y] = num;
                        y--;
                    }
                }
            }

            if (!queue.isEmpty()) {
                Index last = queue.poll();
                copy[i][y] = copy[last.x][last.y];
                y--;
            }

            for (int j = y; j >= 0; j--) {
                copy[i][j] = 0;
            }
        }
        return copy;
    }

    private static int[][] moveDown(int[][] copy) {
        for (int i = 0; i < n; i++) { // y축
            int x = n - 1;
            Queue<Index> queue = new LinkedList<>();

            for (int j = n - 1; j >= 0; j--) { // x축
                if (copy[j][i] == 0) continue; // 빈 칸

                queue.add(new Index(j, i));

                if (queue.size() == 2) {
                    Index cur = queue.poll();
                    Index next = queue.peek();
                    int num = copy[cur.x][cur.y];

                    if (num == copy[next.x][next.y]) { // 같으면 합치기
                        queue.poll();
                        copy[x][cur.y] = num * 2;
                        copy[next.x][next.y] = 0;
                        x--;
                    } else { // 서로 다르면 (2, 4, 0)
                        copy[x][cur.y] = num;
                        x--;
                    }
                }
            }

            if (!queue.isEmpty()) {
                Index last = queue.poll();
                copy[x][i] = copy[last.x][last.y];
                x--;
            }

            for (int j = x; j >= 0; j--) {
                copy[j][i] = 0;
            }
        }
        return copy;
    }

    private static int[][] moveLeft(int[][] copy) {
        for (int i = 0; i < n; i++) {
            int y = 0;
            Queue<Index> queue = new LinkedList<>();

            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0) continue; // 빈 칸

                queue.add(new Index(i, j));

                if (queue.size() == 2) {
                    Index cur = queue.poll();
                    Index next = queue.peek();
                    int num = copy[cur.x][cur.y];

                    if (num == copy[next.x][next.y]) { // 같으면 합치기
                        queue.poll();
                        copy[cur.x][y] = num * 2;
                        copy[next.x][next.y] = 0;
                        y++;
                    } else { // 서로 다르면 (2, 4, 0)
                        copy[cur.x][y] = num;
                        y++;
                    }
                }
            }

            if (!queue.isEmpty()) {
                Index last = queue.poll();
                copy[i][y] = copy[last.x][last.y];
                y++;
            }

            for (int j = y; j < n; j++) {
                copy[i][j] = 0;
            }
        }
        return copy;
    }

    private static int[][] getBoard(int[][] origin) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
    }
}
