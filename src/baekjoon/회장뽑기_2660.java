package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N * (E+V))
 * 메모리: 14220 KB, 시간: 104 ms
 **************************************************************************************/

public class 회장뽑기_2660 {
    private static List<Integer>[] member;
    private static int n;

    private static class Member {
        int num;
        int depth;

        Member(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        member = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            member[i] = new ArrayList<>();
        }

        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;
            member[a].add(b);
            member[b].add(a);
        }

        int[] grade = new int[n + 1];
        int minGrade = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            grade[i] = getGrade(i);
            minGrade = Math.min(minGrade, grade[i]);
        }

        List<Integer> candidates = new ArrayList<>(); // 회장 후보
        for (int i = 1; i <= n; i++) {
            if (minGrade == grade[i]) {
                candidates.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minGrade).append(" ").append(candidates.size());
        sb.append("\n");
        for (Integer cd : candidates) {
            sb.append(cd).append(" ");
        }
        System.out.print(sb);
    }

    private static int getGrade(int mem) { // O(E+V)
        boolean[] visit = new boolean[n + 1];
        visit[mem] = true;

        Queue<Member> queue = new LinkedList<>();
        queue.add(new Member(mem, 0));

        int maxDepth = 0;
        while (!queue.isEmpty()) {
            Member cur = queue.poll();
            maxDepth = Math.max(maxDepth, cur.depth);

            for (Integer next : member[cur.num]) {
                if (visit[next]) continue;
                visit[next] = true;
                queue.offer(new Member(next, cur.depth + 1));
            }
        }

        return maxDepth;
    }
}