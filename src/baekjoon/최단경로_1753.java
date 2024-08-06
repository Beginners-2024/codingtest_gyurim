package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(ElogV)
 * 메모리: 108520 KB, 시간: 788 ms
 **************************************************************************************/

public class 최단경로_1753 {
    private static int[] distance;
    private static boolean[] visit;
    private static List<Edge>[] graph;

    private static class Edge implements Comparable<Edge> {
        int dest;
        int w;

        Edge(int dest, int w) {
            this.dest = dest;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.w == o.w) {
                return Integer.compare(this.dest, o.dest); // 2순위: 도착 노드 id가 작은 애 먼저
            } else return Integer.compare(this.w, o.w); // 1순위: 가중치 작은 애 먼저
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        visit = new boolean[v + 1];

        graph = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        distance = new int[v + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, w));
        }

        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (distance[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(distance[i]).append("\n");
        }

        System.out.print(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visit[cur.dest]) continue;
            visit[cur.dest] = true;

            // 최단 거리 갱신
            for (Edge next : graph[cur.dest]) {
                if (distance[next.dest] > distance[cur.dest] + next.w) {
                    distance[next.dest] = distance[cur.dest] + next.w;
                    pq.offer(new Edge(next.dest, distance[next.dest]));
                }
            }
        }
    }
}
