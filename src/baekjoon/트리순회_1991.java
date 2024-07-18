package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 26
 * 시간 제한: 2초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 14296 KB, 시간: 108 ms
 **************************************************************************************/

public class 트리순회_1991 {
    private static Map<Character, Node> nodes;

    private static class Node {
        char right, left;

        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        nodes = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char num = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            nodes.put(num, new Node(left, right));
        }

        prevOrder('A');
        System.out.println();
        inOrder('A');
        System.out.println();
        postOrder('A');
        System.out.println();
    }

    private static void prevOrder(char num) {
        if (num == '.') return;

        if (nodes.containsKey(num)) {
            Node next = nodes.get(num);
            System.out.print(num);
            prevOrder(next.left);
            prevOrder(next.right);
        }
    }

    private static void inOrder(char num) {
        if (num == '.') return;

        if (nodes.containsKey(num)) {
            Node next = nodes.get(num);
            inOrder(next.left);
            System.out.print(num);
            inOrder(next.right);
        }
    }

    private static void postOrder(char num) {
        if (num == '.') return;

        if (nodes.containsKey(num)) {
            Node next = nodes.get(num);
            postOrder(next.left);
            postOrder(next.right);
            System.out.print(num);
        }
    }
}
