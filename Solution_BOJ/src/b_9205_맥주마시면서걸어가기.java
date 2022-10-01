import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_9205_맥주마시면서걸어가기 {
    static int n, diff;
    static boolean[][] map;
    static Point[] points;
    static boolean[] visited;
    static Point start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());       // 편의점 개수

            map = new boolean[n + 2][n + 2];
            points = new Point[n + 2];
            visited = new boolean[n + 2];

            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                points[i] = new Point(x, y);
            }

            start = points[0];            // 시작 지점
            end = points[n + 1];        // 도착 지점

            diff = dist(start, end);

            // 이동 가능 여부 체크
            for (int i = 0; i < n + 1; i++) {
                Point src = points[i];

                for (int j = i + 1; j < n + 2; j++) {
                    Point dest = points[j];

                    if (dist(src, dest) <= 1000) {
                        map[i][j] = true;
                        map[j][i] = true;               // 플로이드 와샬 사용할 경우 주석 처리
                    }
                }
            }

            /** BFS */
            bfs();

            if (visited[n + 1])
                System.out.println("happy");
            else
                System.out.println("sad");

            /** 플로이드 와샬 */
//            sol();
//
//            if (map[0][n + 1])
//                System.out.println("happy");
//            else
//                System.out.println("sad");

        }   // tc for loop end
    }

    /** 플로이드 와샬 */
    private static void sol() {
        for (int i = 0; i < n + 2; i++)
            for (int j = 0; j < n + 2; j++)
                for (int k = 0; k < n + 2; k++)
                    if (map[j][i] && map[i][k])
                        map[j][k] = true;
    }

    /** BFS */
    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int i = 1; i < n + 2; i++) {
                // 이동 가능한 경우
                if (map[curr][i] && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    /** 맨해튼 거리 계산 */
    private static int dist(Point src, Point dest) {
        return Math.abs(src.x - dest.x) + Math.abs(src.y - dest.y);
    }
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
