import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class b_16236_아기상어 {

    static int N, babyShark, eatCnt, time;
    static int[][] map;
    static PriorityQueue<Fish> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());        // N x N (2 ≤ N ≤ 20)
        map = new int[N][N];

        int sx = 0, sy = 0;

        /**
         * 0: 빈칸
         * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
         * 9: 아기 상어의 위치
         */
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {       // 아기 상어 위치
                    sx = i;
                    sy = j;
                    babyShark = 2;
                }
            }
        }

        pq = new PriorityQueue<>();

        while (true) {
            solution(sx, sy);

            if (pq.isEmpty())
                break;              // 더 이상 먹을 물고기 없다면 종료

            Fish targetFish = pq.poll();
            time += targetFish.dist;
            sx = targetFish.x;
            sy = targetFish.y;

            eatCnt++;

            // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
            if (babyShark == eatCnt) {
                eatCnt = 0;
                babyShark++;
            }

            pq.clear();
        }

        System.out.println(time);
    }

    private static void solution(int sx, int sy) {
        Queue<Fish> q = new LinkedList<>();
        q.add(new Fish(sx, sy, 0));
        map[sx][sy] = 0;
        boolean[][] visited = new boolean[N][N];
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Fish curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                int nd = curr.dist + 1;

                if (checked(nx, ny) && !visited[nx][ny] && map[nx][ny] <= babyShark) {
                    if (map[nx][ny] == babyShark || map[nx][ny] == 0)
                        q.add(new Fish(nx, ny, nd));
                    else if (map[nx][ny] < babyShark)
                        pq.add(new Fish(nx, ny, nd));       // 물고기 후보에 넣음

                    visited[nx][ny] = true;
                }
            }
        }
    }

    // 상 좌 우 하
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    private static boolean checked(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) {          // 우선순위가 같은 경우
                if (this.x == o.x)              // 행이 같은 경우
                    return this.y - o.y;        // 둥 중, 좌측 행이 우선순위를 가짐
                else                            // 행이 같지 않은 경우
                    return this.x - o.x;        // 둘 중, 높은 행이 우선순위를 가짐
            } else {                            // 우선순위가 같지 않은 경우
                return this.dist - o.dist;      // 둘 중, 거리가 더 가까운 쪽이 우선순위를 가짐
            }
        }
    }
}
