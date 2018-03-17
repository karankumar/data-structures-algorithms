package org.careercup.ctci;

import java.util.*;

/**
 * Describe how you could use a single array to implement three stacks.
 */
public class ArrayStack {

    public int[] array;
    public List<Integer> topList;
    public int growthFactor;
    public int numberOfStacks;

    public ArrayStack(int growthFactor, int numberOfStacks) {
        this.growthFactor = growthFactor;
        this.numberOfStacks = numberOfStacks;
        topList = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++) {
            topList.add(i);
        }
        array = new int[topList.size() * growthFactor];
    }

    public void push(int stackNumber, int newElement){
        int topIndex = topList.get(stackNumber - 1);
        checkOverflow(stackNumber);

        array[topIndex] = newElement;
        topList.set(stackNumber -1, topIndex + numberOfStacks);

    }

    public int pop(int stackNumber){
        int topIndex = topList.get(stackNumber - 1);
        if(topIndex - numberOfStacks < 0){
            throw new EmptyStackException();
        }

        int poppedElement = array[topIndex];
        topList.add(stackNumber -1, topList.get(stackNumber -1) - numberOfStacks);
        return poppedElement;
    }

    private void checkOverflow(int stackNumber){
        if(array.length < topList.get(stackNumber - 1)){
            resizeArray();
        }
    }

    private void resizeArray(){
        int[] tmp = Arrays.copyOf(array, array.length * growthFactor);
        this.array = tmp;
    }

    public static void main(String[] args) {
        ArrayStack as = new ArrayStack(3, 3);
        as.push(1,1);
        as.push(1,2);
        as.push(1,3);

        as.push(2,10);
        as.push(2,11);
        as.push(2,12);

        as.push(3,20);
        as.push(3,21);
        as.push(3,22);

        System.out.println("as.array = " + Arrays.toString(as.array));
        System.out.println("tops = " + as.topList.get(0)+ "," +as.topList.get(1)+ "," +as.topList.get(2));

    }
}