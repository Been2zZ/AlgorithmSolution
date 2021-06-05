import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_1525_퍼즐 {
    static int N = 3, GOAL = 123456789, ans;
    static int puzzle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());

                if (k == 0)
                    k = 9;

                puzzle = (puzzle * 10) + k;     // 2차원 퍼즐 -> 1차원 정수
            }
        }

        if (puzzle == GOAL)
            System.out.println(0);
        else
            System.out.println(bfs());

    }

    // 좌, 우, 상, 하
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> m = new HashMap<>();

        q.add(puzzle);
        m.put(puzzle, 0);

        while (!q.isEmpty()) {
            int currNum = q.poll();
            String currStr = String.valueOf(currNum);

            // 1차원 정수에서 '9' 위치 탐색 후, 2차원 좌표 저장
            int nineIndex = currStr.indexOf('9');
            int cx = nineIndex / 3;
            int cy = nineIndex % 3;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                // 이동할 2차원 배열 위치 -> 1차원 문자열에서의 인덱스 위치
                int next = nx * 3 + ny;

                if (!check(nx, ny))
                    continue;

                // 2차원 이동 좌표 -> 1차원 정수 인덱스 변경
                String nextStr = swapToNine(currStr, nineIndex, next);
                int nextNum = Integer.parseInt(nextStr);

                // 새로운 조합 -> map(1차원 정수 조합, 이동횟수)에 count 후 저장, 큐에 추가
                if (!m.containsKey(nextNum)) {
                    m.put(nextNum, m.get(currNum) + 1);
                    q.add(nextNum);
                }

            }
        }

        if (m.containsKey(GOAL))        // 123456789 조건 맞는 경우 이동횟수 저장
            ans = m.get(GOAL);
        else
            ans = -1;

        return ans;
    }

    /** 현재 인덱스의 '9'와 다음 인덱스 swap */
    private static String swapToNine(String str, int curr, int next) {
        StringBuilder sb = new StringBuilder(str);

        char temp = str.charAt(next);

        sb.setCharAt(next, '9');
        sb.setCharAt(curr, temp);

        return sb.toString();
    }

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }
}
