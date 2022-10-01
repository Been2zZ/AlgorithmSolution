import java.util.*;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_2331_반복수열 {
    static int A, P, loop;
    /** LinkedHashSet : 중복 허용 X, 순서 보장 O */
    static LinkedHashSet<Integer> set = new LinkedHashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        P = sc.nextInt();

        /**
         * D[1] = A
         * D[n] = D[n-1]의 각 자리의 숫자를 P번 곱한 수들의 합
         */

        sol(A);

        int cnt = 0;

        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            int next = iter.next();

            if (next == loop)
               break;

            cnt++;
        }

        System.out.println(cnt);
    }

    private static void sol(int curr) {
        if (set.contains(curr)) {
            loop = curr;        // 반복의 시작 저장
            return;
        }

        set.add(curr);

        sol(function(curr));
    }

    /** D[n] = D[n-1]의 각 자리의 숫자를 P번 곱한 수들의 합 반환 */
    private static int function(int pre) {
        String num = Integer.toString(pre);

        int sum = 0;

        for (int i = 0; i < num.length(); i++) {
            int n = num.charAt(i) - '0';

            sum += Math.pow(n, P);
        }

        return sum;
    }
}
