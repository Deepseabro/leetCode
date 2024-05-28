package StringUtil;

import java.util.Arrays;

public class Solution {

    /**
     * 反转字符串和数组，经典问题
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[right];
            s[right] = s[left];
            s[left] = temp;
            left++;
            right--;
        }
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i+= 2 * k) {
            if (i + k <= chars.length) {
                reverseStringUse(chars, i, i + k - 1);
                continue;
            }
            reverseStringUse(chars, i, chars.length - 1);
        }
        return new String(chars);
    }

    public void reverseStringUse(char[] s, int i, int j) {
        int left = i;
        int right = j;
        while (left < right) {
            char temp = s[right];
            s[right] = s[left];
            s[left] = temp;
            left++;
            right--;
        }
    }

}
