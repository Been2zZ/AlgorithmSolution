import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_9012_괄호 {
    static int N, len;
    static char[] str;
    static Stack<Character> stack;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String input = sc.next();
            len = input.length();

            str = new char[len];
            for (int j = 0; j < len; j++)
                str[j] = input.charAt(j);

            stack = new Stack<>();

            sol();
        }
    }

    private static void sol() {
        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty()) {
                if (stack.peek().equals('(')) {
                    if (!stack.peek().equals(str[i]))
                        stack.pop();
                    else
                        stack.push(str[i]);
                } else
                    stack.push(str[i]);
            } else {
                stack.push(str[i]);
            }
        }

        if (stack.isEmpty())
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
