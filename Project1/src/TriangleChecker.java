    public class TriangleChecker {

        public static String checkTriangle(int a, int b, int c) {

            // Check invalid triangle
            if (a <= 0 || b <= 0 || c <= 0) {
                return "Invalid";
            }

            if (a + b <= c || a + c <= b || b + c <= a) {
                return "Invalid";
            }

            // Check triangle type
            if (a == b && b == c) {
                return "Equilateral";
            }

            if (a == b || a == c || b == c) {
                return "Isosceles";
            }

            return "Scalene";
        }
    }
