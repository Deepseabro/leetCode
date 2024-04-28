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
}
