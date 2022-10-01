import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2493_탑 {
    static int N, max;
    static int[] tower;
    static int[] answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());                   // 탑의 개수

        tower = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tower[i]);
        }

        answer = new int[N];
        sol_stack();

        for (int i = 0; i < N; i++)
            System.out.print(answer[i] + " ");

//        map = new int[max][N];
//        for (int i = 0; i < max; i++) {
//            for (int j = 0; j < N; j++) {
//                int len = tower[j];
//
//                for (int k = max - 1; k >= max - len; k--)
//                    map[k][j] = 1;
//
//            }
//        }

//        for (int n = 0; n < N; n++) {
//            int top = max - tower[n];
//            System.out.print(bfs(n, top) + " ");
//        }

//        answer = new int[N];
//        sol_forloop();

    }

    private static void sol_stack() {
        Stack<Tower> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek().height >= tower[i]) {
                    answer[i] = stack.peek().idx + 1;
                    stack.push(new Tower(i, tower[i]));
                    break;
                } else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) {
                answer[i] = 0;
                stack.push(new Tower(i, tower[i]));
            }
        }
    }

    private static void sol_forloop() {
        for (int i = N - 1; i > 0; i--) {
            if (i == max - 1)
                break;
            for (int j = i - 1; j >= 0; j--) {
                if (tower[i] < tower[j]) {
                    answer[i] = j + 1;
                    break;
                }
            }
        }
    }

    private static int bfs(int n, int top) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        if (n == 0 || top == max - 1)
            return 0;

        while (!q.isEmpty()) {
            int cy = q.poll();
            int ny = cy - 1;

            if (ny < 0)
                continue;

            if (map[top][ny] > 0)
                return ny + 1;
            else
                q.add(ny);
        }
        return 0;
    }

    private static class Tower {
        int idx;
        int height;

        public Tower(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
}
