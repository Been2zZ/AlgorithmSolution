import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lv3_성적평균 {
    static int N, K;
    static int[] grade;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 학생 수
        K = Integer.parseInt(st.nextToken());   // 구간 수

        grade = new int[N];                     // 성적
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            grade[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int startIdx = Integer.parseInt(st.nextToken()) - 1;    // 학번 구간 시작 인덱스
            int endIdx = Integer.parseInt(st.nextToken()) - 1;      // 학번 구간 종료 인덱스

            System.out.printf("%.2f\n", sol(startIdx, endIdx));
        }

    }

    private static double sol(int startIdx, int endIdx) {
        double sum = 0;
        for (int i = startIdx; i <= endIdx; i++)
            sum += grade[i];

        return sum / (endIdx - startIdx + 1);
    }
}
