package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 500,000
 *-10,000,000 <= 숫자 카드에 적힌 수 <= 10,000,000
 * 1 ≤ M ≤ 500,000
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(n + m)
 * 메모리: 199440 KB, 시간: 1316 ms
 **************************************************************************************/

public class 숫자카드2_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) { // O(m)
            int num = Integer.parseInt(st.nextToken());
            if (map.containsKey(num)) sb.append(map.get(num) + " "); // O(1)
            else sb.append("0 ");
        }
        System.out.println(sb);
    }
}