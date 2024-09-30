package programmers.level_1;

public class 최대공약수와최소공배수 {

    public static void main(String[] args) {
        int[] result  = solution(2, 5);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] solution(int n, int m) {

        int smallNum, bigNum;

        if(n >= m){
            smallNum = m;
            bigNum = n;
        } else {
            smallNum = n;
            bigNum = m;
        }

        int lcm = bigNum;    // 최소 공배수
        int gcd = smallNum;    // 최대 공약수

        while(lcm % smallNum != 0){
            lcm += bigNum;
        }

        while(bigNum % smallNum != 0){
            int temp = bigNum;
            bigNum = smallNum;
            smallNum = temp % smallNum;
        }

        gcd = smallNum;

        return new int[]{gcd, lcm};
    }
}
