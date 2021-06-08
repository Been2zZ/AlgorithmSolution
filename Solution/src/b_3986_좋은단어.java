import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_3986_좋은단어 {
    static int N, len, ans;
    static char[] word;
    static Stack<Character> stack;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String input = sc.next();
            len = input.length();

            word = new char[len];
            for (int j = 0; j < len; j++)
                word[j] = input.charAt(j);

            stack = new Stack<>();
            ans += sol();
        }

        System.out.println(ans);
    }

    /**
     *  Stack 사용
     * @return 좋은 단어 여부
     */
    private static int sol() {
        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty()) {
                if (stack.peek().equals(word[i]))
                    stack.pop();
                else
                    stack.push(word[i]);
            } else {
                stack.push(word[i]);
            }
        }

        if (stack.size() > 0)
            return 0;
        else
            return 1;
    }
}
