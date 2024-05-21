package LinkedList;

import java.util.Arrays;
import java.util.List;

public class RemoveNthFromEnd {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(1);
        ListNode listNode = solution.buildLinkedList(nums);
        ListNode result = solution.removeNthFromEnd(listNode, 1);
        solution.printList(result);
    }
}
