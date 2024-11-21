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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(ListNode head, Object element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int count(ListNode head, Object element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String convertToString(ListNode head) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(ListNode head1, ListNode head2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsDuplicates(ListNode head) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ListNode addHead(ListNode head, ListNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {
        // TODO Auto-generated method stub
        return null;
    }

    public ListNode insert(ListNode head, ListNode node, int n) throws InvalidIndexException {
        // TODO Auto-generated method stub
        return null;
    }

    public ListNode delete(ListNode head, Object elem) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode reverse(ListNode head) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListNode split(ListNode head, ListNode node) throws InvalidListException {
        // TODO Auto-generated method stub
        return null;
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
