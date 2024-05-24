package HashMapUtil;

public class Intersection {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[] {4,9,5};
        int[] nums2 = new int[] {9,4,9,8,4};
        int[] intersection = solution.intersection(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }
}
