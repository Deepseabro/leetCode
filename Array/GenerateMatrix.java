package Array;

public class GenerateMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ints = solution.generateMatrix(5);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                System.out.printf("%03d   ",ints[i][j]);
            }
            System.out.println();
        }
    }
}
