package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ n ≤ 10^6
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 51044 KB, 시간: 1328 ms
 **************************************************************************************/

public class 회사에_있는_사람_7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 역순 Comparator 를 사용하여 TreeMap 생성
        Map<String, Boolean> log = new TreeMap<>(Comparator.reverseOrder());

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) { // O(n)
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();

            if (status.equals("leave")) { // 자바의 문자열 비교
                log.put(name, false); // 평균: O(1)
            } else {
                log.put(name, true); // O(1)
            }
        }

        log.forEach((key, value) -> { // O(n)
            if (value) {
                System.out.println(key);
            }
        });
    }
}
