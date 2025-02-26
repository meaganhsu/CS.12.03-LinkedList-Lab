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
        if (isEmpty(head)) return 0;

        int cnt = 1;
        ListNode pointer = head.next;

        while (pointer != head) {    // circular linked list
            cnt++;
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
            if (pointer.element.equals(element)) cnt++;
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
            str += "," + pointer.element;
            pointer = pointer.next;
        }

        return str;
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        if (isEmpty(head) || size(head) < n) throw new InvalidIndexException();

        ListNode pointer = head;

        for (int i = 0; i < n; i++) {
            pointer = pointer.next;
        }

        return pointer.element;
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
        if (isEmpty(head) || size(head) < n) throw new InvalidIndexException();

        ListNode pointer = head;

        for (int i = size(head)-1; i > n; i--) {
            pointer = pointer.next;
        }

        return pointer.element;
    }

    @Override
    public boolean equals(ListNode head1, ListNode head2) {
        if (size(head1) != size(head2)) return false;
        if (isEmpty(head1) && isEmpty(head2)) return true;

        ListNode pointer1 = head1;
        ListNode pointer2 = head2;

        for (int i = 0; i < size(head1); i++) {
            if (!pointer1.element.equals(pointer2.element)) return false;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
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
                if (pointer1.element.equals(pointer2.element)) return true;
                pointer2 = pointer2.next;
            }
            pointer1 = pointer1.next;
        }

        return false;
    }

    @Override
    public ListNode addHead(ListNode head, ListNode node) {
        if (isEmpty(head)) {
            head = node;
            head.previous = node;
            head.next = node;
            return node;
        }

        ListNode last = head.previous;

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

        tail1.next = head2;
        head2.previous = tail1;
        tail2.next = head1;
        head1.previous = tail2;

        return head1;
    }

    public ListNode insert(ListNode head, ListNode node, int n) throws InvalidIndexException {
        if (n > size(head)) throw new InvalidIndexException();
        if (n == size(head)) return append(head, node);
        if (isEmpty(head) || n == 0) return addHead(head, node);

        ListNode pointer = head;
        int ntemp = n;

        while (n != 0) {
            pointer = pointer.next;
            System.out.println(pointer.element);
            n--;
        }

        ListNode pointer2 = pointer.previous;

        pointer2.next = node;
        node.previous = pointer2;
        node.next = pointer;
        pointer.previous = node;

        if (ntemp == 0) head = pointer;

        return head;
    }

    public ListNode delete(ListNode head, Object elem) {
        if (isEmpty(head) || (head.element.equals(elem) && size(head) == 1)) return null;

        boolean found = false;
        ListNode pointer = head;

        for (int i = 0; i < size(head); i++) {
            if (pointer.element.equals(elem)) {
                found = true;
                break;
            }
            pointer = pointer.next;
        }

        if (!found) return head;

        ListNode behind = pointer.previous;
        ListNode front = pointer.next;

        behind.next = front;
        front.previous = behind;

        if (head.element.equals(elem)) head = front;

        return head;
    }

    @Override
    public ListNode reverse(ListNode head) {
        if (isEmpty(head)) return null;
        if (size(head) == 1) return head;

        ListNode front = head;
        ListNode back = head.previous;

        for (int i = 0; i < size(head)/2; i++) {
            Object elem = front.element;
            front.element = back.element;
            back.element = elem;
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

        ListNode out = new ListNode(list1Head); out.next = new ListNode(list2Head);
        out.next.previous = out;
        out.previous = out.next;
        out.next.next = out;

        return out;
    }

    @Override
    public ListNode map(ListNode head, IMapTransformation transformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object reduce(ListNode head, IReduceOperator operator, Object initial) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode filter(ListNode head, IFilterCondition condition) {
        // TODO Auto-generated method stub
        return null;
    }


}
