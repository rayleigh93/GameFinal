package com.game.ozanne.gameoz.GameActivity.View;

import android.widget.RelativeLayout;

public interface GameBaseView<T> {



    void setPresenter(T presenter);


    void initView();

    RelativeLayout getGrid();

    void setGridGameView(GridGameView gridGameView);
    GridGameView getGridGameView();
}