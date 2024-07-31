package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 <= T <= 100
 * 1 <= p <= 100,000
 * 0 <= n <= 100,000
 *
 * [첫번째 풀이]
 * - 'R' 연산이 들어오면 rCount++ 해준다. 이후, 'C' 연산이 들어왔을 때,
 *      - rCount % 2 == 1이라면 => 뒤집기 수행 O
 *      - rCOunt % 2 == 0이라면 => 뒤집기 수행 X
 * - 이 조건에 맞춰, 뒤집기가 수행되어야할 조건이라면 뒤집기 수행
 * - 결과: 시간 초과 발생
 *
 * [두번째 풀이]
 * - 'C' 연산이 배열 크기보다 더 많이 수행된다면, error 발생 => early return
 * - 'R' 연산이 들어온 경우, reversed 변수를 이용해 현재 배열이 뒤집힌 상태인지 저장
 * - 'R' 연산 이후 'C' 연산이 들어왔다면,
 *      - reversed == true: deque의 가장 마지막 element를 제거
 *      - reversed == false: deque의 가장 첫번째 element를 제거
 * - 배열을 출력할 때,
 *      - reversed == true: 마지막 element 부터 출력
 *      - reversed == false: 첫번째 element 부터 출력
 * - 결과: 시간초과 해결
 *
 * [반성]
 * - , 뒤에 공백 넣어서 계속 틀렸음
 * - isReversed 변수는 새로운 cmd가 들어올 때마다 초기화되어야하는데, 전역으로 선언해놓고 초기화 안해줘서 계속 틀렸음
 *      - 초기화 문제는 테케 순서 바꿔서 돌려봄으로서 빠르게 문제 원인 파악 가능하니까, 답이 이상하다면 입력값 순서 다르게 해서 넣어보기
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(T * N)
 * 메모리: 143652 KB, 시간: 888 ms
 **************************************************************************************/

public class AC_5430 {
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        while (tc-- > 0) { // O(T)
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();

            LinkedList<Integer> deque = getElements(arr); // O(N)

            runCmd(deque, p); // O(N)
        }
        System.out.println(sb);
    }

    private static void runCmd(LinkedList<Integer> deque, String p) { // O(N)
        boolean isReversed = false; // 현재 배열의 뒤집힌 상태값 저장 (true: 뒤집혀있음, false: 안 뒤집혀있음)

        // D의 개수가 deque의 사이즈보다 크다면 error로 발생
        int dCount = 0;
        for (Character c : p.toCharArray()) {
            if (c == 'D') dCount++;
        }

        if (dCount > deque.size()) {
            sb.append("error").append("\n");
            return;
        }

        // cmd 수행
        for (Character c : p.toCharArray()) { // O(N)
            if (c == 'R') {
                isReversed = !isReversed;
            } else if (c == 'D') {
                if (isReversed) {
                    deque.pollLast();
                } else deque.pollFirst();
            }
        }

        printDeque(deque, isReversed); // O(N)
    }

    private static void printDeque(LinkedList<Integer> deque, boolean isReversed) {
        sb.append("[");
        if (isReversed) {
            while (!deque.isEmpty()) {
                sb.append(deque.pollLast());
                if (deque.size() != 0) {
                    sb.append(",");
                }
            }
        } else {
            while (!deque.isEmpty()) {
                sb.append(deque.pollFirst());
                if (deque.size() != 0) {
                    sb.append(",");
                }
            }
        }
        sb.append("]").append("\n");
    }

    private static LinkedList<Integer> getElements(String arr) { // O(N)
        StringBuilder element = new StringBuilder();
        LinkedList<Integer> deque = new LinkedList<>();

        for (Character c : arr.toCharArray()) {
            if (c >= '0' && c <= '9') {
                element.append(c);
            } else if (c == ',') {
                int em = Integer.parseInt(element.toString());
                deque.offerLast(em);
                element = new StringBuilder();
            }
        }

        if (element.length() > 0) // 만약 element 에 숫자가 들어있다면, element를 마지막으로 추가
            deque.offerLast(Integer.parseInt(element.toString()));

        return deque;
    }
}
