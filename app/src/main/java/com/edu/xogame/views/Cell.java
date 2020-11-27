package com.edu.xogame.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.xogame.datastructure.CellPosition;
import com.edu.xogame.R;

public class Cell extends androidx.appcompat.widget.AppCompatImageView implements View.OnClickListener {

    public static final int EMPTY_IMAGE = R.drawable.cell;
    public static final int X_IMAGE = R.drawable.cell_x;
    public static final int O_IMAGE = R.drawable.cell_o;
    private final CellPosition cellPosition;
    private final Board board;
    public Cell(Context context, Board board, CellPosition cellPosition) {
        super(context);
        this.cellPosition = cellPosition;
        this.board = board;
        setScaleType(ImageView.ScaleType.FIT_XY);
        setImageResource(EMPTY_IMAGE);
        setClickable(true);
        setOnClickListener(this);
    }

    public void check(int status_image) {

        setImageResource(status_image);
    }
    public void uncheck() {

        setImageResource(EMPTY_IMAGE);
    }
    public CellPosition getCellPosition() {
        return cellPosition;
    }

    @Override
    public void onClick(View v) {
        if (board.getGame().isMyTurn())
            board.checkCell(this);
    }

    @Override
    public int hashCode() {
        return cellPosition.hashCode();
    }
}
