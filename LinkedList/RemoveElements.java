package LinkedList;

import java.util.Arrays;
import java.util.List;

public class RemoveElements {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(7,7,7);
        ListNode listNode = solution.buildLinkedList(nums);
        ListNode listNode1 = solution.removeElements(listNode, 7);
        while(listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }
}
