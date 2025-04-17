package programmers.level_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 줄서는방법 {

    public static void main(String[] args) {
        int[] result = solution(3, 5);
        for (int r : result) {
            System.out.print(r + " ");
        }
    }

    public static int[] solution(int n, long k) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) numbers.add(i);

        int[] answer = new int[n];
        k--;

        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        for (int i = 0; i < n; i++) {
            int idx = (int)(k / factorial[n - 1 - i]);
            answer[i] = numbers.get(idx);
            numbers.remove(idx);
            k = k % factorial[n - 1 - i];
        }

        return answer;
    }


//    public static int[] solution(int n, long k) {
//        List<List<Integer>> res = new ArrayList<>();
//        backTrack(new HashSet<>(), new ArrayList<>(), n, res, k);
//
//        List<Integer> answer = res.get((int)k-1);
//        return answer.stream().mapToInt(i->i).toArray();
//    }
//
//    private static void backTrack(Set<Integer> visited, List<Integer> list, int n, List<List<Integer>> res, long k) {
//        if (list.size() == n) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//
//        if (res.size() >= k) return;
//
//        for (int i = 1; i <= n; i++) {
//            if (!visited.contains(i)) {
//                visited.add(i);
//                list.add(i);
//                backTrack(visited, list, n, res, k);
//                list.remove(list.size() - 1);
//                visited.remove(i);
//            }
//        }
//    }
}
