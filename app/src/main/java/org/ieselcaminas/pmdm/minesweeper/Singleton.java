package org.ieselcaminas.pmdm.minesweeper;

/**
 * Created by victor on 15/10/14.
 */
public class Singleton {
    private static Singleton singleton;
    public final int BUTTON_WIDTH = 45;
    public final int BUTTON_HEIGHT = 45;
    private int numRows = 10;
    private int numCols = 10;
    private int numBombs = 10;
    private Singleton() {
    }
    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }

        return singleton;
    }

    public int getNumRows() {return numRows;}
    public int getNumCols() {return numCols;}
    public int getNumBombs() {return numBombs;}

    public void setNumRows(int numRows){
        singleton.numRows=numRows;}
    public void setNumCols(int numCols){
        singleton.numCols=numCols;}


}
