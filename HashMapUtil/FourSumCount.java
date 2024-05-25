package HashMapUtil;

public class FourSumCount {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num1 = new int[] {1,2};
        int[] num2 = new int[] {-2,-1};
        int[] num3 = new int[] {-1,2};
        int[] num4 = new int[] {0,2};
        System.out.println(solution.fourSumCount(num1,num2,num3,num4));
    }
}
