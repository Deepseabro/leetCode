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

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = removeSpace(s);
        chars = stringBuilder.toString().toCharArray();
        // 反转
        reverseStringUse(chars, 0, stringBuilder.length() - 1);
        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverseStringUse(chars, left, i - 1);
                left = i + 1;
            }
        }
        if (left < chars.length) {
            reverseStringUse(chars, left, chars.length - 1);
        }
        return new String(chars);
    }

    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

}
