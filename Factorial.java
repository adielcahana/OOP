
public class Factorial {
    public static long factorialIter(long n) {
        long factorial = 1;
        for (long i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static long factorialRecursive(long n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorialRecursive(n - 1);

    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iteration: " + factorialIter(n));
    }
}
