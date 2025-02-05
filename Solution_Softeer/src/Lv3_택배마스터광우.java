import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lv3_택배마스터광우 {
    static int N, M, K;
    static int minWeight = Integer.MAX_VALUE;
    static int[] weightedRails, calcWeight;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 레일 개수 (3 <= N <= 10)
        M = Integer.parseInt(st.nextToken());   // 택배 바구니 무게 (max(Ni) < M ≤ 50)
        K = Integer.parseInt(st.nextToken());   // 일의 횟수 (1 ≤ K ≤ 50)

        weightedRails = new int[N];             // 무게 전용 레일 (1 ≤ Ni ≤ 50)
        calcWeight = new int[N];                // 무게 계산 배열 (택배 순서)
        visited = new boolean[N];               // 방문 배열

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            weightedRails[i] = Integer.parseInt(st.nextToken());

        dfs(0);
        System.out.println(minWeight);
    }

    private static void dfs(int cnt) {
        if (cnt == N) {
            minWeight = Math.min(minWeight, getWeight());
            return;
        }

        // 완전 탐색
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {      // 방문하지 않은 경우
                visited[i] = true;  // 해당 택배 방문 처리
                calcWeight[cnt] = weightedRails[i]; // 무게 계산 배열에 현재 택배 추가
                dfs(cnt + 1);   // 다음 무계 계산 배열 재귀
                visited[i] = false; // 해당 택배 방문 취소 처리 (완전 탐색)
            }
        }
    }

    private static int getWeight() {
        int idx = 0;
        int totalWeight = 0;
        for (int i = 0; i < K; i++) {               // 일의 횟수(K)만큼 반복
            int weight = 0;
            while (weight + calcWeight[idx] <= M) { // 택배 바구니 무게 넘지 않는 경우 반복 (다음 택배 추가)
                weight += calcWeight[idx];
                idx = (idx + 1) == N ? 0 : idx + 1; // idx 인덱스 N 넘지 않으면 1증가 (다음 택배 인덱스)
            }
            totalWeight += weight;
        }
        return totalWeight;
    }
}
