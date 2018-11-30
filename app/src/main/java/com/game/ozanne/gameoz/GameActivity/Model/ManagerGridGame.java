package com.game.ozanne.gameoz.GameActivity.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
import com.game.ozanne.gameoz.R;

public class ManagerGridGame implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout mLayoutParent;
    private GridGameObject mGridGameObject;
    private GameObject mGameObject;

    private static final int NUMBER_CELL_COLLUMN = 8;

    public ManagerGridGame(Context mContext, RelativeLayout mLayoutParent) {
        this.mContext = mContext;
        this.mLayoutParent = mLayoutParent;
        this.mGridGameObject = new GridGameObject();
    }


    public  void generateGridLayout(GameObject gameObject){

        this.mGameObject = gameObject;
        LinearLayout parent = new LinearLayout(mContext);
        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        parent.setWeightSum(8);
        parent.setOrientation(LinearLayout.VERTICAL);

        mLayoutParent.addView(parent);

        for(int i = 0 ; i<8;i++){

            View view = LayoutInflater.from(mContext).inflate(R.layout.cell, parent, false);
            //LinearLayout ligne = new LinearLayout(mContext);
            LinearLayout ligne = (LinearLayout) view.findViewById(R.id.cell_game);
            ligne.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
            ligne.setWeightSum(8);
            ligne.setOrientation(LinearLayout.HORIZONTAL);
            parent.addView(ligne);

            for(int p =0;p<8;p++){

                LinearLayout layout = new LinearLayout(mContext);
                mGridGameObject.getmListLayout().add(layout);

                layout.setOnClickListener(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
                params.setMargins(1,1,1,1);
                layout.setLayoutParams(params);
                layout.setBackgroundResource(checkTile(gameObject.getTableauGame().get((8*i)+p).getTypeTiles()));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                ligne.addView(layout);
            }



        }



        initializeImageViewGrid(gameObject);




    }


    public void initializeImageViewGrid(GameObject gameObject){

        for(int i=0;i<8*8;i++){

            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(10,10,10,10);
            imageView.setLayoutParams(lp);

            imageView.setImageResource(checkPion(gameObject.getTableauGame().get(i).getTypeCase()));

            mGridGameObject.getmListImageView().add(i,imageView);
            mGridGameObject.getmListLayout().get(i).addView(mGridGameObject.getmListImageView().get(i));
        }
    }



    private int checkPion(String tileNumber) {
        switch (tileNumber) {
            case "vide":
                return 0;

            case "yellow":
                return R.drawable.jelly_yellow;

            case "blue":
                return R.drawable.jelly_blue;

                default:
                    return 0;

        }
    }


    private int checkTile(int tileNumber){
        switch (tileNumber){
            case 0 :
                return R.drawable.grass;

            case 1:
                return R.drawable.grass_black;

            case 2:
                return R.drawable.sand;

            case 3 :
                return R.drawable.water;

            default:
                return R.drawable.grass;
        }
    }


    public void checkDiffGameObject(GameObject gameObject){

        if(mLayoutParent.getChildCount() == 0){
            // prends en compte le type de tile et le type de case
            generateGridLayout(gameObject);
        }


        else {
            // prends en compte  le type de case
            // code
            // avec la fonction qui verifie si une case est manquante etc..

        }



    }


    @Override
    public void onClick(View v) {
        for(int i = 0; i<mGridGameObject.getmListLayout().size();i++){
            if(v == mGridGameObject.getmListLayout().get(i))
            {
                GameManager.check(mGameObject,mGridGameObject,mLayoutParent,i);
            //  if(mListImageView.get(i).getDrawable().getConstantState() == mContext.getResources().getDrawable(R.drawable.jelly_yellow).getConstantState())
            }
        }
    }
}
