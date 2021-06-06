import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_3055_탈출 {
    static int R, C, INF, sx, sy, dx, dy, ans;
    static int[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        INF = Integer.MAX_VALUE;

        map = new int[R][C];
        visited = new int[R][C];

        /**
         *  고슴도치 -> 비버의 굴
         *  물 매분마다 비어있는 칸으로 확장 (비버의 굴 제외)
         *  돌 통과 X
         *  물 이동 불가 X
         *
         *  !! 물이 찰 예정의 칸으로 이동 불가능
         *
         *  . : 비어있는 곳
         *  * : 물이 차있는 곳
         *  X : 돌
         *  D : 비버의 굴
         *  S : 고슴도치 위치
         */
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                switch (ch) {
                    case 'D':
                        dx = i;
                        dy = j;
                        break;
                    case 'S':
                        sx = i;
                        sy = j;
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                    case '*':
                        map[i][j] = 1;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                }

                visited[i][j] = INF;
            }
        }

        /** 물 채우기 */
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (map[i][j] == 1)
                    waterBFS(i, j);

        ans = bfs();

        if (ans == -1)
            System.out.println("KAKTUS");
        else
            System.out.println(ans);

    }

    private static int bfs() {
        visited = new int[R][C];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy, 1));
        visited[sx][sy] = q.peek().cnt;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dirX[d];
                int ny = curr.y + dirY[d];

                if (!check(nx, ny) || visited[nx][ny] > 0)
                    continue;

                if (nx == dx && ny == dy)
                    return curr.cnt;

                int nextCnt = curr.cnt + 1;

                if (map[nx][ny] > nextCnt || map[nx][ny] == 0) {
                    q.add(new Point(nx, ny, nextCnt));
                    visited[nx][ny] = nextCnt;
                }
            }
        }
        return -1;
    }

    private static void waterBFS(int sx, int sy) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx, sy, 1));
        visited[sx][sy] = q.peek().cnt;

        while (!q.isEmpty()) {
            Point currWater = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = currWater.x + dirX[d];
                int ny = currWater.y + dirY[d];

                if (!check(nx, ny) || visited[nx][ny] <= currWater.cnt || (nx == dx && ny == dy))
                    continue;

                int nextCnt = currWater.cnt + 1;

                if (map[nx][ny] == 0 || map[nx][ny] > nextCnt) {
                    q.add(new Point(nx, ny, nextCnt));
                    visited[nx][ny] = nextCnt;
                    map[nx][ny] = nextCnt;
                }
            }
        }
    }

    static int[] dirX = {1, 0, -1, 0};
    static int[] dirY = {0, 1, 0, -1};

    private static boolean check(int x, int y) {
        return (x >= 0 && x < R && y >= 0 && y < C);
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
