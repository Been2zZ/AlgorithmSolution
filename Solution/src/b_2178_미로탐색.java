import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_2178_미로탐색 {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        /**
         * 1 : 이동 가능
         * 0 : 이동 불가
         */
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                int temp = input.charAt(j) - '0';
                if (temp == 1)
                    map[i][j] = -1;
                else
                    map[i][j] = temp;
            }
        }

        int sx = 0, sy = 0;
        int dx = N - 1, dy = M - 1;

        go(sx, sy);

        System.out.println(map[dx][dy]);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void go(int sx, int sy) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sx, sy});
        map[sx][sy] = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!checked(nx, ny))
                    continue;

                if (map[nx][ny] == -1) {
                    // 이동 가능
                    q.add(new int[] {nx, ny});
                    map[nx][ny] = map[cx][cy] + 1;
                }
            }
        }
    }

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

}
