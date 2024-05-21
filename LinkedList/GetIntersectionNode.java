package LinkedList;

import java.util.Arrays;
import java.util.List;

public class GetIntersectionNode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> nums = Arrays.asList(1);
        ListNode headA = solution.buildLinkedList(nums);
        ListNode headB = solution.buildLinkedList(nums);
        ListNode intersectionNode = solution.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode.val);
    }
}
