package com.game.ozanne.gameoz.GameActivity.Model;

import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GridGameObject {


    private List<LinearLayout> mListLayout;
    private List<ImageView> mListImageView;

    public GridGameObject() {
        this.mListLayout = new ArrayList<>();
        this.mListImageView = new ArrayList<>();
    }




    public List<LinearLayout> getmListLayout() {
        return mListLayout;
    }

    public void setmListLayout(List<LinearLayout> mListLayout) {
        this.mListLayout = mListLayout;
    }

    public List<ImageView> getmListImageView() {
        return mListImageView;
    }

    public void setmListImageView(List<ImageView> mListImageView) {
        this.mListImageView = mListImageView;
    }
}
