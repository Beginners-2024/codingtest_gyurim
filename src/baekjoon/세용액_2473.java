package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 3 <= N <= 5,000
 * -1,000,000,000 <= list[i] <= 1,000,000,000
 *
 * long sum = list.get(i) + list.get(left) + list.get(right);
 *  : long을 사용한다면, 모든 인풋과 아웃풋의 자료형을 long으로 통일시켜줘서, 형변환으로 인한 오류가 없도록 대비!!
 *  : 39% 에서 틀려서 QnA 확인해봤더니 .. 자동 형변환 오류 있는 거 같았음 ..
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 메모리: 17308 KB, 시간: 288 ms
 **************************************************************************************/

public class 세용액_2473 {
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Long> list = new ArrayList<>(); // long 값 저장

        for (long i = 0; i < n; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(list);
        long sValue = Long.MAX_VALUE;
        List<Integer> index = new ArrayList<>();

        // long 자료형 사용
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = list.get(i) + list.get(left) + list.get(right);

                if (sValue > Math.abs(sum)) {
                    sValue = Math.abs(sum);

                    index.clear();
                    index.add(i);
                    index.add(left);
                    index.add(right);
                }

                if (sum > 0) { // 세 수의 합이 0보다 크다면 -> right 값 줄여야 함
                    right--;
                } else {
                    left++;
                }
            }
        }

        for (Integer idx : index) {
            System.out.print(list.get(idx) + " ");
        }
    }
}
