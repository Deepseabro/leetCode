import java.util.*;

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int sIndex = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = sIndex; j < s.length; j++) {
                if (g[i] <= s[j]) {
                    result += 1;
                    sIndex = j + 1;
                    break;
                }
            }
        }
        return result;
    }

    public int newfindContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0, cookie = 0;
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    // 遍历数组比较左右两侧大小，怎么体现贪心呢？怎么保证发得最少？
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            candy[i] = 1;
        }

        for (int i = 1; i <= ratings.length - 1; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }


        int result = 0;

        for (int i : candy) {
            result += i;
        }

        return result;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int result = 0;
        // 贪心策略-有重合则移除，需要判断左是否在区间内，右是否在区间内
        // 是否需要先根据右端点进行排序？因为数字越大囊括的区间越大，可能需要删除的越多
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int steps = 1;
        int left = 0;
        // 只使用steps来取值将导致删除元素后，不能正确比较应该比较的元素
        while (steps < intervals.length) {
            // 判断左是否在区间内，右是否在区间内, 如果左不在则右一定不在
            if (intervals[left][0] > intervals[steps][0] || intervals[left][1] > intervals[steps][0]) {
                // steps向后移动，但是left不动继续比较
                steps++;
                result++;
            } else {
                // 当前元素满足条件了，后移到删除元素后的一个
                left = steps;
                // 这里不能steps向后移动一个，因为需要比较的是left和left的下一个
                steps++;
            }
        }
        return result;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed.length < n) {
            return false;
        }
        if (flowerbed.length / 2 + 1 < n) {
            return false;
        }
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 0 && n == 1) {
                return true;
            }
        }
        int count = n;
        for (int i = 0; i < flowerbed.length; i++) {
            // 判断当前位置是否为0，如果是则盘判断左右是否有1，没有则count--
            if (flowerbed[i] == 0) {
                if (i == 0 && flowerbed[i+1] != 1) {
                    flowerbed[i] = 1;
                    count--;
                }
                if (i == flowerbed.length-1 && flowerbed[i-1] != 1) {
                    flowerbed[i] = 1;
                    count--;
                }
                if ((i != 0 && i != flowerbed.length - 1) && flowerbed[i+1] != 1 && flowerbed[i-1] != 1) {
                    flowerbed[i] = 1;
                    count--;
                }
            }
        }

        return count <= 0;
    }

    public boolean canPlaceFlowersBetter(int[] flowerbed, int n) {
        // 定义一个的数组，新数组值默认为0
        int[] a = new int[flowerbed.length + 2];
        // 赋值
        for(int i=1; i<a.length-1; i++){
            a[i] = flowerbed[i-1];
        }
        int ans = 0;
        for(int i=1; i<a.length-1; i++){
            if(a[i]==0 && a[i-1] == 0 && a[i+1] == 0){
                ans++;
                a[i] = 1;
            }
        }
        return ans >= n;
    }
}
