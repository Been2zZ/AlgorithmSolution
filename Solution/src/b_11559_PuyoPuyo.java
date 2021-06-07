import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_11559_PuyoPuyo {
    static int N = 12, M = 6, INF = Integer.MAX_VALUE;
    static int[][] map, visited;
    static List<Point> popList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                switch (ch) {
                    case 'R':
                        map[i][j] = 1;
                        break;
                    case 'G':
                        map[i][j] = 2;
                        break;
                    case 'B':
                        map[i][j] = 3;
                        break;
                    case 'P':
                        map[i][j] = 4;
                        break;
                    case 'Y':
                        map[i][j] = 5;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                }
            }
        }

        int cnt = 0;

        while (true) {
            popList = new ArrayList<>();
            visited = new int[N][M];

            cnt++;

            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (map[i][j] > 0 && visited[i][j] == 0)
                        bfs(i, j, map[i][j], cnt);

            if (popList.size() == 0)
                break;
            else
                pop();
        }

        System.out.println(cnt - 1);
    }

    private static void bfs(int sx, int sy, int color, int cnt) {
        Queue<Point> q = new LinkedList<>();
        List<Point> list = new ArrayList<>();

        q.add(new Point(sx, sy));
        visited[sx][sy] = cnt;

        int result = 0;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            list.add(new Point(curr.x, curr.y));

            result++;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (!check(nx, ny) || visited[nx][ny] > 0)
                    continue;

                if (map[nx][ny] == color) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = cnt;
                }
            }
        }

        if (result < 4) {
            for (int i = 0; i < list.size(); i++) {
                int x = list.get(i).x;
                int y = list.get(i).y;

                map[x][y] = color;
                visited[x][y] = INF;
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                int x = list.get(i).x;
                int y = list.get(i).y;

                map[x][y] = 0;
            }
            popList.addAll(list);
        }

    }

    private static void pop() {
        /** 터짐 */
        for (int i = 0; i < popList.size(); i++) {
            int y = popList.get(i).y;

            String str = "";
            for (int j = 0; j < N; j++)
                if (map[j][y] > 0)
                    str += map[j][y];

                if (str != "") {
                    str = String.format("%012d", Long.parseLong(str));
                    for (int j = 0; j < N; j++)
                        map[j][y] = str.charAt(j) - '0';
                }

        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
