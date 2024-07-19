package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ n, m ≤ 100,000
 * 시간 제한: 2초
 *
 * [알게 된 표현]
 * 1. for (char c : string.toCharArray()) { } // String 문자열을 순회하며 체크
 * 2. if (Character.isDigit(char)) { } // char 에 숫자 0~9가 포함되어 있는지 체크하는 메서드
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N + M)
 * 메모리: 64384 KB, 시간: 1144 ms
 **************************************************************************************/

public class 나는야_포켓몬_마스터_이다솜_1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<String, Integer> poketmon = new HashMap<>();
        Map<Integer, String> poketmonNum = new HashMap<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) { // O(n)
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();

            poketmon.put(name, i); // O(1)
            poketmonNum.put(i, name); // O(1)
        }

        for (int i = 0; i < m; i++) { // O(m)
            st = new StringTokenizer(br.readLine());

            String question = st.nextToken();
            boolean isNumber = false;

            for (char q : question.toCharArray()) { // toCharArray()를 사용하지 않으면 question이 String이라 해당 문법 사용할 수 없다고 뜸
                if (Character.isDigit(q)) {
                    isNumber = true;
                    break;
                }
            }

            if (isNumber) {
                System.out.println(poketmonNum.get(Integer.parseInt(question))); // O(1)
            } else {
                System.out.println(poketmon.get(question)); // O(1)
            }
        }
    }
}
