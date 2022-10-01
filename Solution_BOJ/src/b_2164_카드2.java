import java.util.LinkedList;
import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2164_카드2 {
    static int N, ans;
    static int[] card;
    static LinkedList<Integer> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        card = new int[N + 1];
        list = new LinkedList<>();

        for (int i = 1; i < N + 1; i++)
            list.add(i);

        sol();
    }

    private static void sol() {
        while (!list.isEmpty()) {
            if (list.size() == 1) {
                ans = list.removeFirst();
                break;
            }

            list.removeFirst();

            int move = list.removeFirst();
            list.add(move);
        }

        System.out.println(ans);
    }
}
