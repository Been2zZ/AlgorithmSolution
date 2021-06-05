import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_2234_성곽 {
    static int n, m, cnt, max;
    static int[][] map;
    static int[][] visited;
    static Map<Integer, Integer> hashmap;
    static LinkedList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new int[m][n];
        hashmap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        /**
         * 벽이 있는 경우
         * 서(좌) : +1
         * 북(상) : +2
         * 동(우) : +4
         * 남(하) : +8
         */

        max = Integer.MIN_VALUE;

        int area = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    cnt = 0;
                    dfs(i, j, area);
                    max = Math.max(max, cnt);
                    hashmap.put(area, cnt);
                    area++;
                }
            }
        }

        adjList = new LinkedList[area + 1];
        for (int i = 1; i < area + 1; i++)
            adjList[i] = new LinkedList<>();

        link(0, 0);

        /**
         * 1. 이 성에 있는 방의 개수
         * 2. 가장 넓은 방의 넓이
         * 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
         */
        System.out.println(area - 1);
        System.out.println(max);
        System.out.println(sol());
    }

    /**
     * 하, 우, 상, 좌
     */
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void dfs(int x, int y, int area) {
        visited[x][y] = area;

        cnt++;

        // map의 벽 정보 10진수 -> 2진수
        String bin = String.format("%04d",
                Integer.parseInt(Integer.toBinaryString(map[x][y])));

        for (int d = 0; d < 4; d++) {
            // 하, 우, 상, 좌
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!check(nx, ny) || visited[nx][ny] > 0)
                continue;

            // 이동 가능한 경우 (이동하려는 방향에 벽 존재하지 않는 경우)
            if (bin.charAt(d) == '0')
                dfs(nx, ny, area);
        }
    }

    private static void link(int x, int y) {
        map[x][y] = -1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!check(nx, ny) || map[nx][ny] == -1)
                continue;

            int curr = visited[x][y];
            int next = visited[nx][ny];

            if (visited[x][y] != visited[nx][ny]
                    && !adjList[curr].contains(next)
                    && !adjList[next].contains(curr)) {
                adjList[curr].add(next);
            }

            link(nx, ny);
        }

    }

    private static int sol() {
        int max = -1;

        for (int area = 1; area < adjList.length - 1; area++) {
            Iterator<Integer> iter = adjList[area].listIterator();
            while (iter.hasNext()) {
                max = Math.max(max, hashmap.get(area) + hashmap.get(iter.next()));
            }
        }
        return max;
    }

    private static boolean check(int x, int y) {
        return (x >= 0 && x < m && y >= 0 && y < n);
    }
}
