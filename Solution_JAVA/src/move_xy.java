public class move_xy {

    static int[][] map;

    public static void main(String[] args) {

        map = new int[10][10];

        move_four(0, 0);
        move_night(0, 0);
    }

    static int[] dx_four = {0, 0, -1, 1};
    static int[] dy_four = {-1, 1, 0, 0};

    private static void move_four(int x, int y) {
        // 인접한 네 방향으로 이동 (동, 서, 남, 북)
        for (int d = 0; d < 4; d++) {
            int nx = x + dx_four[d];
            int ny = y + dy_four[d];
        }
    }

    static int[] dx_night = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy_night = {1, 2, 2, 1, -1, -2, -2, -1};

    private static void move_night(int x, int y) {
        // 체스 말 나이트의 이동
        for (int d = 0; d < 8; d++) {
            int nx = x + dx_night[d];
            int ny = y + dy_night[d];
        }
    }
}
