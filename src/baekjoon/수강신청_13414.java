package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ K ≤ 100,000
 * 1 ≤ L ≤ 500,000
 * 시간 제한: 1초
 *
 * [알게 된 개념]
 * 1. LinkedHashSet
 *   - HashSet은 삽입 순서를 유지하지 않기 때문에, 삽입 순서를 유지하고 싶다면 LinkedHashSet을 사용해야 함
 *   - 시간 복잡도
 *     - 삽입: O(1) (add)
 *     - 삭제: O(1) (remove)
 *     - 탐색: O(1) (contains)
 *     - 순회: O(N) (iterator)
 *   - 이 자료구조는 삽입 순서를 유지하면서도 HashSet의 상수 시간 성능을 제공!
 *   - 그렇기 때문에, 추가적으로 순서 유지를 위한 링크드 리스트를 관리하므로 약간의 메모리 오버헤드 발생함
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(l + k)
 * 메모리: 88884 KB, 시간: 1460 ms
 **************************************************************************************/

public class 수강신청_13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Set<String> set = new LinkedHashSet<>(); // 삽입 순서를 유지하는 Set의 구현체

        for (int i = 0; i < l; i++) {       // O(l)
            st = new StringTokenizer(br.readLine());
            String number = st.nextToken();

            if (set.contains(number)) {     // O(1)
                set.remove(number);         // O(1)
                set.add(number);            // O(1)
            } else {
                set.add(number);
            }
        }

        int count = 0;
        for (String number : set) {         // O(k)
            if (count == k) break;
            System.out.println(number);
            count++;
        }
    }
}
