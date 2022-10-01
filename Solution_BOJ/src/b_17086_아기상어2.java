import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_17086_아기상어2 {
    static int N, M, max;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 0)
                    bfs(i, j);

        System.out.println(max);
    }

    private static void bfs(int sx, int sy) {
        visited = new boolean[N][M];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int d = 0; d < 8; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (!checked(nx, ny) || visited[nx][ny])
                    continue;

                if (map[nx][ny] == 0) {
                    q.add(new Point(nx, ny, curr.cnt + 1));
                    visited[nx][ny] = true;
                } else {
                    max = Math.max(max, curr.cnt + 1);
                    return;
                }
            }
        }
    }

    // 8 방향 (대각선 포함)
    static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};

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

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
