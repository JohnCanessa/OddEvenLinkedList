import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Nodes for linked list
 */
class ListNode {

    // **** members ****
    int val;
    ListNode next;

    // **** constructor(s) ****
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    // **** ****
    @Override
    public String toString() {
        return "" + this.val;
    }
}


/**
 * 328. Odd Even Linked List 
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {


    /**
     * Populate linked list with the specified array.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static ListNode populate(int[] arr) {

        // **** sanity ckeck(s) ****
        if (arr.length == 0)
            return null;

        // **** initialization ****
        ListNode h = null;

        // **** traverse array adding nodes to the linked list ****
        for (int i = arr.length - 1; i >= 0; i--) {
            h = new ListNode(arr[i], h);
        }

        // **** return head of linkned list ****
        return h;
    }


    /**
     * Display linked list.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void display(ListNode head) {

        // **** sanity check(s) ****
        if (head == null)
            return;

        // **** traverse the link list ****
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val);
            if (p.next != null)
                System.out.print("->");
        }
    }


    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.9 MB, less than 22.15% of Java online submissions.
     */
    static ListNode oddEvenList(ListNode head) {

        // **** sanity checks ****
        if (head == null)
            return null;

        if (head.next == null)
            return head;

        // **** initialization ****
        ListNode p          = null;
        ListNode q          = null;
        ListNode evenHead   = null;
        ListNode evenTail   = null;
        int i               = 1;

        // **** traverse the linked list O(n) ****
        for (p = head; p != null; i++) {

            // **** check we do NOT need to move this node ****
            if (i % 2 == 1) {
                q = p;
                p = p.next;
                continue;
            }

            // **** remove p from linked list ***
            q.next = p.next;
            p.next = null;

            // **** insert p to even linked list ****
            if (evenHead == null) {
                evenHead        = p;
                evenTail        = evenHead;
            } else {
                evenTail.next   = p;
                evenTail        = p;
            }

            // **** update p ****
            p = q.next;
        }

        // **** append even to odd linked list****
        q.next = evenHead;

        // **** return odd-even linked list ****
        return head;
    }


    /**
     * 
     */
    static ListNode oddEvenList1(ListNode head) {

        // **** sanity checks ****
        if (head == null)
            return null;

        // **** initialization ****
        ListNode odd        = head;
        ListNode even       = head.next;
        ListNode evenHead   = even;

        // **** traverse the linked list ****
        while (even != null && even.next != null) {
            odd.next    = even.next;
            odd         = odd.next;
            even.next   = odd.next;
            even        = even.next;
        }

        // **** append the even to the odd linked list ****
        odd.next = evenHead;

        // **** odd-even linked list ****
        return head;
    }


    /**
     * Test scafolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reder ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read and split node values ****
        String[] strs = br.readLine().trim().split(",");

        // **** close buffere reader ****
        br.close();

        // **** convert string array to integer array ****
        int[] arr = Arrays.stream(strs).mapToInt(Integer::parseInt).toArray();

        // ???? ????
        System.out.println("main <<<          arr: " + Arrays.toString(arr));

        // **** populate linked list ****
        ListNode head = populate(arr);

        // ???? ????
        System.out.print("main <<<        input: ");
        display(head);
        System.out.println();

        // **** generate odd even linked list ****
        head = oddEvenList(head);

        // **** display odd even linked list ****
        System.out.print("main <<<  oddEvenList: ");
        display(head);
        System.out.println();



        // **** populate linked list ****
        head = populate(arr);

        // **** generate odd even linked list ****
        head = oddEvenList1(head);

        // **** display odd even linked list ****
        System.out.print("main <<< oddEvenList1: ");
        display(head);
        System.out.println();
        
    }
}