package HashMapUtil;

import java.util.*;

public class Solution {

    /**
     * 使用两个数组用于记录字母出现的次数（记录字母的频率总是可以用数组而不是map，内存更小，更快）
     * 记录完毕后按照字母顺序比较，如果不同则返回false，如果走出了循环则返回true
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] anums = new int[27];
        int[] bnums = new int[27];
        for (char c : s.toCharArray()) {
            anums[c - 'a'] += 1;
        }
        for (char c : t.toCharArray()) {
            bnums[c - 'a'] += 1;
        }
        for (int i = 0; i < 27; i++) {
            if (anums[i] != bnums[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用两个数组记录字符出现的频率，如果a数组的小于b数组的则记录，大于则返回false
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] anums = new int[27];
        int[] bnums = new int[27];
        for (char c : ransomNote.toCharArray()) {
            anums[c - 'a'] += 1;
        }
        for (char c : magazine.toCharArray()) {
            bnums[c - 'a'] += 1;
        }
        for (int i = 0; i < 27; i++) {
            if (anums[i] > bnums[i]) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashSet<Integer> used = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            List<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            used.add(i);
            int[] anums = new int[27];
            for (char c : strs[i].toCharArray()) {
                anums[c - 'a'] += 1;
            }
            for (int w = i + 1; w < strs.length; w++) {
                if (used.contains(w)) {
                    continue;
                }

                if (isAnagramBetter(anums, strs[w])) {
                    temp.add(strs[w]);
                    used.add(w);
                }

            }
            result.add(temp);
        }
        return result;
    }

    public List<List<String>> groupAnagramsBetter(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            List<String> orDefault = map.getOrDefault(s, new ArrayList<>());
            orDefault.add(strs[i]);
            map.put(s, orDefault);
        }

        return new ArrayList<>(map.values());
    }

    public boolean isAnagramBetter(int[] anums, String t) {
        int[] bnums = new int[27];
        for (char c : t.toCharArray()) {
            bnums[c - 'a'] += 1;
        }
        for (int i = 0; i < 27; i++) {
            if (anums[i] != bnums[i]) {
                return false;
            }
        }
        return true;
    }
}
