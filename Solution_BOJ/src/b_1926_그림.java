import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class b_1926_그림 {

    static int n, m;

    static int[][] map;

    static boolean[][] visited;

    static List<Integer> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());       // 행
        m = Integer.parseInt(st.nextToken());       // 열

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j])
                    list.add(bfs(i, j));

            }
        }

        System.out.println(list.size());
        
        int resultCnt = list.size() == 0 ? 0 : list.stream().sorted().collect(Collectors.toList()).get(list.size() - 1);
        System.out.println(resultCnt);
    }

    private static int bfs(int sx, int sy) {
        int cnt = 1;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!checked(nx, ny) || visited[nx][ny])
                    continue;

                if (map[nx][ny] == 1) {
                    cnt++;
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return cnt;
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static boolean checked(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
