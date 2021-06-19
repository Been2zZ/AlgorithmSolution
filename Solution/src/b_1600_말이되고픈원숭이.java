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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        K = Integer.parseInt(st.nextToken());       // 원숭이 동작 횟수

        st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());       // 가로 길이 : 열
        H = Integer.parseInt(st.nextToken());       // 세로 길이 : 행

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int sx, int sy) {
        /**
         * 아직 미완료 !!!
         */

        /**
         * DP 3차원 배열
         */
        Queue<Monkey> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];

        q.add(new Monkey(sx, sy, 0, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Monkey currMonkey = q.poll();

            print(visited);

            if (currMonkey.x == H - 1 && currMonkey.y == W - 1)
                return currMonkey.totalMoveCnt;

            if (currMonkey.horseMoveCnt < K) {
                // 말의 이동 방법
                for (int d = 0; d < 8; d++) {
                    int nx = currMonkey.x + hx[d];
                    int ny = currMonkey.y + hy[d];

                    if (!checked(nx, ny) || visited[nx][ny])
                        continue;

                    q.add(new Monkey(nx, ny, currMonkey.horseMoveCnt + 1, currMonkey.totalMoveCnt + 1));
                    visited[nx][ny] = true;
                }
            } else {
                // 원숭이의 이동 방법
                for (int d = 0; d < 4; d++) {
                    int nx = currMonkey.x + mx[d];
                    int ny = currMonkey.y + my[d];

                    if (!checked(nx, ny) || visited[nx][ny])
                        continue;

                    q.add(new Monkey(nx, ny, currMonkey.horseMoveCnt, currMonkey.totalMoveCnt + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    private static void print(boolean[][] visited) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (visited[i][j])
                    System.out.print("* ");
                else
                    System.out.print("F ");
            }
            System.out.println();
        }
        System.out.println("=======");
    }

    /** 말의 이동 방법 */
    static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hy = {-2, -1, 1, 2, -2, -1, 1, 2};

    /** 원숭이의 이동 방법 */
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < H && y >= 0 && y < W);
    }

    private static class Monkey {
        int x;
        int y;
        int horseMoveCnt;
        int totalMoveCnt;

        public Monkey(int x, int y, int horseMoveCnt, int totalMoveCnt) {
            this.x = x;
            this.y = y;
            this.horseMoveCnt = horseMoveCnt;
            this.totalMoveCnt = totalMoveCnt;
        }
    }
}
