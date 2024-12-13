package leetcode.math;

public class myPow {

    public static void main(String[] args) {
        double x = 2.00000;
        int n = 10;

        System.out.println(myPow(x, n));
    }

    public static double myPow(double x, int n) {
        if (n == 0)  return 1.0;

        double result = DFS(x, n);

        if (n > 0) return result;
        return 1.0 / result;
    }

    private static double DFS(double x, int n) {
        if (n == 0)  return 1.0;

        double half = DFS(x, n / 2);
        if (n % 2 == 0) return half * half;
        return half * half * x;
    }
}
