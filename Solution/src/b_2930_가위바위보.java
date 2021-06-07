import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2930_가위바위보 {
    static int Round, N, score, maxScore;
    static int[] Me;
    static int[][] Other;

    static final int R = 0, S = 1, P = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Round = sc.nextInt();           // 라운드 수
        String me = sc.next();
        Me = new int[Round];            // 상근이가 각 라운드에 낸 모양
        for (int i = 0; i < Round; i++) {
            char hand = me.charAt(i);

            switch (hand) {
                case 'R':
                    Me[i] = R;
                    break;
                case 'S':
                    Me[i] = S;
                    break;
                case 'P':
                    Me[i] = P;
                    break;
            }
        }


        N = sc.nextInt();               // 친구 수
        Other = new int[N][Round];

        for (int i = 0; i < N; i++) {
            String other = sc.next();   // 친구들이 각 라운드에 낸 모양

            for (int j = 0; j < Round; j++) {
                char hand = other.charAt(j);

                switch (hand) {
                    case 'R':
                        Other[i][j] = R;
                        break;
                    case 'S':
                        Other[i][j] = S;
                        break;
                    case 'P':
                        Other[i][j] = P;
                        break;
                }
            }
        }

        sol();

        System.out.println(score);
        System.out.println(maxScore);
    }

    /** 나머지 연산 사용 */
    private static void sol() {
        int max = 0;

        for (int r = 0; r < Round; r++) {
            int[][] testScore = {{R, 0}, {S, 0}, {P, 0}};

            for (int n = 0; n < N; n++) {
                if ((Me[r] + 1) % 3 == Other[n][r]) // 이긴 경우
                    score += 2;
                else if (Me[r] == Other[n][r])      // 비긴 경우
                    score += 1;

                for (int i = 0; i < 3; i++) {
                    if ((testScore[i][0] + 1) % 3 == Other[n][r]) // 이긴 경우
                        testScore[i][1] += 2;
                    else if (testScore[i][0] == Other[n][r])      // 비긴 경우
                        testScore[i][1] += 1;

                    max = Math.max(max, testScore[i][1]);
                }
            }
            maxScore += max;
        }
    }

}
