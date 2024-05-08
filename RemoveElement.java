public class RemoveElement {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {2};
        System.out.println(solution.removeElement(nums, 3));
        System.out.println();
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
