package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 50
 * 1 ≤ M ≤ 13
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 메모리: 19620 KB, 시간: 208 ms
 **************************************************************************************/

public class 치킨배달_15686 {
    private static int n;
    private static int m;
    private static int[][] city;
    private static List<Index> chickens;
    private static List<Index> homes;

    private static int minCityDistance = Integer.MAX_VALUE;

    private static class Index {
        int x, y;

        Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Chickens {
        List<Index> select;
        int num; // 다음 고를 치킨집의 넘버

        Chickens(List<Index> select, int num) {
            this.select = select;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];
        chickens = new ArrayList<>();
        homes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());

                if (city[i][j] == 1) { // 집
                    homes.add(new Index(i, j));
                } else if (city[i][j] == 2) { // 치킨
                    chickens.add(new Index(i, j));
                }
            }
        }

        // 치킨 집 최대 M개 -> 도시의 치킨 거리가 가장 작게 되는 프로그램
        bfs();
        System.out.println(minCityDistance);
    }

    private static void bfs() {
        // 도시의 치킨 거리를 가장 작게 하는 방법 탐색 !

        Queue<Chickens> queue = new LinkedList<>();
        queue.add(new Chickens(new ArrayList<>(), 0));

        while (!queue.isEmpty()) {
            Chickens cur = queue.poll();

            if (cur.select.size() == m) {
                // M개를 다 고른 뒤, 도시의 치킨 거리 구하기
                minCityDistance = Math.min(minCityDistance, getCityDistance(cur.select));
                continue;
            }

            for (int i = cur.num; i < chickens.size(); i++) {
                List<Index> selected = new ArrayList(cur.select);
                selected.add(chickens.get(i));
                queue.add(new Chickens(selected, i + 1));
            }
        }
    }

    private static int getCityDistance(List<Index> select) {
        int total = 0;
        for (Index home : homes) {
            int minDistance = Integer.MAX_VALUE; // 치킨 거리

            for (Index chicken : select) {
                minDistance = Math.min(minDistance, getDistance(home, chicken));
            }

            total += minDistance;
        }
        return total;
    }

    private static int getDistance(Index home, Index chicken) {
        return Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y);
    }
}
