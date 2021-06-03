import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_2644_촌수계산 {
    static int n, start, end, m, cnt;
    static int[] visited;
    static int[][] adjArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        visited = new int[n + 1];
        Arrays.fill(visited, -1);

        adjArray = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            adjArray[src][dest] = 1;
            adjArray[dest][src] = 1;
        }

        bfs(start);
        System.out.println(cnt);
    }

    private static void bfs(int p) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(p);

        while (!queue.isEmpty()) {
            int q = queue.poll();

            if (q == end) {
                cnt = visited[end] + 1;
                break;
            }

            for (int i = 1; i < n + 1; i++) {
                if (adjArray[q][i] == 1 && visited[i] < 0) {
                    queue.add(i);
                    visited[i] = visited[q] + 1;
                }
            }
        }

        if (visited[end] == -1)
            cnt = -1;
    }
}
