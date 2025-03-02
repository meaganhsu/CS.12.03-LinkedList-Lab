package impl;

import common.InvalidIndexException;
import common.InvalidListException;
import common.ListNode;
import interfaces.IFilterCondition;
import interfaces.IListManipulator;
import interfaces.IMapTransformation;
import interfaces.IReduceOperator;

/**
 * This class represents the iterative implementation of the IListManipulator interface.
 *
 */
public class ListManipulator implements IListManipulator {

    @Override
    public int size(ListNode head) {
        if (isEmpty(head)) return 0;      // if head is empty, the size is 0

        int cnt = 1;
        ListNode pointer = head.next;

        while (pointer != head) {    // while pointer doesn't go back to the first (circular)
            cnt++;       // count for each node
            pointer = pointer.next;
        }

        return cnt;
    }

    @Override
    public boolean isEmpty(ListNode head) {
        return head == null;
    }

    @Override
    public boolean contains(ListNode head, Object element) {
        if (isEmpty(head)) return false;      // can't contain element if the list is empty

        ListNode pointer = head.next;

        while (pointer != head) {    // circular linked list
            if (pointer.element.equals(element)) return true;     // returns true if element is in the middle of the list
            pointer = pointer.next;         // moves to next node
        }

        return head.element.equals(element);      // returns whether the head = element
    }

    @Override
    public int count(ListNode head, Object element) {
        if (isEmpty(head)) return 0;

        int cnt = 0;
        ListNode pointer = head.next;

        if (head.element.equals(element)) cnt++;       // if the head = element then add 1

        while (pointer != head) {
            if (pointer.element.equals(element)) cnt++;       // increase count when the pointer finds an element
            pointer = pointer.next;         // moves to next node
        }

        return cnt;
    }

    @Override
    public String convertToString(ListNode head) {
        if (isEmpty(head)) return "";

        String str = head.element + "";
        ListNode pointer = head.next;

        while (pointer != head) {
            str += "," + pointer.element;        // add comma and element to the string
            pointer = pointer.next;
        }

        return str;       // return string
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (isEmpty(head) || size(head) < n) throw new InvalidIndexException();     // exception when list is empty or n is invalid

        ListNode pointer = head;

        for (int i = 0; i < n; i++) {     // starts from the beginning of the list, traverses nodes until n count
            pointer = pointer.next;
        }

        return pointer.element;      // returning node element at n
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
        if (isEmpty(head) || size(head) < n) throw new InvalidIndexException();

        ListNode pointer = head;

        for (int i = size(head)-1; i > n; i--) {      // starts at end of list, traverses nodes until n count
            pointer = pointer.next;
        }

        return pointer.element;      // returning node element at n
    }

    @Override
    public boolean equals(ListNode head1, ListNode head2) {
        if (size(head1) != size(head2)) return false;         // lists can't be equal if sizes are different
        if (isEmpty(head1) && isEmpty(head2)) return true;      // equal if both lists are empty

        ListNode pointer1 = head1;     // pointer for head1 list
        ListNode pointer2 = head2;     // pointer for head2 list

        for (int i = 0; i < size(head1); i++) {
            if (!pointer1.element.equals(pointer2.element)) return false;      // if nodes at any point are not the same, returns false
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;         // both pointer goes to the next node in the list
        }

        return true;
    }

    @Override
    public boolean containsDuplicates(ListNode head) {
        if (isEmpty(head)) return false;

        ListNode pointer1 = head;
        ListNode pointer2;

        for (int i = 0; i < size(head); i++) {
            pointer2 = pointer1.next;
            for (int j = i+1; j < size(head); j++) {
                if (pointer1.element.equals(pointer2.element)) return true;      // compares one node with the rest of the nodes
                pointer2 = pointer2.next;
            }
            pointer1 = pointer1.next;
        }

        return false;
    }

    @Override
    public ListNode addHead(ListNode head, ListNode node) {
        if (isEmpty(head)) {       // assigns the new node as the head of the list if the list is empty
            head = node;
            head.previous = node;      // making it circular
            head.next = node;
            return node;
        }

        ListNode last = head.previous;

        // putting the node as the head
        last.next = node;
        node.previous = last;
        node.next = head;

        return node;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        if (isEmpty(head1)) return head2;
        else if (isEmpty(head2)) return head1;

        ListNode tail1 = head1.previous;
        ListNode tail2 = head2.previous;

        // attaching the tail of list1 to the head of list2
        tail1.next = head2;
        head2.previous = tail1;
        tail2.next = head1;
        head1.previous = tail2;

        return head1;
    }

