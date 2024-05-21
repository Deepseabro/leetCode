package LinkedList;

import java.util.Arrays;
import java.util.List;

public class ReverseList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList();
        ListNode listNode = solution.buildLinkedList(nums);
        ListNode listNode1 = solution.reverseBetter(listNode);
        solution.printList(listNode1);
    }
}
