package com.game.ozanne.gameoz.GameActivity.Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObjectGrid;
import com.game.ozanne.gameoz.R;

import java.util.ArrayList;
import java.util.List;

public class GenerateGridGame implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout mLayoutParent;
    private GameObjectGrid mGameObjectGrid;

    private List<LinearLayout> mListLayout;
    private List<ImageView> mListImageView;


    private static final int NUMBER_CELL_COLLUMN = 8;

    public GenerateGridGame(Context mContext, RelativeLayout mLayoutParent, GameObjectGrid mGameObjectGrid) {
        this.mContext = mContext;
        this.mLayoutParent = mLayoutParent;
        this.mGameObjectGrid = mGameObjectGrid;

        this.mListLayout = new ArrayList<>();
        this.mListImageView = new ArrayList<>();
    }


    public  void generateGridLayout(){

         this.mListLayout = new ArrayList<>();
         this.mListImageView = new ArrayList<>();


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
            ligne.setWeightSum(8f);
            ligne.setOrientation(LinearLayout.HORIZONTAL);
            parent.addView(ligne);

            for(int p =0;p<8;p++){
               // int index = (i*8) + p;
                LinearLayout layout = new LinearLayout(mContext);
                mListLayout.add(layout);

                layout.setOnClickListener(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
                params.setMargins(1,1,1,1);
                layout.setLayoutParams(params);
                layout.setBackgroundResource(checkTile(0));
                layout.setOrientation(LinearLayout.HORIZONTAL);
                ligne.addView(layout);
            }



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


    public void checkDiffGameObject(){

        if(mGameObjectGrid == null){

            // prends en compte le type de tile et le type de case
            generateGridLayout();


        }


        else {
            // prends en compte  le type de case
            // code
            // avecla fonction qui verifie si une case est manquante etc..



        }



    }


    @Override
    public void onClick(View v) {
        for(int i = 0; i<mListLayout.size();i++){
            if(v == mListLayout.get(i))
                Log.i("RDR","index => " + i);
        }
    }
}
