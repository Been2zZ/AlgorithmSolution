import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS, BFS 추천문제]
 */
public class b_1600_말이되고픈원숭이 {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        K = Integer.parseInt(st.nextToken());       // 원숭이 말 이동 가능 횟수

        st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());       // 가로 길이 : 열
        H = Integer.parseInt(st.nextToken());       // 세로 길이 : 행

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[H][W][K + 1];

        System.out.println(bfs(0, 0));

    }

    /**
     * (sx, sy) -> (dx, dy) 최단거리 구하기
     * @param sx
     * @param sy
     * @return
     */
    private static int bfs(int sx, int sy) {
        Queue<Monkey> q = new LinkedList<>();

        q.add(new Monkey(sx, sy, K, 0));
        visited[sx][sy][K] = true;

        while (!q.isEmpty()) {
            Monkey currMonkey = q.poll();

            if (currMonkey.x == H - 1 && currMonkey.y == W - 1)
                return currMonkey.totalMoveCnt;

            for (int d = 0; d < 4; d++) {
                // 원숭이의 이동 방법
                int nx = currMonkey.x + mx[d];
                int ny = currMonkey.y + my[d];
                int nk = currMonkey.k;

                if (checked(nx, ny) && !visited[nx][ny][nk] && map[nx][ny] == 0) {
                    q.add(new Monkey(nx, ny, nk, currMonkey.totalMoveCnt + 1));
                    visited[nx][ny][nk] = true;
                }
            }

            if (currMonkey.k > 0) {
                // 말의 이동 방법
                for (int d = 0; d < 8; d++) {
                    int nx = currMonkey.x + hx[d];
                    int ny = currMonkey.y + hy[d];
                    int nk = currMonkey.k - 1;

                    if (checked(nx, ny) && !visited[nx][ny][nk] && map[nx][ny] == 0) {
                        q.add(new Monkey(nx, ny, nk, currMonkey.totalMoveCnt + 1));
                        visited[nx][ny][nk] = true;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 말의 이동 방법
     */
    static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hy = {-2, -1, 1, 2, -2, -1, 1, 2};

    /**
     * 원숭이의 이동 방법
     */
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < H && y >= 0 && y < W);
    }

    private static class Monkey {
        int x;
        int y;
        int k;
        int totalMoveCnt;

        public Monkey(int x, int y, int k, int totalMoveCnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.totalMoveCnt = totalMoveCnt;
        }
    }
}
