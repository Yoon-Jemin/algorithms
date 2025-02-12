package leetcode.hashtable;

import java.util.*;

public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(majorityElement(nums));
    }

    public static List<Integer> majorityElement(int[] nums) {
        Set<Integer> res = new HashSet<>();

        int limit = nums.length / 3;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            if (map.get(num) > limit) res.add(num);
        }

        return new ArrayList<>(res);
    }
}
