package Array;

import java.util.List;

public class SpiralOrder {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> integers = solution.spiralOrder(matrix);
        System.out.println(integers);
    }
}
