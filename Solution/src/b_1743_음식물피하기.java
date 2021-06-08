import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1743_음식물피하기 {
    static int N, M, K, max;
    static int[][] map;
    static int[][] trash;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        trash = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            trash[i][0] = x - 1;
            trash[i][1] = y - 1;

            map[x - 1][y - 1] = 1;
        }

        for (int i = 0; i < K; i++) {
            int sx = trash[i][0];
            int sy = trash[i][1];
            if (visited[sx][sy] == 0)
                bfs(sx, sy);
        }

        System.out.println(max);
    }

    private static void bfs(int sx, int sy) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy, 1));
        visited[sx][sy] = 1;

        int cnt = 0;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            cnt++;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (!checked(nx, ny) || visited[nx][ny] > 0)
                    continue;

                if (map[nx][ny] == 1) {
                    q.add(new Point(nx, ny, curr.cnt + 1));
                    visited[nx][ny] = curr.cnt + 1;
                }
            }
        }
        max = Math.max(max, cnt);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    private static class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
