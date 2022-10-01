import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS, BFS 추천문제]
 */
public class b_9019_DSLR {
    static int T;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            /**
             * 0 <= A, B < 10000
             */
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];       // 숫자의 방문 체크 배열

            System.out.println(sol(A, B));
        }
    }

    private static String sol(int a, int b) {
        Queue<Item> q = new LinkedList<>();

        q.add(new Item(a, ""));
        visited[a] = true;

        while (!q.isEmpty()) {
            Item curr = q.poll();

            if (curr.num == b)
                return curr.command;

            for (int i = 0; i < 4; i++) {
                int nextNum = calc(curr.num, dslr[i]);
                char nextCommand = dslr[i];

                if (nextNum > 10000)
                    break;

                if (!visited[nextNum]) {
                    q.add(new Item(nextNum, curr.command + nextCommand));
                    visited[nextNum] = true;
                }
            }
        }

        return "";
    }

    static char[] dslr = {'D', 'S', 'L', 'R'};

    private static int calc(int num, char ch) {
        switch (ch) {
            case 'D':
                return num * 2 > 9999 ? (num * 2) % 10000 : num * 2;
            case 'S':
                return num == 0 ? 9999 : num - 1;
            case 'L':
                return (num % 1000) * 10 + num / 1000;
            case 'R':
                return (num % 10) * 1000 + num / 10;
            default:
                return num;
        }
    }

    public static class Item {
        int num;        // 현재 숫자
        String command; // 명령어

        public Item(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }
}
