package org.careercup.ctci;

import java.util.EmptyStackException;
import java.util.Set;

public class Stacks {

    public class MyStack<T>{

        public MyStack() {}

        public MyStack(T data) {
            push(data);
        }

        public class StackNode<T>{
            T data;
            StackNode<T> next;

            StackNode(T data){
                this.data = data;
            }
        }

        StackNode<T> top;

        public boolean isEmpty(){
            if(top == null){
                throw new EmptyStackException();
            }
            return false;
        }

        public T peek(){
            if(top == null){
                throw new EmptyStackException();
            }
            return top.data;
        }

        public void push(T element){
            StackNode<T> newElement = new StackNode(element);
            if(top != null){
                newElement.next = top;
            }
            top = newElement;
        }

        public T pop(){
            if(top == null){
                throw new EmptyStackException();
            }
            StackNode<T> oldTop = top;
            top = oldTop.next;
            return oldTop.data;
        }
    }

    public class MinStack<T> extends MyStack{

    }


    public class SetOfStacks<T>{

        public class SetOfStacksNode<T> {
            MyStack<T> stack;
            SetOfStacksNode<T> next;
            int stackIndex;
            SetOfStacksNode<T> prev;

            public SetOfStacksNode(T data) {
                this.stack = new MyStack(data);
            }
        }

        SetOfStacksNode<T> head;
        int elementCount;
        int currentStackIndex;
        int maxEle;

        public SetOfStacks(int maxEle) {
            currentStackIndex = 1;
            this.maxEle = maxEle;
            elementCount = 0;
        }

        public void push(T data){
            elementCount ++;
            if(elementCount < maxEle){
                head.stack.push(data);
            }

            if(elementCount == maxEle){
                elementCount = 0;
                SetOfStacksNode oldHead = head;
                SetOfStacksNode newHead = new SetOfStacksNode(data);
                this.head = newHead;
                head.next = oldHead;
                head.stackIndex = currentStackIndex;
                currentStackIndex ++;
            }
        }

        public T pop(){
            T pop = null;
            elementCount --;
            if(elementCount >= 0){
                pop = head.stack.pop();
            }

            if(elementCount < 0){
                elementCount = maxEle;

                if(head.prev == null){
                    throw new EmptyStackException();
                } else {
                    SetOfStacksNode newHead = head.prev;
                    head = newHead;
                    pop = head.stack.pop();
                    elementCount--;
                }
            }
            return pop;
        }
    }

    void sort(MyStack<Integer> s) {
        MyStack<Integer> r = new MyStack<Integer>();
        while(s.top != null) {
             /* Insert each element in s in sorted order into r. */
            int tmp = s.pop();
            while(!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop());
            }
            r.push(tmp);
        }

         /* Copy the elements from r back into s. */
        while (!r.isEmpty()) {
            s.push(r.pop());
        }
    }

    public static void main(String[] args) {

    }
}
