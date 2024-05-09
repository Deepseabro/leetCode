import java.util.Arrays;

public class MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {0,1,0,3,12};
        solution.moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
