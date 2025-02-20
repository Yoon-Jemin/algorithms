package programmers.고득점Kit.동적계획법;

public class ex5_도둑질 {

    public static void main(String[] args) {

        int[] money = {10, 5, 3, 1, 10};
        System.out.println(solution(money));

    }

//    print(solution([1,2,3,1]), 4)
//    print(solution([1,1,4,1,4]), 8)
//    print(solution([1000,0,0,1000,0,0,1000,0,0,1000]), 3000)
//    print(solution([1000,1,0,1,2,1000,0]), 2001)
//    print(solution([1000,0,0,0,0,1000,0,0,0,0,0,1000]), 2000)
//    print(solution([1,2,3,4,5,6,7,8,9,10]), 30)
//    print(solution([0,0,0,0,100,0,0,100,0,0,1,1]), 201)
//    print(solution([11,0,2,5,100,100,85,1]), 198)
//    print(solution([1,2,3]), 3)
//    print(solution([91,90,5,7,5,7]), 104)
//    print(solution([90,0,0,95,1,1]), 185)

    public static int solution(int[] money) {

        int[] dp1 = new int[money.length-1];
        int[] dp2 = new int[money.length];

        // 첫 번째 집 포함
        dp1[0] = money[0];
        dp1[1] = money[0];
        for(int i = 2; i < money.length-1; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }

        // 첫 번째 집 불포함
        dp2[0] = 0;
        dp2[1] = money[1];
        for(int i = 2; i < money.length; i++){
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }

        return Math.max(dp1[money.length-2], dp2[money.length-1]);

    }
}
