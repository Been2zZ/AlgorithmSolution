import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1158_요세푸스문제 {
    static int N, K;
    static ArrayList<Integer> list;
    static ArrayList<Integer> ansList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        list = new ArrayList<>();
        ansList = new ArrayList<>();

        for (int i = 0; i < N; i++)
            list.add(i + 1);

//        sol_dfs(0, 1);
        sol_bfs();

        System.out.print("<");
        for (int i = 0; i < ansList.size(); i++) {
            System.out.print(ansList.get(i));
            if (i != ansList.size() - 1)
                System.out.print(", ");
        }
        System.out.print(">");
    }

    /**
     * !! StackOverflowError !!
     */
    private static void sol_dfs(int index, int cnt) {
        if (cnt == K) {
            ansList.add(list.get(index));
            list.remove(list.get(index));
            N--;

            if (N == 0)
                return;

            sol_dfs(index, 1);
        } else {
            sol_dfs((index + 1) % N, cnt + 1);
        }
    }

    private static void sol_bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        int cnt = 1;

        while (!q.isEmpty()) {
            int currIdx = q.poll();

            if (cnt == K) {
                ansList.add(list.get(currIdx));
                list.remove(list.get(currIdx));
                N--;

                if (N == 0)
                    return;

                cnt = 1;
                q.add(currIdx);
            } else {
                cnt++;
                q.add((currIdx + 1) % N);
            }
        }
    }
}
