import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_2606_바이러스 {
    static int N, M, cnt;
    static boolean[] visited;
    static LinkedList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 정점 개수
        st = new StringTokenizer(br.readLine());
        M =  Integer.parseInt(st.nextToken());      // 간선 개수

        visited = new boolean[N + 1];
        adjList = new LinkedList[N + 1];

        // 인접 리스트 초기화
        for (int i = 0; i < N + 1; i++)
            adjList[i] = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            adjList[src].add(dest);
            adjList[dest].add(src);
        }

        dfs(1);

        for (int i = 2; i < N + 1; i++)
            if (visited[i])
                cnt++;

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
