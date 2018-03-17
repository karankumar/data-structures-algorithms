package org.careercup.ctci;

import java.util.*;

class Arrays {
    public static void main(String args[]){
        //System.out.println(isUnique("unique"));
        //System.out.println(checkPermutations("dogs", "god"));
        //System.out.println(urlify2("I am sam      ", 8));
        //System.out.println(isPermutationOfPalindrome("t eest"));
        //System.out.println(isOneAway("pale", "paleo"));
        //System.out.println(compress("aaaabsbcc123pppppz"));

        int[][] table = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        rotateMatrix(table);
        //setZero(table);
        //hashtableExample();
    }

    public static boolean isUnique(String input){
        Set<Character> resultSet = new HashSet();
        for (int i=0; i < input.length(); i++) {
            resultSet.add(input.charAt(i));
        }
        return (resultSet.size() == input.length());
    }

    public static boolean isUnique2(String input){
        boolean[] checker = new boolean[128];
        for (int i = 0; i < input.length(); i++) {
            int ascii = (int) input.charAt(i);
            System.out.println(ascii);
            if(checker[ascii])
                return false;
            else
                checker[ascii] = true;
        }
        return true;
    }

    public static boolean checkPermutations(String s1, String s2){
        if(s1.length() == s2.length()){
            int [] place = new int[128];
            for (char c1: s1.toCharArray()){
                place[(int) c1] = c1;
            }
            for (char c2: s2.toCharArray()){
                if(place[(int)c2] == '\0'){
                    return false;
                }
            }
            return true;
        } else{
            return false;
        }
    }

    public static String urlify(String s, int length){
        int spaces = (s.length() - length)/3;
        String res = "";
        for (int i=0; i<spaces; i++){
            res = res + s.substring(0, s.indexOf(" ")) + "%20";
            s = s.substring(s.indexOf(" ") +1 , s.length());
        }
        return res + s.substring(0, s.indexOf(" "));
    }

    //  FIXME
    public static String urlify2(String s, int length){
        int l = s.length();
        int spaces = (l - length)/3;
        String url = "";
        for (int i=0; i<spaces; i++){
            if(s.indexOf(" ") != -1){
                url = url + s.substring(0, s.indexOf(" ")-1);
                s = s.substring(s.indexOf(" ")+1, l);
                l = l - s.indexOf(" ");
            }else{
                url = url + s;
            }
        }
        return url;
    }

    public static boolean isPermutationOfPalindrome(String s){
        Map<Character, Integer> freq = new HashMap<>();

        int singleOccCount = 0;
        int nonSpaceChars = 0;

        for(char c: s.toCharArray()){
            if(c == ' '){
                continue;
            } else{
                nonSpaceChars ++;
                int o = (freq.get(c) != null) ? (freq.get(c) + 1) : 1;
                if (o > 2)
                    return false;
                freq.put(c, o);
            }
        }

        boolean singleOccAllowed = (nonSpaceChars %2 == 0) ? false : true;
        for (Map.Entry<Character, Integer> e: freq.entrySet()) {
            int occ = freq.get(e.getKey());
            if(occ > 2 || (!singleOccAllowed && occ == 1)){
                return false;
            }
            if(singleOccAllowed && occ == 1){
                singleOccCount ++;
            }
            if(singleOccAllowed && singleOccCount > 1){
                return false;
            }
        }
        return true;
    }


    public static boolean isOneAway(String s1, String s2){
        int diffs = 0;
        if(Math.abs(s1.length() - s2.length()) >1){
            return false;
        }
        if(Math.abs(s1.length() - s2.length()) == 1){
            diffs++;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 =  s2.toCharArray();
        for (int i=0,j=0; (i<s1.length() && j<s2.length()); i++,j++){
            if(c1[i] != '\0' && c2[j] != '\0'){
                if(c1[i] != c2[j]){
                    if(c1[i+1] == c2[j]){
                        diffs++;
                        i++;
                    } else if(c1[i] == c2[j+1]){
                        diffs++;
                        j++;
                    } else {
                        diffs++;
                    }
                }
                if(diffs > 1) return false;
            }
            //diffs++;
        }
        return true;
    }

    //  O(N^2)
    //  FIXME: this sucks, do it in O(N)
    public static String compress(String s){
        String res = "";
        char[] c = s.toCharArray();
        for (int i=0;i<c.length;i++) {
            int count = 1;
            if (i != c.length-1 && c[i] == c[i + 1]) {
                for (int j = i + 1; j < s.length(); j++) {
                    if (c[i] == c[j]) {
                        count++;
                    } else{
                        break;
                    }
                }
            }
            if (count > 1){
                res = res + (c[i] + String.valueOf(count));
            } else{
                res = res + c[i];
            }
            i=(i+count) -1;
        }
        return res;
    }

    //  O(N^2)
    public static boolean rotateMatrix(int[][] m){
        /*int[][] res = new int[m.length][m.length];
        for (int r=0;r<m.length;r++){
            for (int c=m.length-1, j=0;c>=0;c--,j++){
                res[r][j] = m[c][r];
            }

        }*/

        if (m.length == 0 || m. length != m[0].length) return false;
        int n = m. length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for(int i = first; i < last; i++) {
                int offset = i-first;
                int top = m[first][i]; // save top
                // left -> top
                m[first][i] = m[last-offset] [first];
                // bottom -> left
                m[last-offset] [first] = m[last][last - offset];
                // right -> bottom
                m[last][last - offset] = m[i][last];
                // top -> right
                m[i][last] = top; // right <- saved top
            }
        }

        return true;
    }

    private static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }


    //  O(n^2)
    public static int[][] setZero(int[][] input){
        int [][] zeroed = new int[input.length][input.length];
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for (int i=0;i<input.length;i++) {
            for (int j = 0; j < input.length; j++) {
                if(input[i][j] == 0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i=0;i<input.length;i++) {
            for (int j = 0; j < input.length; j++) {
                if(rows.contains(i)){
                    zeroed[i][j] = 0;
                } else if(cols.contains(j)){
                    zeroed[i][j] = 0;
                } else{
                    zeroed[i][j] = input[i][j];
                }
            }
        }
        printMatrix(zeroed);
        return zeroed;
    }

    public static void hashtableExample(){
        Hashtable<Integer, Integer> ht = new Hashtable<>();
        ht.put(1,1);
        ht.put(1,2);
        ht.put(1,3);
        System.out.println(ht.size());
        System.out.println(ht.get(1));
        System.out.println("ht = " + ht);
    }
}
