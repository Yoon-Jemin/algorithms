package leetcode.array;

import java.util.*;

public class MaximumSetSize {

    public static void main(String[] args) {
//        int[] nums1 = {1,2,3,4,5,6};
//        int[] nums2 = {2,3,2,3,2,3};
        int[] nums1 = {2, 4, 1, 4};
        int[] nums2 = {10, 2, 4, 10};
        System.out.println(maximumSetSize(nums1, nums2));
    }

    public static int maximumSetSize(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        int n = nums1.length / 2;

        for (int i = 0; i < nums1.length; i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
            set.add(nums1[i]);
            set.add(nums2[i]);
        }

        int count1 = GetOnlyOne(n, map1, map2, result, set);
        int count2 =  GetOnlyOne(n, map2, map1, result, set);

        for (int num : set) {
            if (count1 < n && map1.containsKey(num)) {
                count1++;
                result.add(num);
                map1.put(num, map1.get(num) -1);
                if (map1.get(num) == 0) map1.remove(num);
            } else if (count2 < n && map2.containsKey(num)) {
                count2++;
                result.add(num);
                map2.put(num, map2.get(num) -1);
                if (map2.get(num) == 0) map2.remove(num);
            }
        }

        return result.size();
    }

    private static int GetOnlyOne(int n, Map<Integer, Integer> myMap, Map<Integer, Integer> otherMap, Set<Integer> result, Set<Integer> set) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (Integer key : myMap.keySet()) {
            if (count == n) break;
            if (!otherMap.containsKey(key)) {
                result.add(key);
                list.add(key);
                set.remove(key);
                count++;
            }
        }

        for (int l : list) {
            if (myMap.get(l) == 1) myMap.remove(l);
            else myMap.put(l, myMap.get(l) - 1);
        }

        return count;
    }
}
