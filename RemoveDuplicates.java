public class RemoveDuplicates {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        System.out.println(solution.removeDuplicates(nums));
        System.out.println();
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
