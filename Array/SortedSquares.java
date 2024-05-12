package Array;

public class SortedSquares {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {-7,-3,2,3,11};
        int[] ints = solution.sortedSquares(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
