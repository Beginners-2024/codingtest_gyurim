package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(ElogV)
 * 메모리: 51532 KB, 시간: 576 ms
 **************************************************************************************/

public class 최소비용_구하기2_11779 {
    private static class Edge implements Comparable<Edge> {
        int node;
        long cost;

        Edge(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost == o.cost) return Integer.compare(this.node, o.node);
            else return Long.compare(this.cost, o.cost);
        }
    }

    private static List<Edge>[] graph;
    private static boolean[] visit;
    private static long[] distance;
    private static int[] prevCity; // 최단 경로를 구성하는 직전 도시
    // prevCity[A] = B
    //      - 최단 경로: 시작 도시 > (생략) > B 도시 > A 도시

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        prevCity = new int[n + 1];

        distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        sb.append(distance[end]).append("\n");

        List<Integer> route = new ArrayList<>(); // start ~ end 도시 까지 경로 저장
        int city = end;
        route.add(end);

        while (city != start) {
            route.add(prevCity[city]);
            city = prevCity[city];
        }

        sb.append(route.size()).append("\n");
        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }
        System.out.print(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance[start] = 0L;
        prevCity[start] = -1;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visit[cur.node]) continue;
            visit[cur.node] = true;

            for (Edge next : graph[cur.node]) {
                if (distance[next.node] > distance[cur.node] + next.cost) {
                    distance[next.node] = distance[cur.node] + next.cost;
                    prevCity[next.node] = cur.node;
                    pq.offer(new Edge(next.node, distance[next.node]));
                }
            }
        }
    }
}