    public ListNode insert(ListNode head, ListNode node, int n) throws InvalidIndexException {
        if (n > size(head)) throw new InvalidIndexException();      // cannot insert a node if n > size of the list
        if (n == size(head)) return append(head, node);             // add to the end of the list when n = size
        if (isEmpty(head) || n == 0) return addHead(head, node);        // if n = 0 or list is empty, node is the head

        ListNode pointer = head;
        int ntemp = n;

        while (n != 0) {
            pointer = pointer.next;      // point to the pointer after insertion
            n--;
        }

        ListNode pointer2 = pointer.previous;       // point before insertion

        pointer2.next = node;
        node.previous = pointer2;
        node.next = pointer;
        pointer.previous = node;

        return head;
    }

    public ListNode delete(ListNode head, Object elem) {
        if (isEmpty(head) || (head.element.equals(elem) && size(head) == 1)) return null;

        boolean found = false;
        ListNode pointer = head;

        for (int i = 0; i < size(head); i++) {       // finding node to be deleted
            if (pointer.element.equals(elem)) {
                found = true;
                break;
            }
            pointer = pointer.next;
        }

        if (!found) return head;    // node doesn't exist

        ListNode behind = pointer.previous;
        ListNode front = pointer.next;

        behind.next = front;
        front.previous = behind;

        // new head if previous head was deleted
        if (head.element.equals(elem)) head = front;

        return head;
    }

    @Override
    public ListNode reverse(ListNode head) {
        if (isEmpty(head)) return null;
        if (size(head) == 1) return head;

        ListNode front = head;
        ListNode back = head.previous;

        while (front != back) {
            Object elem = front.element;      // swap back and front elements
            front.element = back.element;
            back.element = elem;

            front = front.next;      // moving front forwards
            back = back.previous;     // moving back backwards
        }

        return head;
    }

    @Override
    public ListNode split(ListNode head, ListNode node) throws InvalidListException {
        if (isEmpty(head) || head == node || node == null) throw new InvalidListException();

        ListNode pointer = head.next;

        ListNode list1Head = head;
        ListNode list2Head = node;

        while (pointer != node) {    // adding to first list
            ListNode newNode = new ListNode(pointer.element);
            list1Head.next = newNode;
            newNode.previous = list1Head;
            list1Head = newNode;

            pointer = pointer.next;
        }

        list1Head.next = list1Head;
        list1Head.previous = list1Head;

        pointer = node.next;
        while (pointer != head) {    // adding to second list
            list2Head.next = pointer;
            pointer.previous = list2Head;
            list2Head = pointer;

            pointer = pointer.next;
        }

        list2Head.next = list2Head;
        list2Head.previous = list2Head;

        // combining for return
        ListNode out = new ListNode(list1Head);
        out.next = new ListNode(list2Head);
        out.next.previous = out;
        out.previous = out.next;
        out.next.next = out;

        return out;
    }

    @Override
    public ListNode map(ListNode head, IMapTransformation transformation) {
        if (isEmpty(head)) return head;      // nothing to transform if list is empty

        head.element = transformation.transform(head.element);
        ListNode pointer = head.next;

        while (pointer != head) {
            pointer.element = transformation.transform(pointer.element);     // transforming each node elem
            pointer = pointer.next;
        }

        return head;
    }

    @Override
    public Object reduce(ListNode head, IReduceOperator operator, Object initial) {
        if (head == null) return initial;      // nothing to operate on initial

        ListNode pointer = head;
        boolean first = true;

        while (pointer != head || first) {
            initial = operator.operate(pointer.element, initial);    // operating node and initial
            pointer = pointer.next;

            first = false;
        }

        return initial;
    }

    @Override
    public ListNode filter(ListNode head, IFilterCondition condition) {
        if (head == null || (!condition.isSatisfied(head.element) && size(head) == 1)) return null;

        ListNode pointer = head;       // pointer for original list
        ListNode outHead = new ListNode(-1);       // list to be returned
        ListNode pointer2 = outHead;      // pointer for returned list
        boolean first = true;      // first iteration boolean

        while (pointer != head || first) {
            if (condition.isSatisfied(pointer.element)) {      // if node satisfies condition
                if (outHead.element.equals(-1)) {         // if first node, makes new head for filtered list
                    outHead.element = pointer.element;
                    outHead.next = outHead;
                    outHead.previous = outHead;
                    pointer2 = outHead;
                }
                else {
                    ListNode add = new ListNode(pointer.element);       // adding to filtered list
                    pointer2.next = add;
                    add.previous = pointer2;
                    pointer2 = add;
                }
            }

            first = false;
            pointer = pointer.next;
        }

        return outHead;
    }

}
