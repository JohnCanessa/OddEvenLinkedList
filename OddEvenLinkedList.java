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
     * Populate linked list from the contents of the specified array.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static ListNode populate(int[] arr) {

        // **** sanity ckeck(s) ****
        if (arr.length == 0) return null;

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
    static String display(ListNode head) {

        // **** sanity check(s) ****
        if (head == null) return "";

        // **** initialization ****
        StringBuilder sb = new StringBuilder();

        // **** traverse link list ****
        for (ListNode p = head; p != null; p = p.next) {
            sb.append(p.val);
            if (p.next != null)
                sb.append("->");
        }

        // **** ****
        return sb.toString();
    }


    /**
     * Given a singly linked list, 
     * group all odd nodes together followed by the even nodes.
     * 
     * Execution: O(n) - Space: O(1)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.9 MB, less than 22.15% of Java online submissions.
     */
    static ListNode oddEvenList0(ListNode head) {

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
     * Given a singly linked list, 
     * group all odd nodes together followed by the even nodes.
     * 
     * Execution: O(n) - Space: O(1)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.6 MB, less than 52.56% of Java online submissions.
     * 
     * 70 / 70 test cases passed.
     * Status: Accepted
     * Runtime: 0 ms
     * Memory Usage: 38.6 MB
     */
    static ListNode oddEvenList1(ListNode head) {

        // **** sanity check(s) ****
        if (head == null) return null;
        if (head.next == null) return head;

        // **** initialization ****
        ListNode evenHead   = null;
        ListNode evenTail   = null;

        ListNode p          = head; 
        ListNode e          = head.next;

        // **** ****
        while (p.next != null) {

            // **** point to next even node ****
            e = p.next;

            // **** point to next odd node ****
            p.next  = e.next;

            // **** ****
            if (e.next != null)
                p = e.next;

            // **** move even node to even list ****
            if (evenHead == null)
                evenHead = e;
            else 
                evenTail.next = e;
        
            // **** update even next node ****
            e.next      = null;

            // **** update even tail ****
            evenTail    = e;
        }

        // **** append even to odd linked list ****
        p.next = evenHead;

        // **** return head of odd-even linked list ****
        return head;
    }


    /**
     * Given a singly linked list, 
     * group all odd nodes together followed by the even nodes.
     * 
     * Execution: O(n) - Space: O(1)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.4 MB, less than 79.60% of Java online submissions.
     * 
     * 70 / 70 test cases passed.
     * Status: Accepted
     * Runtime: 0 ms
     * Memory Usage: 38.4 MB
     */
    static ListNode oddEvenList2(ListNode head) {

        // **** sanity check(s) ****
        if (head == null) return null;
        if (head.next == null) return head;

        // **** initialization ****
        ListNode evenHead   = null;         // even linked list head
        ListNode evenTail   = null;         // even linked list tail

        ListNode tail       = null;         // input list tail node

        // **** traverse the input linked list ****
        for (ListNode p = head; p != null; p = p.next) {

            // **** check if we are done traversing the linked list ****
            if (p.next == null) {
                tail = p;
                break;
            }

            // **** point to next even node ****
            ListNode even = p.next;

            // **** set tail of linked list ****
            if (even.next == null) tail = p;

            // *** skip the even node ****
            p.next = even.next;

            // **** add even node to tail of evenHead ****
            even = new ListNode(even.val);

            // **** update the head and tail of the even linked list ****
            if (evenHead == null) {
                evenHead = even;
                evenTail = even;
            } else {
                evenTail.next = even;
                evenTail = even;
            }
        }

        // **** append even to odd linked list ****
        tail.next = evenHead;

        // **** return head of odd-even linked list ****
        return head;
    }


    /**
     * Given a singly linked list, 
     * group all odd nodes together followed by the even nodes.
     * 
     * Execution: O(n) - Space: O(1)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.9 MB, less than 22.15% of Java online submissions.
     */
    static ListNode oddEvenList(ListNode head) {

        // **** sanity check(s) ****
        if (head == null) return null;
        if (head.next == null) return head;

        // **** initialization ****
        ListNode odd        = head;         // first odd node
        ListNode even       = head.next;    // first odd node
        ListNode evenHead   = even;         // first even node

        // **** traverse the linked list ****
        while (even != null && even.next != null) {

            // **** point to next odd node  ****
            odd.next    = even.next;
            odd         = odd.next;

            // **** point to next even node ****
            even.next   = odd.next;
            even        = even.next;
        }

        // **** append even to odd linked list ****
        odd.next = evenHead;

        // **** return head of odd-even linked list ****
        return head;
    }


    /**
     * Test scafolding
     * !!! NOT PART OF SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reder ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read and split node values ****
        String[] strs = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // **** convert string array to integer array ****
        int[] arr = Arrays.stream(strs).mapToInt(Integer::parseInt).toArray();

        // ???? ????
        System.out.println("main <<<          arr: " + Arrays.toString(arr));


        // **** populate linked list ****
        ListNode head = populate(arr);

        // ???? ????
        System.out.println("main <<<        input: " + display(head));

        // **** generate odd even linked list ****
        head = oddEvenList0(head);

        // **** display odd even linked list ****
        System.out.println("main <<< oddEvenList0: " + display(head));


        // **** populate linked list ****
        head = populate(arr);

        // **** generate odd even linked list ****
        head = oddEvenList1(head);

        // **** display odd even linked list ****
        System.out.println("main <<< oddEvenList1: " + display(head));


        // **** populate linked list ****
        head = populate(arr);

        // **** generate odd even linked list ****
        head = oddEvenList2(head);

        // **** display odd even linked list ****
        System.out.println("main <<< oddEvenList2: " + display(head));


        // **** populate linked list ****
        head = populate(arr);

        // **** generate odd even linked list ****
        head = oddEvenList(head);

        // **** display odd even linked list ****
        System.out.println("main <<<  oddEvenList: " + display(head));
    }
}