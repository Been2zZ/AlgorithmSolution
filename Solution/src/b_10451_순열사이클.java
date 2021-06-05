import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_10451_순열사이클 {
    static int N;
    static LinkedList<Integer>[] adjList;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());

            visited = new boolean[N + 1];

            // 인접 리스트
            adjList = new LinkedList[N + 1];
            for (int i = 0; i < N + 1; i++)
                adjList[i] = new LinkedList<>();

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i < N + 1; i++) {
                int j = Integer.parseInt(st.nextToken());

                adjList[i].add(j);
            }

            int cnt = 0;

            for (int i = 1; i < N + 1; i++)
                if (!visited[i]) {
                    cnt++;
                    dfs(i);
                }

            System.out.println(cnt);
        }
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
