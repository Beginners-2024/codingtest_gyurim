package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * N, M <= 500,000
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N + MlogM)
 * 메모리: 25928 KB, 시간: 312 ms
 **************************************************************************************/

public class 듣보잡_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) { // O(N)
            String name = br.readLine();
            set.add(name);
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) { // O(M)
            String name = br.readLine();

            if (set.contains(name)) { // O(1)
                list.add(name);
            }
        }

        sb.append(list.size()).append("\n");
        Collections.sort(list); // O(MlogM)

        for (String str : list) {
            sb.append(str).append("\n");
        }
        System.out.print(sb);
    }
}
