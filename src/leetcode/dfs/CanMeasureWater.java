package leetcode.dfs;

public class CanMeasureWater {

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3,5,4));
    }

    public static boolean canMeasureWater(int x, int y, int target) {
        if (x + y < target) return false;

        int gcd = gcd(x,y);
        return target % gcd == 0;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
