import java.util.Scanner;

public class TriangleApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter side 1:");
        int a = scanner.nextInt();

        System.out.println("Enter side 2:");
        int b = scanner.nextInt();

        System.out.println("Enter side 3:");
        int c = scanner.nextInt();

        String result = TriangleChecker.checkTriangle(a, b, c);

        System.out.println("Triangle Type: " + result);
    }
}