package org.ieselcaminas.pmdm.minesweeper;

import android.app.ActionBar;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MineButton extends android.support.v7.widget.AppCompatImageButton {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private final float scale = getContext().getResources().getDisplayMetrics().density;
    private int row,col;
    private ButtonState state;
    private boolean isBomb;

    public MineButton(Context context,int row, int col) {
        super(context);
        this.row=row;
        this.col=col;
        isBomb = false;
        initButton();
    }

    private void initButton() {
        ViewGroup.LayoutParams params= new FrameLayout.LayoutParams((int) (WIDTH*scale),(int)(HEIGHT*scale));
        setLayoutParams(params);
        state = ButtonState.CLOSED;
        setBackgroundDrawable(getResources().getDrawable(R.drawable.boton));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    setBackgroundDrawable(getResources().getDrawable(R.drawable.boton_pressed));

                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    setBackgroundDrawable(getResources().getDrawable(R.drawable.boton));
                }
                return false;
            }
        });
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MineButton button = (MineButton) v;

                if(button.getState() == ButtonState.CLOSED){
                    button.setState(ButtonState.FLAG);

                }else {
                    if (button.getState() == ButtonState.FLAG) {
                        button.setState(ButtonState.QUESTION);

                    }else {
                        if (button.getState() == ButtonState.QUESTION) {
                            button.setState(ButtonState.CLOSED);
                        }
                    }
                }
                return false;
            }
        });
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void setState(ButtonState state) {
        this.state = state;
        switch (state) {
            case CLOSED:
                setImageDrawable(null);
                setPadding(5,5,5,5);
                setScaleType(ScaleType.FIT_XY);
                break;
            case FLAG:
                setImageDrawable(getResources().getDrawable(R.drawable.flag));
                setPadding(5,5,5,5);
                setScaleType(ScaleType.FIT_XY);
                break;
            case QUESTION:
                setImageDrawable(getResources().getDrawable(R.drawable.question));
                setPadding(5,5,5,5);
                setScaleType(ScaleType.FIT_XY);
                break;
            default:
                setImageBitmap(null);
        }
    }
    public ButtonState getState() {return state;}


}
