package programmers.고득점Kit.깊이너비우선탐색;

public class ex1_타겟넘버 {

    public static void main(String[] args) {
        int[] numbers = {4,1,2,1};
        int target = 4;

        System.out.println(solution(numbers, target));
    }

    static int answer;
    static int size;
    static int[] nums;
    public static int solution(int[] numbers, int target) {
        answer = 0;
        size = numbers.length;
        nums = numbers;

        DFS(numbers[0], 1, target);
        DFS(-numbers[0], 1, target);

        return answer;
    }

    private static void DFS(int number, int depth, int target) {
        if(depth == size){
            if(number == target) answer++;
            return;
        }

        int plusNumber = number + nums[depth];
        int plusDepth = depth+1;
        DFS(plusNumber, plusDepth, target);

        int minusNumber = number - nums[depth];
        int minusDepth = depth+1;
        DFS(minusNumber, minusDepth, target);

        depth++;

    }
}
