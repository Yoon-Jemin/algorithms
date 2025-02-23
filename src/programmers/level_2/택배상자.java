package programmers.level_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 택배상자 {

    public static void main(String[] args) {
        int[] order = {1, 2, 3, 4, 5};
//        int[] order = {4,3,1,2,5};
        System.out.println(solution(order));
    }

    public static int solution(int[] order) {
        int n = order.length;

        int next = 0;
        Stack<Integer> stack = new Stack<>();
        List<Integer> truck = new ArrayList<>();

        for (int box = 1; box <= n; box++) {
            if (box != order[next]) {
                stack.push(box);
                continue;
            }

            next++;
            truck.add(box);

            while (!stack.isEmpty()) {
                if (stack.peek() == order[next]) {
                    truck.add(stack.pop());
                    next++;
                } else {
                    break;
                }
            }
        }

        return truck.size();
    }
}
