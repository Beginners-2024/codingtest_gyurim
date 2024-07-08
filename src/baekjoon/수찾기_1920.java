package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000
 * 1 ≤ M ≤ 100,000
 * -2^31 <= A[i] <= 2^31
 *
 * 만약 A[i]에 특정 수가 포함되어 있는지 N개의 수를 조회한다면, 최대 100,000번을 조회해야함.
 * 이때, 특정 수는 M개가 있기에 100,000 * 100,000 = 10^10 > 10^9 => 시간초과 발생
 * MlogN => 10^5 * 5 < 10^9 => 시간초과 발생 X
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(MlogN)
 * 메모리: 48644 KB, 시간: 708 ms
 **************************************************************************************/

public class 수찾기_1920 {
    private static int n;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        A = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(getExistState(num) + "\n");
        }
        System.out.println(sb);
    }

    private static int getExistState(int num) {
        int left = 0;
        int right = n - 1;
        int idx = (left + right) / 2;

        while (left <= right) {
            idx = (left + right) / 2;

            if (A[idx] > num) {
                right = idx - 1;
            } else if (A[idx] < num) {
                left = idx + 1;
            } else {
                return 1;
            }
        }

        return 0;
    }
}
