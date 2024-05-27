package HashMapUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    /**
     * 使用两个数组用于记录字母出现的次数（记录字母的频率总是可以用数组而不是map，内存更小，更快）
     * 记录完毕后按照字母顺序比较，如果不同则返回false，如果走出了循环则返回true
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] anums = new int[27];
        int[] bnums = new int[27];
        for (char c : s.toCharArray()) {
            anums[c - 'a'] += 1;
        }
        for (char c : t.toCharArray()) {
            bnums[c - 'a'] += 1;
        }
        for (int i = 0; i < 27; i++) {
            if (anums[i] != bnums[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用两个数组记录字符出现的频率，如果a数组的小于b数组的则记录，大于则返回false
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] anums = new int[27];
        int[] bnums = new int[27];
        for (char c : ransomNote.toCharArray()) {
            anums[c - 'a'] += 1;
        }
        for (char c : magazine.toCharArray()) {
            bnums[c - 'a'] += 1;
        }
        for (int i = 0; i < 27; i++) {
            if (anums[i] > bnums[i]) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashSet<Integer> used = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            List<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            used.add(i);
            int[] anums = new int[27];
            for (char c : strs[i].toCharArray()) {
                anums[c - 'a'] += 1;
            }
            for (int w = i + 1; w < strs.length; w++) {
                if (used.contains(w)) {
                    continue;
                }

                if (isAnagramBetter(anums, strs[w])) {
                    temp.add(strs[w]);
                    used.add(w);
                }

            }
            result.add(temp);
        }
        return result;
    }

    public List<List<String>> groupAnagramsBetter(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            List<String> orDefault = map.getOrDefault(s, new ArrayList<>());
            orDefault.add(strs[i]);
            map.put(s, orDefault);
        }

        return new ArrayList<>(map.values());
    }

    public boolean isAnagramBetter(int[] anums, String t) {
        int[] bnums = new int[27];
        for (char c : t.toCharArray()) {
            bnums[c - 'a'] += 1;
        }
        for (int i = 0; i < 27; i++) {
            if (anums[i] != bnums[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> used = new HashSet<>();
        HashSet<Integer> nums = new HashSet<>();
        for (int i : nums2) {
            nums.add(i);
        }
        for (int i = 0; i < nums1.length; i++) {
            if (nums.contains(nums1[i])) {
                used.add(nums1[i]);
            }
        }
        int[] result = new int[used.size()];
        int count = 0;
        for (Integer integer : used) {
            result[count++] = integer;
        }
        return result;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> used = new HashSet<>();
        Integer squareNum = getSquareNum(n);
        used.add(squareNum);
        while (true) {
            squareNum = getSquareNum(squareNum);
            if (squareNum == 1) {
                return true;
            }
            if (used.contains(squareNum)) {
                return false;
            }
            used.add(squareNum);
        }
    }

    public Integer getSquareNum(int n) {
        int sum = 0;
        while (n > 0) {
            int i = n % 10;
            sum += i * i;
            n = n / 10;
        }
        return sum;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[]{-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    /**
     * 超出时间限制了
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        int[] numsA = new int[nums1.length * nums2.length];
        int[] numsB = new int[nums3.length * nums4.length];
        int count = 0;
        for (int k : nums1) {
            for (int i1 : nums2) {
                numsA[count] = k + i1;
                count++;
            }
        }
        count = 0;
        for (int j : nums3) {
            for (int i1 : nums4) {
                numsB[count] = j + i1;
                count++;
            }
        }
        for (int i : numsA) {
            for (int i1 : numsB) {
                if (i + i1 == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public int fourSumCountBetter(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        Map<Integer, Integer> mapA = new HashMap<>();
        for (int i : nums1) {
            for (int i1 : nums2) {
                int sum = i + i1;
                mapA.put(sum, mapA.getOrDefault(sum, 0) + 1);
            }
        }
        for (int i : nums3) {
            for (int j : nums4) {
                if (mapA.containsKey(-(i + j))) {
                    result += mapA.getOrDefault(-i - j, 0);
                }
            }
        }
        return result;
    }

    /**
     * 求三个数之和要满足一个条件，可以转化为固定一个元素，然后问题就转换为另外两个数加起来是否等于固定元素
     * 结果正确，但是超时了，sad:(
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            int target = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(-target - nums[j])) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(target);
                    temp.add(nums[j]);
                    temp.add(-target - nums[j]);
                    // 去重
                    result.add(temp);
                } else {
                    map.put(nums[j], j);
                }
            }
            map = new HashMap<>();
        }
        // 去重
        result.stream().forEach(x -> x.sort(Comparator.comparingInt(o -> o)));
        return result.stream().distinct().collect(Collectors.toList());
    }

    public List<List<Integer>> threeSumBetter(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    right--;
                    left++;
                } else if (sum > 0) {
                    // 右指针左移
                    right--;
                } else {
                    //
                    left++;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = threeSumFour(nums, target - nums[i], i);
            result.addAll(lists);
        }
        return result;
    }

    public List<List<Integer>> threeSumFour(int[] nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start + 1; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (i > start + 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                long sum = (long) nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    temp.add(nums[start]);
                    result.add(temp);
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    right--;
                    left++;
                } else if (sum > target) {
                    // 右指针左移
                    right--;
                } else {
                    //
                    left++;
                }
            }
        }
        return result;
    }
}
