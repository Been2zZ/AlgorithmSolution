import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */

public class b_11724_연결요소의개수 {
    static int N, M;
    static LinkedList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        adjList = new LinkedList[N + 1];

        for (int i = 1; i < N + 1; i++)
            adjList[i] = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[v].add(w);
            adjList[w].add(v);
        }

        int cnt = 0;

        for (int i = 1; i < N + 1; i++)
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }


        System.out.println(cnt);
    }

    private static void dfs(int v) {
        visited[v] = true;

        Iterator<Integer> iter = adjList[v].listIterator();
        while (iter.hasNext()) {
            int w = iter.next();

            if (!visited[w])
                dfs(w);
        }
    }
}
