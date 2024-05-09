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
            if (ratings[i] > ratings[i + 1]) {
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
                if (i == 0 && flowerbed[i + 1] != 1) {
                    flowerbed[i] = 1;
                    count--;
                }
                if (i == flowerbed.length - 1 && flowerbed[i - 1] != 1) {
                    flowerbed[i] = 1;
                    count--;
                }
                if ((i != 0 && i != flowerbed.length - 1) && flowerbed[i + 1] != 1 && flowerbed[i - 1] != 1) {
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
        for (int i = 1; i < a.length - 1; i++) {
            a[i] = flowerbed[i - 1];
        }
        int ans = 0;
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] == 0 && a[i - 1] == 0 && a[i + 1] == 0) {
                ans++;
                a[i] = 1;
            }
        }
        return ans >= n;
    }

    /**
     * 基础思路是按照下标遍历一遍，遇到就射箭-最大射箭数
     * 考虑要使用最小的箭，要从跨度最大的开始考虑
     * 先排序，排序完后从最大的开始遍历下标
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 1) {
            return 1;
        }
        int result = 0;
        // 完成排序, o[0] -> (0[0][0], o[0][1])
        Arrays.sort(points, (o1, o2) -> (o2[1] - o2[0]) - (o1[1] - o1[0]));
        Set<Integer> usedIndex = new HashSet<>();
        // 遍历数组
        for (int i = 0; i < points.length; i++) {
            // 遍历坐标
            if (usedIndex.contains(i)) {
                continue;
            }
            usedIndex.add(i);
            int start = points[i][0];
            int end = points[i][1];
            Map<Integer, Set<Integer>> map = new HashMap<>();
            int beforeLength = -1;
            int maxIndex = -1;
            while (start <= end) {
                Set<Integer> temp = new HashSet<>();
                for (int i1 = 0; i1 < points.length; i1++) {
                    // 不比较当前的
                    if (usedIndex.contains(i1)) {
                        continue;
                    }
                    // 能够击中其他气球，要记录个数，记录坐标，set既可以取到个数也可以取到下标
                    if (points[i1][0] <= start && start <= points[i1][1]) {
                        temp.add(i1);
                    }
                }
                map.put(start, temp);
                if (temp.size() > beforeLength) {
                    beforeLength = temp.size();
                    maxIndex = start;
                }
                start++;
            }
            if (maxIndex != -1) {
                usedIndex.addAll(map.get(maxIndex));
                map = new HashMap<>();
            }
            result++;
        }
        return result;
    }

    public int findMinArrowShotsBetter(int[][] points) {
        int result = 1;
        // 完成排序, o[0] -> (0[0][0], o[0][1])
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        // 遍历数组，排序之后只用一直向前遍历就行，比较当前和下一个气球能不能一起打爆，如果可以就不加
        for (int i = 1; i < points.length; i++) {
            // 两个气球没有碰到一起，已经按X_start排序了，因此不可能出现points[i][0] > points[i-1][1]但是重叠的情况
            if (points[i][0] > points[i - 1][1]) {
                result++;
            } else {
                // 如果两个气球是碰到一起的，则需要融合边界
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return result;
    }

    /**
     * 需要记录每个字符第一次和最后一次出现的位置，转换成区间
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        // 先转换成区间，然后看区间内的是否都满足条件，满足就归并为以一个区间，不满足则查找下一个元素的最后位置
        Map<Character, int[][]> partition = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // partition包含则表示已经计算过该字符的区间了
            if (partition.containsKey(s.charAt(i))) {
                continue;
            }
            int[][] index = new int[][]{{i, s.lastIndexOf(s.charAt(i))}};
            partition.put(s.charAt(i), index);
        }

        for (int i = 0; i < s.length(); ) {
            int[][] ints = partition.get(s.charAt(i));
            // 判断区间内元素是否满足条件
            int start = ints[0][0];
            int end = ints[0][1];
            // 可以优化的地方：不用再去遍历区间内的元素，可以直接遍历每个元素，动态更新end位置，如果被包含则end不变，如果未包含则end自动扩大
            // 当end == i时，则说明当前区间是满足条件的
            while (start <= end) {
                int[][] ints1 = partition.get(s.charAt(start));
                // 超出区域
                if (ints1[0][1] > end) {
                    end = ints1[0][1];
                }
                start++;
            }
            result.add(end - ints[0][0] + 1);
            i = end + 1;
        }
        return result;
    }

    public List<Integer> partitionLabelsBetter(String s) {
        //用来标记每个字母最后一次出现的位置
        int[] last = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            //记录每个字母最后一次出现的位置
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> list = new ArrayList();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            //若加入当前字符，最远位置应该在之前最远和当前最远取最大值
            end = Math.max(end, last[s.charAt(i) - 'a']);
            //若遍历到最远的位置依然没有更远的位置出现，证明已经到达之前字符串的最远位置，可以分割
            if (i == end) {
                //分割字符串，添加这段串的长度
                list.add(end - start + 1);
                //把起始位置更新到下个串的第一位，也就是上个串的后一位
                start = end + 1;
            }
        }
        return list;
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

    /**
     * x 的平方根
     * 通过二分查找，比如9的平方根，则答案一定在[1,9]之间，通过二分法找到最相近的
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return x;
        }
        int left = 1;
        int right = x;
        int mid, sqrt;
        while (left <= right) {
            mid = left + (right - left) / 2;
            sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            } else if (mid > sqrt) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 问题在于在找到一个满足条件的元素后，如何判断是第几个元素，怎么继续查找(元素处在中间如何解决)-两边分别查询
     * 大体思路上还是二分法，但是循环如何进行
     * 如果找到了一个满足条件的元素后，右边的值一定大于等于target
     * 如果只找到一个，那么答案一定是这一个元素加上他的相邻上一个
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[] {-1,-1};
        int left = 0;
        int right = nums.length - 1;
        int mid;
        List<Integer> index = new ArrayList<>();
        // 查右边
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                index.add(mid);
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 查左边
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                index.add(mid);
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (index.size() == 1) {
            result[0] = index.getFirst();
            result[1] = index.getFirst();
        } else if (index.size() > 1){
            result[0] = index.stream().reduce(index.get(0), Integer::min);
            result[1] = index.stream().reduce(index.get(0), Integer::max);
        }
        return result;
    }

    /**
     * 简单的思路：要删除元素，并且返回删除后的长度，则删除元素置为0，长度遇到val则减一；
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int fastIndex = 0;
        int slowIndex = 0;
        int count = 0;
        while (fastIndex < nums.length) {
            if (nums[fastIndex] == val) {
                fastIndex++;
            } else {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
                fastIndex++;
                count++;
            }
        }
        return count;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int slowIndex = 0;
        int fastIndex = 1;
        int count = 1;
        while (fastIndex < nums.length) {
            if (nums[fastIndex] != nums[slowIndex]) {
                nums[++slowIndex] = nums[fastIndex];
                count++;
            }
            fastIndex++;
        }
        return count;
    }

    /**
     * 思路一：遇到0就跟末尾的元素交换，但是不满足保持非零元素的相对顺序
     * 思路二：先把非0元素提前，再处理末尾0
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slowIndex = 0;
        int fastIndex = 0;
        int count = 0;
        // 将非0元素提前
        while (fastIndex < nums.length) {
            if (nums[fastIndex] != 0) {
                nums[slowIndex++] = nums[fastIndex];
            } else {
                count++;
            }
            fastIndex++;
        }
        // 处理末尾0元素
        for (int i = nums.length - count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 思路：其实跟上面移除0的类似，就是把不需要移除的元素提前，然后根据长度和内容比较，
     * 怎么移除不清楚，因为需要处理两个元素，涉及到长度移除判断
     * 思路二：搞个栈，读到#出栈元素，其他元素进栈，然后比较
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                if (stack.size() > 0) {
                    stack.pop();
                }
            } else {
                stack.push(chars[i]);
            }
        }
        String st = stack.toString();

        stack = new Stack<>();
        chars = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                if (stack.size() > 0) {
                    stack.pop();
                }
            } else {
                stack.push(chars[i]);
            }
        }
        String tt = stack.toString();
        return st.equals(tt);
    }

    /**
     * 双指针问题，指针不但可以往前走，也可以往后走，思路不要太固定
     * 本题思路：快慢指针，当前元素不是#号时，快指针给慢指针赋值，为#时则慢指针后退
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompareBetter(String s, String t) {
        return removeBack(s).equals(removeBack(t));
    }

    public String removeBack(String s) {
        int slowIndex = 0;
        int fastIndex = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#' && slowIndex != 0) {
                slowIndex--;
            } else if (chars[i] != '#'){
                chars[slowIndex++] = chars[fastIndex];
            }
            fastIndex++;
        }
        return String.valueOf(chars, 0, slowIndex);
    }
}
