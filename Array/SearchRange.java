package Array;

import java.util.Arrays;

public class SearchRange {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {5,7,7,8,8,8,8,8,8,8,10};
        int[] ints = solution.searchRange(nums, 8);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
