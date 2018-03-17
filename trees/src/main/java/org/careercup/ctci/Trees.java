package org.careercup.ctci;

import java.util.LinkedList;

public class Trees {

    class BinaryTree {

        public BinaryTree(int root){
            this.value = root;
        }

        BinaryTree left;
        BinaryTree right;
        int value;
    }

    public BinaryTree createTree(int root, int[] elements) {
        BinaryTree r = new BinaryTree(root);
        System.out.println("Building tree with root value " + r.value);
        for (int i = 0; i < elements.length; i++) {
            insert(r, elements[i]);
        }
        return r;
    }

    public void insert(BinaryTree node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                System.out.println("  Inserted " + value + " to left of "
                        + node.value);
                node.left = new BinaryTree(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                System.out.println("  Inserted " + value + " to right of "
                        + node.value);
                node.right = new BinaryTree(value);
            }
        }
    }

    public BinaryTree getTree() {
        return createTree(5, new int[]{2,1,8,6,3,9});
        /*     5
        *    /   \
        *   2     8
        *  / \   / \
        * 1   3 6   9 */
    }

    public static void main(String[] args) {
        Trees t = new Trees();
        BinaryTree tree = t.getTree();
        System.out.println("\n------------- Executing program --------------");

        tree.left.value = 25;
        System.out.println("t.validateBST(tree) = " + t.validateBST(tree));
    }

    LinkedList<Integer> ll;

    public boolean validateBST(BinaryTree n){
        if(n != null){
            validateBST(n.left);
            if(!visit(n))
                return false;
            validateBST(n.right);
        }
        return true;
    }

    public boolean visit(BinaryTree n){
        //System.out.println("node = " + n.value);

        if(ll == null){
            ll = new LinkedList<>();
            ll.add(n.value);
            return true;
        }

        System.out.println("ll.peek() = " + ll.peekLast());
        System.out.println("n.value = " + n.value);

        if(ll.peekLast() > n.value){
            return false;
        }
        else
            ll.add(n.value);
        return true;
    }
}