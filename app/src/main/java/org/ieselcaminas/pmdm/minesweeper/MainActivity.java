package org.ieselcaminas.pmdm.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MineButton[][] board;
    private Singleton singleton;
    private int cols, rows;
    private GridLayout gridLayout;
    private BombMatrix bombMatrix;
    private boolean gameOver;
    private ImageButton resetButton;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetButton = findViewById(R.id.imageButton);

        singleton = singleton.getInstance();
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(singleton.getNumRows());
        gridLayout.setColumnCount(singleton.getNumCols());
        clear();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        //showBombs();

    }

    private void createButtons() {
        cols = singleton.getNumCols();
        rows = singleton.getNumRows();
        board = new MineButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frameLayout = new FrameLayout(this);
                BackCell backCell = new BackCell(this);
                frameLayout.addView(backCell);
                TextView tv = new TextView(this);
                tv.setText("" + bombMatrix.getNumber(i, j));
                tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
                frameLayout.addView(tv);
                board[i][j] = new MineButton(this, i, j);
                frameLayout.addView(board[i][j]);
                gridLayout.addView(frameLayout);

                board[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MineButton button = (MineButton) v;
                        int row = button.getRow();
                        int col = button.getCol();
                        if (button.getState() == ButtonState.CLOSED && !gameOver) {
                            if (bombMatrix.isBomb(row, col)) {
                                board[row][col].setImageDrawable(getResources().getDrawable(R.drawable.bomb));
                                board[row][col].setPadding(5, 5, 5, 5);
                                board[row][col].setScaleType(ImageView.ScaleType.FIT_XY);
                                gameOver = true;
                            } else {
                                if (bombMatrix.getNumber(row, col) == 0) {
                                    revealEmptyButtons(row, col);
                                } else {
                                    board[row][col].setState(ButtonState.OPEN);
                                    board[row][col].setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                    }

                });
            }
        }
    }

    private void revealEmptyButtons(int row, int col) {
        int maxRow = singleton.getNumRows();
        int maxCol = singleton.getNumCols();
        board[row][col].setState(ButtonState.OPEN);
        board[row][col].setVisibility(View.INVISIBLE);

        if (bombMatrix.getNumber(row, col) == 0) {
            if (row + 1 < maxRow) {
                if (board[row + 1][col].getState() == ButtonState.CLOSED) {
                    revealEmptyButtons(row + 1, col);
                }
                if (col + 1 < maxCol) {
                    if (board[row + 1][col + 1].getState() == ButtonState.CLOSED) {
                        revealEmptyButtons(row + 1, col + 1);
                    }
                }
                if (col - 1 >= 0) {
                    if (board[row + 1][col - 1].getState() == ButtonState.CLOSED) {
                        revealEmptyButtons(row + 1, col - 1);
                    }
                }
            }
            if (row - 1 >= 0) {
                if (board[row - 1][col].getState() == ButtonState.CLOSED) {
                    revealEmptyButtons(row - 1, col);
                }
                if (col - 1 >= 0) {
                    if (board[row - 1][col - 1].getState() == ButtonState.CLOSED) {
                        revealEmptyButtons(row - 1, col - 1);
                    }
                }
                if (col + 1 < maxCol) {
                    if (board[row - 1][col + 1].getState() == ButtonState.CLOSED) {
                        revealEmptyButtons(row - 1, col + 1);
                    }
                }
            }
            if (col - 1 >= 0) {
                if (board[row][col - 1].getState() == ButtonState.CLOSED) {
                    revealEmptyButtons(row, col - 1);
                }
            }
            if (col + 1 < maxCol) {
                if (board[row][col + 1].getState() == ButtonState.CLOSED) {
                    revealEmptyButtons(row, col + 1);
                }

            }
        }


    }

    private void clear() {
        gridLayout.removeAllViews();
        bombMatrix = new BombMatrix(this);
        createButtons();
        gameOver = false;
    }

    private void showBombs() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (bombMatrix.isBomb(i, j)) {
                    board[i][j].setImageDrawable(getResources().getDrawable(R.drawable.bomb));
                    board[i][j].setPadding(5, 5, 5, 5);
                    board[i][j].setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        }
    }
}
