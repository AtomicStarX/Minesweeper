package org.ieselcaminas.pmdm.minesweeper;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BackCell extends android.support.v7.widget.AppCompatImageView {

    public BackCell(Context context) {
        super(context);
        setImageDrawable(getResources().getDrawable(R.drawable.back));
        final float scale = getContext().getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams((int) (org.ieselcaminas.pmdm.minesweeper.MineButton.WIDTH * scale),
                        (int) (scale * org.ieselcaminas.pmdm.minesweeper.MineButton.HEIGHT));
        setLayoutParams(layoutParams);
    }

}
