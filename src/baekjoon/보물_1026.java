package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * N ≤ 50
 * 0 <= A, B <= 100
 * 시간 제한: 2초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NlogN)
 * 메모리: 14304 KB, 시간: 128 ms
 **************************************************************************************/

public class 보물_1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        Integer[] b = new Integer[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder()); // 내림 차순 정렬을 위해, wrapper 클래스인 Integer 로 변경

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += a[i] * b[i];
        }

        System.out.println(total);
    }
}
