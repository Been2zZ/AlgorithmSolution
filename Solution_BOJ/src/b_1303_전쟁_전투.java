import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1303_전쟁_전투 {
    static int N, M, W, B;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = input.charAt(j);

                if (ch == 'W')
                    map[i][j] = 0;
                else
                    map[i][j] = 1;
            }
        }

        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[0][i][j])
                    bfs(i, j, 0);
                else if (map[i][j] == 1 && !visited[1][i][j])
                    bfs(i, j, 1);
            }
        }

        System.out.println(W + " " + B);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void bfs(int sx, int sy, int area) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sx, sy});
        visited[area][sx][sy] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            cnt++;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!checked(nx, ny) || visited[area][nx][ny])
                    continue;

                if (map[nx][ny] == area) {
                    q.add(new int[] {nx, ny});
                    visited[area][nx][ny] = true;
                }
            }
        }

        if (area == 0)
            W += Math.pow(cnt, 2);
        else
            B += Math.pow(cnt, 2);
    }

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }
}
