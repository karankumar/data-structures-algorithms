package org.careercup.ctci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion{
    public static void main(String[] args) {
        Recursion rec = new Recursion();
        //System.out.println("rec.fact(5) = " + rec.fact(5));
        //rec.printArray(new int[]{1,2,3,4,5});
        //System.out.println("isPalindrome = " + rec.isPalindrome("kaylk"));
        //System.out.println("rec.pow(base,exp) = " + rec.pow(2, 3));
        //System.out.println("rec.jsMap() = " + Arrays.toString(rec.jsMap(new int[]{1,2,3,4,5})));
        System.out.println("\"llo\".substring(2) = " + "llo".substring(2));

    }

    public int fact(int num){
        if(num < 2) return 1;
        return fact(num - 1) * num;
    }

    public String reverseString(String s){
        if(s.length() < 2) return s;
        return reverseString(s.substring(1)) + s.charAt(0);

        /*if(s.length() ==0) return "";
        return  reverseString(s.substring(1)) + s.charAt(0);*/
    }

    public void printArray(int[] arr){
        if(arr.length == 0) return;
        printArray(Arrays.copyOfRange(arr, 0, arr.length -1));
        System.out.println(arr[arr.length -1]);
    }

    public boolean isPalindrome(String s){
        if(s.length() < 2) return true;
        if(s.charAt(0) != s.charAt(s.length() -1 )){
            return false;
        } else {
            return isPalindrome(s.substring(1,s.length()-1));
        }
    }

    public int pow(int base, int exp){
        if(exp == 0) return 1;
        return pow(base, exp-1) * base;
    }

    public int[] jsMap(int[] arr){
        if(arr.length == 0) return new int[5];  //  FIXME: How do i do this without knowing the length of the arr?
        int[] newArr = jsMap(Arrays.copyOfRange(arr, 0, arr.length -1));
        newArr[arr.length - 1] = arr[arr.length -1] * 2;
        return newArr;
    }

}