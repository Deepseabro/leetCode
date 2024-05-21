package LinkedList;

import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    public ListNode buildLinkedList(List<Integer> nums) {
        ListNode head = new ListNode();
        ListNode p = head;
        for (int i = 0; i < nums.size(); i++) {
            p.val = nums.get(i);
            if (i != nums.size() - 1) {
                ListNode listNode = new ListNode();
                p.next = listNode;
                p = listNode;
            }
        }
        return head;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode p = dummyNode;
        ListNode result = head;
        while (p != null && p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummyNode.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        ListNode cur = dummyNode;
        for (int i = 0; i < n + 1; i++) {
            cur = cur.next;
        }
        while (cur != null) {
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return dummyNode.next;
    }

    /**
     * 最简单的思路就是遍历一次把元素都存起来，然后再反向遍历一次
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummyNode = new ListNode(-1, head);
        List<Integer> nums = new ArrayList<>();
        ListNode cur = head;
        ListNode pre = dummyNode;
        while (cur != null) {
            nums.add(cur.val);
            cur = cur.next;
        }
        cur = head;
        for (int i = nums.size() - 1; i >= 0; i--) {
            cur.val = nums.get(i);
            cur = cur.next;
        }
        return dummyNode.next;
    }

    public ListNode reverseListRuc(ListNode head) {
        ListNode listNodeTmp = null;
        if (head.next != null) {
            listNodeTmp = reverseListRuc(head.next);
        }
        ListNode listNode = new ListNode();
        if (listNodeTmp != null) {
            listNodeTmp.next = listNode;
            listNode.val = head.val;
            return listNode;
        } else {
            listNode.val = head.val;
            return listNode;
        }
    }

    public ListNode reverseBetter(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 两个指针一前一后，临时指针保存后的下一个，交换后前等于前的next，后等于前的next，保存temp
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1, head);
        ListNode result = dummyNode;
        ListNode pre = head;
        ListNode cur = head.next;
        while (pre != null && pre.next != null) {
            dummyNode.next = cur;
            ListNode temp = cur.next;
            cur.next = pre;
            pre.next = temp;

            dummyNode = pre;
            pre = pre.next;
            if (pre != null) {
                cur = pre.next;
            } else {
                cur = null;
            }
        }
        return result.next;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    /**
     * 相交的意思是headA.next == headB.next，主要解决两个链表长度不一致的问题
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int aLength = getLength(headA);
        int bLength = getLength(headB);
        if (aLength > bLength) {
            int length = aLength - bLength;
            while (length > 0) {
                headA = headA.next;
                length--;
            }
        } else if (aLength < bLength) {
            int length = bLength - aLength;
            while (length > 0) {
                headB = headB.next;
                length--;
            }
        }
        // 处于同一位置
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }

    public int getLength(ListNode head) {
        int result = 0;
        while (head != null) {
            result++;
            head = head.next;
        }
        return result;
    }

    public ListNode getIntersectionNodeBetter(ListNode headA, ListNode headB) {

        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

}
