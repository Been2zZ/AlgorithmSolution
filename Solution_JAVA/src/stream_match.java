import java.util.Arrays;

public class stream_match {
    public static void main(String[] args) {
        int[] arr = {3, 7, 4, 2, 1, 8, 6, 9, 3};

        boolean allMatch = Arrays.stream(arr).allMatch(num -> num / 2 == 0);
        boolean anyMatch = Arrays.stream(arr).anyMatch(num -> num / 2 == 0);
        boolean noneMatch = Arrays.stream(arr).noneMatch(num -> num / 2 == 0);

        System.out.println(">> 짝수 판별");
        System.out.println("[allMatch] 배열의 모든 요소가 짝수로만 이루어져 있는가? " + allMatch);
        System.out.println("[anyMatch] 배열의 요소중 짝수가 존재하는가? " + anyMatch);
        System.out.println("[noneMatch] 배열의 모든 요소가 짝수가 아닌 값으로 이루어져 있는가? " + noneMatch);

        System.out.println("\n>> 10 미만의 수 판별");
        allMatch = Arrays.stream(arr).allMatch(num -> num < 10);
        anyMatch = Arrays.stream(arr).anyMatch(num -> num < 10);
        noneMatch = Arrays.stream(arr).noneMatch(num -> num < 10);

        System.out.println("[allMatch] 배열의 모든 요소가 10 미만의 수로 이루어져 있는가? " + allMatch);
        System.out.println("[anyMatch] 배열의 요소중 10 미만의 수가 존재하는가? " + anyMatch);
        System.out.println("[noneMatch] 배열의 모든 요소가 10 미만의 수가 아닌 값으로 이루어져 있는가? " + noneMatch);

        System.out.println("\n>> 10 이상의 수 판별");
        allMatch = Arrays.stream(arr).allMatch(num -> num >= 10);
        anyMatch = Arrays.stream(arr).anyMatch(num -> num >= 10);
        noneMatch = Arrays.stream(arr).noneMatch(num -> num >= 10);
        System.out.println("[allMatch] 배열의 모든 요소가 10 이상의 수로 이루어져 있는가? " + allMatch);
        System.out.println("[anyMatch] 배열의 요소중 10 이상의 수가 존재하는가? " + anyMatch);
        System.out.println("[noneMatch] 배열의 모든 요소가 10 이상의 수가 아닌 값으로 이루어져 있는가? " + noneMatch);

    }
}
