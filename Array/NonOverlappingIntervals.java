package Array;

public class NonOverlappingIntervals {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = new int[][]{{1,2},{1,2},{1,2}};
        System.out.println(solution.eraseOverlapIntervals(intervals));
    }
}
