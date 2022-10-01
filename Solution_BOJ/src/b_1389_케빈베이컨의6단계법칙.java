import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_1389_케빈베이컨의6단계법칙 {
    static int N, M, ans;
    static LinkedList<Integer>[] adjList;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new LinkedList[N + 1];
        visited = new int[N + 1];

        for (int i = 1; i < N + 1; i++)
            adjList[i] = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[v].add(w);
            adjList[w].add(v);
        }

        int min = Integer.MAX_VALUE;
        ans = 0;

        for (int user = 1; user < N + 1; user++) {
            int result = bfs(user);

            if (result < min) {
                min = result;
                ans = user;
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int v) {
        visited = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = 1;

       int sum = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            Iterator<Integer> iter = adjList[curr].listIterator();
            while (iter.hasNext()) {
                int next = iter.next();

                if (visited[next] > 0)
                    continue;

                q.add(next);
                visited[next] = visited[curr] + 1;

                sum += visited[curr];
            }
        }

        return sum;
    }
}
