package org.careercup.ctci;

import java.util.HashSet;

class LinkedLists {
    public static void main(String args[]) {
        LinkedLists foo = new LinkedLists();
        int[] values = new int[]{3,5,8,5,10,2,1};
        //foo.deleteDups(foo.createLinkedList(values));
        //foo.deleteDups(foo.createLinkedList(values));
        //foo.kthToLast(foo.createLinkedList(values), 3);
        //foo.deleteMiddleNode(foo.createLinkedList(), new Node(3));
        //foo.partition(foo.createLinkedList(new int[]{3,5,8,5,10,2,1}), 5);
        //foo.addLists(foo.createLinkedList(new int[]{7,1,6,1}), foo.createLinkedList(new int[]{5,9,2}));

        /*Node n = new Node(200);
        LinkedList l1 = foo.createLinkedList(new int[]{1,2,3,4});
        LinkedList l2 = foo.createLinkedList(new int[]{9,8,6,7});
        foo.addNodeToList(n, l1);
        foo.addNodeToList(n, l2);
        //  l1 and l2 intersect at node n. tested by commenting out above lines
        System.out.println("isIntersecting = " + foo.isIntersecting(l1,l2));*/

        System.out.println(" CN= " + foo.findCorrupt(foo.createCorruptLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10})).data);

    }

    void deleteDups(LinkedList list) {
        HashSet<Integer> set = new HashSet<Integer>();
        Node n = list.head;
        while (n.next != null) {
            Node nextNode = n.next;
            if(set.contains(nextNode.data)){
                n.next = nextNode.next;
            } else {
                set.add(nextNode.data);
            }
            n=n.next;
        }
        printLinkedList(list);
    }

    void kthToLast(LinkedList list, int k){
        Node base = list.head;
        Node runner = list.head;
        int i = 1;
        while(runner.next != null){
            if(i > k){
                base = base.next;
            }
            i++;
            runner = runner.next;
        }
        System.out.println("i = " + i);
        System.out.println("data in " + k + "th to last node = " + base.data);
    }

    void deleteMiddleNode(LinkedList list, Node n){
        Node runner = list.head;
        while(runner.next != null){
            Node nextNode = runner.next;
            if(nextNode.data == n.data){
                runner.next = nextNode.next;
            }
            runner = runner.next;
        }
        printLinkedList(list);
    }

    public void partition (LinkedList l, int partition){
        LinkedList before = new LinkedList();
        LinkedList after = new LinkedList();
        Node n = l.head;

        while(n != null){
            if(n.data < partition){
                before.appendToList(n.data);
            } else {
                after.appendToList(n.data);
            }
            n=n.next;
        }
        before.appendToList(after.head);
        printLinkedList(before);
    }

    public void addLists(LinkedList l1, LinkedList l2){
        LinkedList sum = new LinkedList();
        Node n1 = l1.head;
        Node n2 = l2.head;
        int carryOver = 0;

        while(n1 != null || n2 != null){
            int d1 = (n1 != null) ? n1.data : 0;
            int d2 = (n2 != null) ? n2.data : 0;
            int sumData = d1 + d2;
            sum.appendToList((sumData % 10) + carryOver);
            carryOver = sumData / 10;
            n1 = (n1 != null) ? n1.next : n1;
            n2 = (n2 != null) ? n2.next : n2;
        }
        printLinkedList(sum);
    }

    //  O(MN) - Solvable in O(M+N) by comparing the last node of both lists as equal
    public boolean isIntersecting(LinkedList l1, LinkedList l2){
        Node n1 = l1.head;
        while(n1 != null){
            Node n2 = l2.head;
            while(n2 != null){
                if(n1==n2) return true;
                n2 = n2.next;
            }
            n1 = n1.next;
        }
        return false;
    }

    public Node findCorrupt(LinkedList l){
        Node slow = l.head;
        Node fast = l.head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        if(fast == null || fast.next == null){
            return null;
        }

        slow = l.head;

        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    /*public LinkedList<Integer> removeDuplicatesWithoutBuffer(LinkedList<Integer> input){
        LinkedList<Integer> resp = new LinkedList<>(input);
        int len = input.size();

        for (int i=0; i<len; i++) {
            LinkedList<Integer> temp = new LinkedList<>(input);
            for (int j=i+1; j< len - i; j++) {
                if(input.get(i).equals(temp.get(j))){
                    resp.remove(i);
                }
            }
        }
        System.out.println("resp = " + resp);
        return resp;
    }*/

    public LinkedList createCorruptLinkedList(int[] values){
        LinkedList linkedList = new LinkedList();
        Node n = new Node(911);
        for (int i = 0; i < values.length; i++) {
            if(i == 3)  linkedList.appendToList(n);
            linkedList.appendToList(values[i]);
        }
        linkedList.appendToList(n);
        // INFINITE LOOP! printLinkedList(linkedList);
        return linkedList;
    }

    public LinkedList createLinkedList(int[] values){
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < values.length; i++) {
            linkedList.appendToList(values[i]);
        }
        return linkedList;
    }

    public LinkedList addNodeToList(Node nodeToAdd, LinkedList l){
        l.appendToList(nodeToAdd);
        return l;
    }

    public void printLinkedList(LinkedList linkedList){
        Node test = linkedList.head;
        while(test != null){
            System.out.println("test.data = " + test.data + " " + test);
            test = test.next;
        }
    }
}

class LinkedList{
    Node head;

    LinkedList (){}

    public void appendToList(int d){
        Node n = head;

        //  If head is null
        if(n == null){
            head = new Node(d);
            return;
        }

        while(n.next != null){
            n= n.next;
        }
        //  Set the new node as next to the last item of the list
        n.next = new Node(d);
    }

    public void appendToList(Node newNode){
        Node n = head;

        //  If head is null
        if(n == null){
            head = newNode;
            return;
        }

        while(n.next != null){
            n= n.next;
        }
        //  Set the new node as next to the last item of the list
        n.next = newNode;
    }

    public void delete(int d){
        if(d == head.data){
            Node newHead = head.next;
            head = newHead;
        }

        Node n = head;
        while(n.next != null){
            //  Always searching for the next element to make sure we have access to the previous element
            Node node = n.next;
            if (node.data == d){
                n.next = node.next;
            }
            n=n.next;
        }
    }

}

class Node {
    int data;
    Node next;

    Node (int d){
        this.data = d;
    }
}


/*
class LinkedListNode {
    public LinkedListNode next, prev, last;
    public int data;
    public LinkedListNode(int d, LinkedListNode n, LinkedListNode p){
        data = d;
        setNext(n);
        setPrevious(p);
    }

    public LinkedListNode(int d) {
        data = d;
    }

    public LinkedListNode() { }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", data)
                .append("next", next)
                .append("prev", prev)
                .append("last", last)
                .toString();
    }

    public void setNext(LinkedListNode n) {
        next = n;
        if (this == last) {
            last = n;
        }
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }

    public void setPrevious(LinkedListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }

    public LinkedListNode clone() {
        LinkedListNode next2 = null;
        if (next != null) {
            next2 = next.clone();
        }
        LinkedListNode head2 = new LinkedListNode(data, next2, null);
        return head2;
    }
}*/
