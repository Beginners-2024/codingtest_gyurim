package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * [힌트]
 * 크기가 1x1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수
 * 즉, 변이 아닌 꼭짓점이 기준 !
 *
 * 1. 방향 구하기
 * 2. 꼭짓점 그리기
 * 3. 1x1 정사각형 구하기
 **************************************************************************************/

public class 드래곤_커브_15685 {
    private static boolean[][] map;
    private static int N = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        map = new boolean[N + 1][N + 1];

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, getDirections(d, g));
        }

        System.out.println(getNumberOfSquares());
    }

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    // 들어온 방향을 체크하면서, 다음 세대의 방향을 결정
    private static List<Integer> getDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>(); // 방향을 담아줄 list 생성
        directions.add(d); // 초기 d 입력

        while (g-- > 0) { // g 세대까지 반복
            for (int i = directions.size() - 1; i >= 0; i--) { // 마지막 방향 -> 처음 방향 순서로 반복
                int direction = (directions.get(i) + 1) % 4; // (기존의 d + 1) % 4: 반시계 방향으로 90도 회전
                directions.add(direction); // 새로운 방향 추가
            }
        }
        return directions;
    }

    private static int RIGHT = 0;
    private static int UP = 1;
    private static int LEFT = 2;
    private static int DOWN = 3;

    // getDirections()을 통해 구한 방향을 가지고 꼭짓점을 그림
    // 각 꼭짓점을 그려줄 맵을 이중 배열로 선언
    private static void draw(int x, int y, List<Integer> directions) {
        map[x][y] = true;

        for (Integer dir : directions) {
            if (dir == RIGHT) {
                map[++x][y] = true;
            } else if (dir == UP) {
                map[x][--y] = true;
            } else if (dir == LEFT) {
                map[--x][y] = true;
            } else if (dir == DOWN) {
                map[x][++y] = true;
            }
        }
    }

    private static int getNumberOfSquares() {
        int count = 0;
        for (int x = 0; x < N; x++) { // 0 ~ 100 인덱스 존재 (x+1, y+1 <= 100 위해, < N 부등호)
            for (int y = 0; y < N; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1]) {
                    count++;
                }
            }
        }
        return count;
    }
}
