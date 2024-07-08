package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 1,000,000 = 10^6
 * -10^9 ≤ Xi ≤ 10^9
 * 시간 제한: 2초
 *
 * list.indexOf(x[i]) 는 선형탐색이므로 O(N^2)가 된다. 따라서 시간초과 발생함
 * HashMap.containsKey()의 시간복잡도는 평균 O(1)이지만, 최악의 경우 O(n)이 된다. 하지만 이는 매우 드문 상황
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(sorted 배열 정렬 시간) = O(nlogn)
 * 메모리: 267152 KB, 시간: 1080 ms
 **************************************************************************************/

public class 좌표압축_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] x = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        int[] sorted = x.clone();
        Arrays.sort(sorted); // O(nlogn)

        // 압축: 자신의 값보다 더 작은 값을 가진 좌표의 개수
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int num : sorted) {
            if (!map.containsKey(num)) {  // map 에는 중복된 num 값이 들어가지 않게 됨 // containsKey() 시간복잡도: 평균 O(1)
                map.put(num, index++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map.get(x[i])).append(" ");
        }

        System.out.println(sb);
    }
}
