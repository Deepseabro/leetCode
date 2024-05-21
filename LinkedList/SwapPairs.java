package LinkedList;

import java.util.Arrays;
import java.util.List;

public class SwapPairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(1,2);
        ListNode listNode = solution.buildLinkedList(nums);
        ListNode result = solution.swapPairs(listNode);
        solution.printList(result);
    }
}
