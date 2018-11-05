package org.ieselcaminas.pmdm.minesweeper;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class BombMatrix {
    private int[][] matrix;
    private Singleton singleton;

    public BombMatrix(Context context) {
        singleton = Singleton.getInstance();
        matrix = new int[singleton.getNumRows()][singleton.getNumCols()];
        for (int i = 0; i < singleton.getNumRows(); i++) {
            for (int j = 0; j < singleton.getNumCols(); j++) {
                matrix[i][j] = 0;
            }
        }
        createRandomBombs();
        calculateNumbers();
    }

    public TextView getTextView(Context context, int i, int j) {
        TextView textview = null;
        return textview;
    }

    public void createRandomBombs() {
        Random r = new Random();
        int row, col;
        int counter = 0;
        while (singleton.getNumBombs() > counter) {
            row = r.nextInt(singleton.getNumRows());
            col = r.nextInt(singleton.getNumCols());
            if (matrix[row][col] != -1) {
                matrix[row][col] = -1;
                counter++;
            }
        }

    }

    public void calculateNumbers() {
        int maxRow = singleton.getNumRows() ;
        int maxCol = singleton.getNumCols() ;
        for (int i = 0; i < singleton.getNumRows(); i++) {
            for (int j = 0; j < singleton.getNumCols(); j++) {
                if (matrix[i][j] == -1) {
                    if (i + 1 < maxRow) {
                        if (matrix[i + 1][j] >= 0)
                            matrix[i + 1][j] += 1;

                        if (j + 1 < maxCol) {
                            if (matrix[i + 1][j + 1] >= 0)
                                matrix[i + 1][j + 1] += 1;
                        }
                        if (j - 1 >= 0) {
                            if (matrix[i + 1][j - 1] >= 0)
                                matrix[i + 1][j - 1] += 1;
                        }
                    }
                    if (i - 1 >= 0) {
                        if (matrix[i - 1][j] >= 0)
                            matrix[i - 1][j] += 1;
                        if (j - 1 >= 0) {
                            if (matrix[i - 1][j - 1] >= 0)
                                matrix[i - 1][j - 1] += 1;
                        }
                        if (j + 1 < maxCol) {
                            if (matrix[i - 1][j + 1] >= 0)
                                matrix[i - 1][j + 1] += 1;
                        }
                    }

                    if (j -1  >= 0) {
                        if (matrix[i][j - 1] >= 0)
                            matrix[i][j - 1] += 1;
                    }

                    if (j + 1 < maxCol) {
                        if (matrix[i][j + 1] >= 0)
                            matrix[i][j + 1] += 1;
                    }
                }
            }
        }
    }

    public boolean isBomb(int row, int col){
        return matrix[row][col] == -1;
    }

    public int getNumber(int row, int col){
        return  matrix[row][col];
    }

}
