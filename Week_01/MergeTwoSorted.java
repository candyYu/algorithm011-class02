/**
 * Created by yuping on 2020/6/27.
 */
public class MergeTwoSorted {


    public class ListNode {
           int val;
           ListNode next;
           ListNode() {}
           ListNode(int val) { this.val = val; }
           ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //递归终止条件
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val<l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }

    }

    public  ListNode mergeTwoListsMethod2(ListNode l1,ListNode l2) {
        //迭代，直接指针指向
        ListNode dumpy = new ListNode(-1);
        ListNode prev = dumpy;

        while (l1!=null && l2!=null) {
            if(l1.val<=l2.val) {
                prev.next = l1;
                l1 = l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = (l1!=null)? l1:l2;
        return dumpy.next;
    }

}
