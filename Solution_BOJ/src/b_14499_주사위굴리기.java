import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_14499_주사위굴리기 {

    static int N, M, x, y, K;

    static int[][] map;

    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());           // 지도의 세로 크기 (행)
        M = Integer.parseInt(st.nextToken());           // 지도의 가로 크기 (열)
        x = Integer.parseInt(st.nextToken());           // 주사위 x 좌표
        y = Integer.parseInt(st.nextToken());           // 주사위 y 좌표
        K = Integer.parseInt(st.nextToken());           // 명령의 개수

        map = new int[N][M];
        dice = new int[6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        /**
         * 동 : 0
         * 서 : 1
         * 북 : 2
         * 남 : 3
         */
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++)
            sol(Integer.parseInt(st.nextToken()) - 1);

    }

    private static void sol(int dir) {
        // 주사위 굴린 위치가 범위 밖인 경우, 해당 명령 무시
        if (checked(x + dx[dir], y + dy[dir])) {
            x += dx[dir];       // 이동할 x좌표 이동
            y += dy[dir];       // 이동할 y좌표 이동

            dice = calc(dir, dice);         // 주사위 굴리기

            System.out.println(dice[0]);    // 주사위의 윗면 값 (top, 0)

            if (map[x][y] == 0) {
                map[x][y] = dice[1];
            } else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }
        }
    }

    /**
     * 주사위 굴리기
     *
     * @param dir  방향
     * @param dice [top, down, right, left, front, back]
     * @return 주사위 굴린 후의 값
     */
    private static int[] calc(int dir, int[] dice) {
        int[] newDice = new int[dice.length];

        System.arraycopy(dice, 0, newDice, 0, dice.length);

        switch (dir) {
            case 0:
                newDice[0] = dice[3];
                newDice[1] = dice[2];
                newDice[2] = dice[0];
                newDice[3] = dice[1];
                break;
            case 1:
                newDice[0] = dice[2];
                newDice[1] = dice[3];
                newDice[2] = dice[1];
                newDice[3] = dice[0];
                break;
            case 2:
                newDice[0] = dice[4];
                newDice[1] = dice[5];
                newDice[4] = dice[1];
                newDice[5] = dice[0];
                break;
            case 3:
                newDice[0] = dice[5];
                newDice[1] = dice[4];
                newDice[4] = dice[0];
                newDice[5] = dice[1];
                break;
        }

        return newDice;
    }

    /**
     * 이동 배열
     * 동(0), 서(1), 북(2), 남(3)
     */
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

}
