import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_2583_영역구하기 {
    static int M, N, K, cnt, index;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        list = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int lbx = Integer.parseInt(st.nextToken());
            int lby = Integer.parseInt(st.nextToken());
            int rtx = Integer.parseInt(st.nextToken());
            int rty = Integer.parseInt(st.nextToken());

            int temp;
            temp = lbx;
            lbx = M - lby;
            lby = temp;

            temp = rtx;
            rtx = M - rty;
            rty = temp;

            // map 채우기
            for (int j = rtx; j < lbx; j++)
                for (int k = lby; k < rty; k++)
                    map[j][k] = 1;

        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    list.add(0);
                    dfs(i, j);
                    index++;
                }
            }
        }

        System.out.println(index);

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!checked(nx, ny) || visited[nx][ny])
                continue;

            if (map[nx][ny] == 0)
                dfs(nx, ny);
        }

        list.set(index, list.get(index) + 1);
    }

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < M && y >= 0 && y < N);
    }
}
