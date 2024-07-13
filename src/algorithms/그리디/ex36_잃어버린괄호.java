package algorithms.그리디;

import java.util.Scanner;

// 백준 P1541
public class ex36_잃어버린괄호 {

    static int answer = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String example = sc.nextLine();

        String[] str = example.split("-");

        for(int i = 0; i < str.length; i++){
            int temp = mySum(str[i]);
            if(i == 0) answer += temp;
            else answer -=temp;
        }

        System.out.println(answer);

    }

    private static int mySum(String s) {
        int sum = 0;
        String[] strNum = s.split("[+]");
        for(int i = 0; i <strNum.length; i++){
            sum += Integer.parseInt(strNum[i]);
        }
        return sum;
    }
}
