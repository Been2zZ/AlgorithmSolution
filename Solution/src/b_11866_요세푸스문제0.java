import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_11866_요세푸스문제0 {
    static int N, K;
    static List<Integer> list;
    static List<Integer> ansList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        list = new ArrayList<>();
        for (int i = 0; i < N; i++)
            list.add(i + 1);

        ansList = new ArrayList<>();
        sol(0);

        System.out.print("<");
        for (int i = 0; i < ansList.size(); i++) {
            if (i != ansList.size() - 1)
                System.out.print(ansList.get(i) + ", ");
            else
                System.out.print(ansList.get(i));
        }
        System.out.print(">");
    }

    private static void sol(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int cnt = 1;

        while (!q.isEmpty()) {
            int currIdx = q.poll();
            int nextIdx = currIdx;

            if (cnt == K) {
                ansList.add(list.get(currIdx));
                list.remove(currIdx);
                cnt = 1;

                if (list.isEmpty())
                    break;

            } else {
                cnt++;
                nextIdx = (currIdx + 1) % list.size();
            }

            q.add(nextIdx);
        }
    }
}
