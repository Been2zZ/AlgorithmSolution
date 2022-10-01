import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_16205_변수명 {
    static int T, N;
    static String input, CamelCase, SnakeCase, PascalCase;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /**
         * 1 : 카멜 표기법
         *     ex. variableN
         *
         * 2 : 스네이크 표기법
         *     ex. variable_n
         *
         * 3 : 파스칼 표기법
         *     ex. VariableN
         */
        T = sc.nextInt();
        input = sc.next();

        N = input.length();

        SnakeCase = orginalString();

        sol();
        /**
         * 1. 카멜 표기법
         * 2. 스네이크 표기법
         * 3. 파스칼 표기법
         */
        System.out.println(CamelCase);
        System.out.println(SnakeCase);
        System.out.println(PascalCase);
    }

    private static void sol() {
        String result = "";
        boolean flag = false;

        for (int i = 0; i < SnakeCase.length(); i++) {
            char ch = SnakeCase.charAt(i);
            if (ch == '_') {
                flag = true;
            } else {
                if (flag) {
                    result += (char) (ch - 32);
                    flag = false;
                } else {
                    result += ch;
                }
            }
        }

        CamelCase = result;

        char ch = (char) (CamelCase.charAt(0) - 32);
        result = "";
        result = ch + CamelCase.substring(1, CamelCase.length());

        PascalCase = result;
    }

    private static String orginalString() {
        String result = "";
        /**
         * 대문자 -> 소문자 : +32
         * A ~ Z : 65 ~ 90
         * a ~ z : 97 ~ 122
         * _ : 95
         */
        for (int i = 0; i < N; i++) {
            char ch = input.charAt(i);
            if (T == 1 || T == 3) {
                if (ch >= 65 && ch <= 90) {
                    if (i != 0)
                        result += (char) 95;
                    result += (char) (ch + 32);
                } else if (ch >= 97 && ch <= 122) {
                    result += ch;
                }
            } else {
                result = input;
            }
        }

        return result;
    }
}
