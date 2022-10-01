import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_2667_단지번호붙이기 {
    static int N, cnt;
    static int[][] map;
    static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        answer = new ArrayList<>();

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                int temp = input.charAt(j) - '0';
                if (temp == 1)
                    map[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (map[i][j] == -1)    // 집
                    go(i, j, cnt++);

        // output
        System.out.println(answer.size());
        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++)
            System.out.println(answer.get(i));

    }


    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void go(int x, int y, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        map[x][y] = c + 1;

        int sum = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!check(nx, ny))
                    continue;

                if (map[nx][ny] == -1) {
                    q.add(new int[] {nx, ny});
                    sum++;

                    map[nx][ny] = c + 1;
                }
            }
        }
        answer.add(c, sum);
    }

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }
}
