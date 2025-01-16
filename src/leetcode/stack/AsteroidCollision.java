package leetcode.stack;

import java.util.Stack;

public class AsteroidCollision {

    public static void main(String[] args) {
//        int[] asteroids = {5,10,-5};
//        int[] asteroids = {8, -8};
//        int[] asteroids = {10, 2, -5};
        int[] asteroids = {1, -2, -2, -2};
        int[] result = asteroidCollision(asteroids);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty()) {
                stack.push(asteroid);
                continue;
            }

            if (stack.peek() * asteroid < 0) {  // 서로 다른 방향
                int prvAsteroid = stack.peek();
                if (prvAsteroid > 0 && asteroid < 0){
                    DFS(prvAsteroid, asteroid, stack);
                } else {
                    stack.push(asteroid);
                }
            } else {    // 서로 같은 방향
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1 ; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void DFS(int prvAsteroid, int curAsteroid, Stack<Integer> stack) {
        if (curAsteroid + prvAsteroid == 0) {   // 상쇄
            stack.pop();
        } else if (curAsteroid < 0 && prvAsteroid > 0) {    // 충돌
            if (curAsteroid + prvAsteroid < 0) {    // 새로운 운석이 더 큼
                stack.pop();
                if (!stack.isEmpty()) DFS(stack.peek(), curAsteroid, stack);
                else stack.push(curAsteroid);
            }
        } else {
            stack.push(curAsteroid);
        }
    }
}
