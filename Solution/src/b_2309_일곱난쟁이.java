import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2309_일곱난쟁이 {
    static int N = 9, Length = 100;
    static int[] arr = new int[N];
    static boolean[] visited;
    static List<Integer> ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        visited = new boolean[N];

        nCr(0, 0);

        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++)
            System.out.println(ans.get(i));
    }

    private static void nCr(int start, int cnt) {
        if (cnt == 7) {
            int sum = 0;

            for (int i = 0; i < N; i++)
                if (visited[i])
                    sum += arr[i];

            if (sum == Length) {
                ans = new ArrayList<>();

                for (int i = 0; i < N; i++)
                    if (visited[i])
                        ans.add(arr[i]);
            }
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                nCr(i + 1, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
