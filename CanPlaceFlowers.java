public class CanPlaceFlowers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] flowerbed = new int[] {0,0,1,0,0};
        int n = 1;
        System.out.println(solution.canPlaceFlowers(flowerbed, n));
    }
}
