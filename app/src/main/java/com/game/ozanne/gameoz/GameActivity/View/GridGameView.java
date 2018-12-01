package com.game.ozanne.gameoz.GameActivity.View;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.ClickOnCaseGrilleGame;


import java.util.ArrayList;
import java.util.List;



public class GridGameView implements View.OnClickListener {

    private List<LinearLayout> mListLayout;
    private List<ImageView> mListImageView;
    private ClickOnCaseGrilleGame mListenerClick;
    private LinearLayout mLayoutParent;

    public GridGameView(ClickOnCaseGrilleGame clickOnCaseGrilleGame) {
        this.mListLayout = new ArrayList<>();
        this.mListImageView = new ArrayList<>();
        this.mListenerClick = clickOnCaseGrilleGame;
    }

    public LinearLayout getmLayoutParent() {
        return mLayoutParent;
    }

    public void setmLayoutParent(LinearLayout mLayoutParent) {
        this.mLayoutParent = mLayoutParent;
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






    @Override
    public void onClick(View v) {
        for(int i = 0; i< mListLayout.size(); i++){
            if(v == mListLayout.get(i))
            {

                mListenerClick.clickOnCase(i);
               /* if(GameManager.check(mGameObject, mGridGameView,mLayoutParent,i)){
                    GameManager.makeRond(mGameObject, mGridGameView,mLayoutParent,mContext,i);
                }
                else {
                    Log.i("TOTO","?OP");
                    GameManager.deleteRond(mGameObject, mGridGameView,mLayoutParent,mContext,i);
                }*/
            }
        }
    }
}
