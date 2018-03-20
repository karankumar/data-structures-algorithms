package org.careercup.ctci.medium;

import java.util.ArrayList;
import java.util.List;

public class PondSizes {

    public List<Integer> computePondSizes(int[][] land){
        List<Integer> result = new ArrayList<>();
        int cols = land[0].length;
        int rows = land.length;

        boolean[][] covered = new boolean[rows][cols];

        for(int i=0; i < cols; i++){
            for(int j=0; j < rows; j++){
                //System.out.print(land[i][j] + "\t");
                if(land[i][j] == 0  && !covered[i][j]){
                    result.add(getSize(land, covered, i, j));
                }
            }
            //System.out.println();
        }
        return result;
    }

    public int getSize(int[][] land, boolean[][] covered, int rowIndex, int colIndex){

        //  If the index is outside land[][], return immediately
        int cols = land[0].length;
        int rows = land.length;
        if(rowIndex >= rows || colIndex >= cols || rowIndex < 0 || colIndex < 0){
            return 0;
        }

        //  If the element is covered or piece of land is not a pond
        if(covered[rowIndex][colIndex]  || land[rowIndex][colIndex] != 0){
            return 0;
        }

        //  Mark the piece of land as covered and recurse
        covered[rowIndex][colIndex] = true;

        return 1 + getSize(land, covered, rowIndex + 1, colIndex) +
                getSize(land, covered, rowIndex, colIndex + 1) +
                getSize(land, covered, rowIndex + 1, colIndex + 1) +
                getSize(land, covered, rowIndex + 1, colIndex - 1);
    }
}
